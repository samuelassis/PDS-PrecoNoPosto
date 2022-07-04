package com.example.preconoposto.ui

import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.preconoposto.R
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeFragmentTest {

    private lateinit var scenario: FragmentScenario<HomeFragment>

    fun withChildViewCount(count: Int, childMatcher: Matcher<View>): Matcher<View> {
        return object : BoundedMatcher<View, ViewGroup>(ViewGroup::class.java) {
            override fun matchesSafely(viewGroup: ViewGroup): Boolean {
                val matchCount = viewGroup.children
                    .filter { childMatcher.matches(it) }
                    .count()
                return matchCount == count
            }

            override fun describeTo(description: Description) {
                description.appendText("with child count $count")
            }

        }
    }

    @Before
    fun setup() {
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_PrecoNoPosto)
        scenario.moveToState(Lifecycle.State.STARTED)
    }

    @Test
    fun `showMap_networkConnectionAvailable`() {
        onView(withText(R.string.try_again)).check(matches(not(isDisplayed())))
        onView(withId(R.id.homeGasStationMap)).check(matches(isDisplayed()))
    }

    @Test
    fun `showMap_hasConvenienceStore`() {
        onView(withId(R.id.homeHasConvenienceStoreTb)).check(matches(isDisplayed()))
        onView(withId(R.id.homeHasConvenienceStoreTb)).perform(click())
        onView(withChildViewCount(1, withTagKey(1)))
        onView(withChildViewCount(1, withTagKey(2)))
        onView(withChildViewCount(1, withTagKey(3)))
        onView(withChildViewCount(1, withTagKey(4)))
        onView(withChildViewCount(1, withTagKey(8)))
        onView(withChildViewCount(1, withTagKey(9)))
        onView(withChildViewCount(1, withTagKey(10)))
    }

}