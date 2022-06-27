package com.example.preconoposto.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.preconoposto.MainCoroutineRule
import com.example.preconoposto.data.GasStation
import com.example.preconoposto.data.Rating
import com.example.preconoposto.data.User
import com.example.preconoposto.data.relations.GasStationWithRatingsAndUser
import com.example.preconoposto.data.relations.RatingAndUser
import com.example.preconoposto.domain.GasStationDetailsImpl
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Rule
import org.junit.Test
import org.junit.rules.Timeout
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.concurrent.TimeUnit
import kotlin.test.assertEquals

class GasStationDetailsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val rule = Timeout(1, TimeUnit.MINUTES)

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @RelaxedMockK
    lateinit var gasStationDetails: GasStationDetailsImpl

    @RelaxedMockK
    private lateinit var observerPrice: Observer<String>

    @RelaxedMockK
    private lateinit var observerAverage: Observer<Map<String, String>>

    @RelaxedMockK
    private lateinit var observerGasStationWithRatingsAndUser: Observer<GasStationWithRatingsAndUser>

    @RelaxedMockK
    private lateinit var observerFuelsPrice: Observer<Map<String, String>>

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
        // Arrange
        val expected = mapOf<String, String>("generalScore" to "5.0/5.0")

        // Mock
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

    @Test
    fun `when calling getGasStationWithRatingsAndUser then should return rating and user from a specific gas station`() {
        // Arrange
        val mockGasStation = GasStation(1L, 1L, 1L, 1L, 1L, "Posto 1")
        val mockListOfRatingAndUser = listOf(RatingAndUser(Rating(1L,
            1L,
            1L,
            5.0,
            5.0,
            5.0,
            5.0,
            5.0,
            5.0,
            "Dummy comment",
            Date(20210503)), User(1L, "user@user.com", "password", "User", "01/01/2000")))

        val expected = GasStationWithRatingsAndUser(mockGasStation, mockListOfRatingAndUser)

        // Mock
        coEvery {
            gasStationDetails.getGasStationWithRatingsAndUser(1L)
        } returns expected

        // Act
        val gasStationDetailsViewModel = GasStationDetailsViewModel()
        gasStationDetailsViewModel.gasStationDetailsImpl = gasStationDetails

        gasStationDetailsViewModel.getGasStationWithRatingsAndUser(1L).observeForever(
            observerGasStationWithRatingsAndUser
        )

        // Assert
        verify(exactly = 1) {
            observerGasStationWithRatingsAndUser.onChanged(expected)
        }
    }

    @Test
    fun `when calling getPriceTexts then should return the fuel's prices from a specific gas station`() {
        // Arrange
        val expected = mapOf(
            "gasPrice" to "7.1999",
            "alcoholPrice" to "6.3799",
            "dieselPrice" to "8.0999",
            "date" to "${Date(20210503)}"
        )

        // Mock
        coEvery {
            gasStationDetails.getPriceTexts(1L)
        } returns expected

        // Act
        val gasStationDetailsViewModel = GasStationDetailsViewModel()
        gasStationDetailsViewModel.gasStationDetailsImpl = gasStationDetails

        gasStationDetailsViewModel.getPriceTexts(1L).observeForever(
            observerFuelsPrice
        )

        // Assert
        verify(exactly = 1) {
            observerFuelsPrice.onChanged(expected)
        }
    }

    @Test
    fun `when calling getTodayDateTime then should return today date time`() {
        // Arrange
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val expected = sdf.format(Timestamp(System.currentTimeMillis()))

        // Act
        val gasStationDetailsViewModel = GasStationDetailsViewModel()

        // Assert
        assertEquals(expected, gasStationDetailsViewModel.getTodayDateTime())
    }
}