package com.example.preconoposto.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.preconoposto.MainCoroutineRule
import com.example.preconoposto.data.GasStation
import com.example.preconoposto.data.Price
import com.example.preconoposto.data.Rating
import com.example.preconoposto.data.User
import com.example.preconoposto.data.relations.GasStationAndPrice
import com.example.preconoposto.data.relations.GasStationWithRatingsAndUser
import com.example.preconoposto.data.relations.RatingAndUser
import com.example.preconoposto.repository.FavoriteRepository
import com.example.preconoposto.repository.GasStationRepository
import com.example.preconoposto.ui.viewmodels.GasStationDetailsViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.rules.Timeout
import java.sql.Date
import java.util.concurrent.TimeUnit
import kotlin.test.assertEquals

class GasStationDetailsTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val rule = Timeout(1, TimeUnit.MINUTES)

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @RelaxedMockK
    lateinit var gasStationDetails: GasStationDetailsImpl

    @RelaxedMockK
    lateinit var gasStationRepository: GasStationRepository

    @RelaxedMockK
    lateinit var favoriteRepository: FavoriteRepository

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
    fun `when calling getGasStationWithRatingsAndUser then should return rating and user from a specific gas station`() = runBlocking{
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
            gasStationRepository.getGasStationWithRatingsAndUser(1L)
        } returns expected

        // Act
        val gasStationDetails = GasStationDetailsImpl(favoriteRepository, gasStationRepository)

        val getGasStationWithRatingsAndUser = gasStationDetails.getGasStationWithRatingsAndUser(1L)

        // Assert
        assertEquals(getGasStationWithRatingsAndUser, expected)
    }

    @Test
    fun `when calling getGasStationAndPrice then should return gas station and price`() = runBlocking{
        // Arrange
        val mockGasStation = GasStation(1L, 1L, 1L, 1L, 1L, "Posto 1")
        val mockPrice = Price(
            1L, 1L, 6.099, 6.099, 6.099, Date(20210503)
        )
        val expected = GasStationAndPrice(mockPrice, mockGasStation)

        // Mock
        coEvery {
            gasStationRepository.getGasStationAndPrice(1L)
        } returns expected

        // Act
        val gasStationDetails = GasStationDetailsImpl(favoriteRepository, gasStationRepository)

        val getGasStationAndPrice = gasStationDetails.getGasStationAndPrice(1L)

        // Assert
        assertEquals(getGasStationAndPrice, expected)
    }

    @Test
    fun `when calling getScoreAverageTexts then should return score average from a specific gas station`() = runBlocking {
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

        val mockGasStationWithRatingsAndUser = GasStationWithRatingsAndUser(mockGasStation, mockListOfRatingAndUser)

        val expected = mapOf(
            "generalScore" to "NaN/5.0",
            "attendanceScore" to "NaN/5.0",
            "qualityScore" to "NaN/5.0",
            "waitingTimeScore" to "NaN/5.0",
            "serviceScore" to "NaN/5.0",
            "safetyScore" to "NaN/5.0"
        )

        // Mock
        coEvery {
            gasStationDetails.getScoreAverageTexts(1L)
        } returns expected

        coEvery {
            gasStationDetails.getGasStationWithRatingsAndUser(1L)
        } returns mockGasStationWithRatingsAndUser

        // Act
        val gasStationDetails = GasStationDetailsImpl(favoriteRepository, gasStationRepository)

        val getScoreAverageTexts = gasStationDetails.getScoreAverageTexts(1L)

        // Assert
        assertEquals(getScoreAverageTexts, expected)
    }


}