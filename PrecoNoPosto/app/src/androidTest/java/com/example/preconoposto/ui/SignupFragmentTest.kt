package com.example.preconoposto.ui

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.preconoposto.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SignupFragmentTest {

    private lateinit var scenario: FragmentScenario<SignupFragment>

    @Before
    fun setup() {
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_PrecoNoPosto)
        scenario.moveToState(Lifecycle.State.STARTED)
    }

    @Test
    fun `showAlert_emptyFields`() {
        onView(withId(R.id.signupConfirmButton)).perform(click())
        onView(withText(R.string.signup_required_field_error_title)).check(matches(isDisplayed()))
    }

    @Test
    fun `showAlert_invalidBirthDateFormat`() {
        onView(withId(R.id.signupBirthDateEditText)).perform(typeText("10"))
        onView(withText(R.string.inserir_campo_data_de_nascimento)).check(matches(isDisplayed()))
    }

    @Test
    fun `showAlert_underage`() {
        onView(withId(R.id.signupBirthDateEditText)).perform(typeText("01/01/2020"))
        onView(withText(R.string.menor_de_idade)).check(matches(isDisplayed()))
    }

    @Test
    fun `showAlert_invalidAge`() {
        onView(withId(R.id.signupBirthDateEditText)).perform(typeText("01/01/3000"))
        onView(withText(R.string.data_de_nascimento_invalida)).check(matches(isDisplayed()))
    }

    @Test
    fun `successfully_signedUp`() {
        onView(withId(R.id.signupNameEditText)).perform(typeText("John"))
        onView(withId(R.id.signupSurnameEditText)).perform(typeText("Doe"))
        onView(withId(R.id.signupBirthDateEditText)).perform(typeText("01/01/2000"), closeSoftKeyboard())
        onView(withId(R.id.signupEmailEditText)).perform(typeText("johndoe@gmail.com"), closeSoftKeyboard())
        onView(withId(R.id.signupPasswordEditText)).perform(typeText("password"), closeSoftKeyboard())

        onView(withText(R.string.signup_required_field_error_title)).check(doesNotExist())
        onView(withText(R.string.data_de_nascimento_invalida)).check(doesNotExist())
        onView(withText(R.string.menor_de_idade)).check(doesNotExist())
        onView(withText(R.string.inserir_campo_data_de_nascimento)).check(doesNotExist())

        onView(withId(R.id.signupConfirmButton)).perform(click())
    }
}