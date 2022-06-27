package com.example.preconoposto.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.preconoposto.MainCoroutineRule
import com.example.preconoposto.domain.GasStationDetailsImpl
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.rules.Timeout
import org.junit.runner.Description
import java.util.concurrent.TimeUnit
import kotlin.system.measureTimeMillis
import kotlin.test.assertEquals
import kotlin.test.assertTrue
class GasStationDetailsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val rule = Timeout(1, TimeUnit.MINUTES)

    @RelaxedMockK
    lateinit var gasStationDetails: GasStationDetailsImpl

    @RelaxedMockK
    private lateinit var observerPrice: Observer<String>

    @RelaxedMockK
    private lateinit var observerAverage: Observer<Map<String,String>>

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    init {
        MockKAnnotations.init(this)
    }

    @Test
    fun `when calling setNewPrice to gasoline then should set the new suggested price`() {
        // Mock
        val gasStationDetailsViewModel = GasStationDetailsViewModel()
        val fuel = "Gasolina"
        val price = "7.1999"

        // Act
        gasStationDetailsViewModel.priceGasoline.observeForever(
            observerPrice
        )
        gasStationDetailsViewModel.setNewPrice(fuel, price)

        // Assert
        verify(exactly = 1) {
            observerPrice.onChanged("7.1999/L")
        }
    }

    @Test
    fun `when calling setNewPrice to alcohol then should set the new suggested price`() {
        // Mock
        val gasStationDetailsViewModel = GasStationDetailsViewModel()
        val fuel = "Alcool"
        val price = "6.3799"

        // Act
        gasStationDetailsViewModel.priceAlcohol.observeForever(
            observerPrice
        )
        gasStationDetailsViewModel.setNewPrice(fuel, price)

        // Assert
        verify(exactly = 1) {
            observerPrice.onChanged("6.3799/L")
        }
    }

    @Test
    fun `when calling setNewPrice to diesel then should set the new suggested price`() {
        // Mock
        val gasStationDetailsViewModel = GasStationDetailsViewModel()
        val fuel = "Diesel"
        val price = "8.0999"

        // Act
        gasStationDetailsViewModel.priceDiesel.observeForever(
            observerPrice
        )
        gasStationDetailsViewModel.setNewPrice(fuel, price)

        // Assert
        verify(exactly = 1) {
            observerPrice.onChanged("8.0999/L")
        }
    }

    @Test
    fun `when calling getScoreAverageTexts then should return score average from a specific gas station`() {
        // Mock
        val expected = mapOf<String, String>("generalScore" to "5.0/5.0")

        coEvery {
            gasStationDetails.getScoreAverageTexts(1L)
        } returns expected

        // Act
        val gasStationDetailsViewModel = GasStationDetailsViewModel()
        gasStationDetailsViewModel.gasStationDetailsImpl = gasStationDetails

        gasStationDetailsViewModel.getScoreAverageTexts(1L).observeForever(
            observerAverage
        )

        // Assert
        verify(exactly = 1) {
            observerAverage.onChanged(expected)
        }

    }
}