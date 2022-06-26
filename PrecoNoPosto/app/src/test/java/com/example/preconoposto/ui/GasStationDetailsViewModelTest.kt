package com.example.preconoposto.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.preconoposto.domain.GasStationDetailsImpl
import com.example.preconoposto.ui.viewmodels.GasStationDetailsViewModel
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import org.junit.Rule
import org.junit.Test
import org.junit.rules.Timeout
import java.util.concurrent.TimeUnit

class GasStationDetailsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val rule = Timeout(1, TimeUnit.MINUTES)

    @RelaxedMockK
    lateinit var gasStationDetails: GasStationDetailsImpl

    @RelaxedMockK
    private lateinit var observerPrice: Observer<String>

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


        // Act

        // Assert

    }
}