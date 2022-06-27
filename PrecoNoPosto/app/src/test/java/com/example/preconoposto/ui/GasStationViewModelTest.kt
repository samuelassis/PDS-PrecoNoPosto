package com.example.preconoposto.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.preconoposto.MainCoroutineRule
import com.example.preconoposto.data.GasStation
import com.example.preconoposto.data.Price
import com.example.preconoposto.data.relations.GasStationAndPrice
import com.example.preconoposto.domain.GasStationDetailsImpl
import com.example.preconoposto.domain.GasStationFiltersImpl
import com.example.preconoposto.ui.viewmodels.GasStationDetailsViewModel
import com.example.preconoposto.ui.viewmodels.GasStationsViewModel
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Rule
import org.junit.Test
import org.junit.rules.Timeout
import java.sql.Date
import java.util.concurrent.TimeUnit

class GasStationViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val rule = Timeout(1, TimeUnit.MINUTES)

    @RelaxedMockK
    lateinit var gasStationDetailsImpl: GasStationDetailsImpl

    @RelaxedMockK
    lateinit var gasStationFilter: GasStationFiltersImpl

    @RelaxedMockK
    private lateinit var gasStationsCompleteList: Observer<List<GasStationAndPrice?>>

    @RelaxedMockK
    private lateinit var listOfMapAverages: Observer<List<Map<String,String>>>

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    init {
        MockKAnnotations.init(this)
    }

    private   val gasStation1 = GasStation(
        idGasStation = 1,
        idAddress = 1,
        idService = 1,
        idRating = 1,
        idPrice = 1,
        name = "Posto 1"
    )
    private  val gasStation2 = GasStation(
        idGasStation = 2,
        idAddress = 2,
        idService = 2,
        idRating = 1,
        idPrice = 2,
        name = "Posto 2"
    )
    private val price1 = Price(
        idPrice = 1,
        idGasStation = 1,
        gasolinePrice = 5.849,
        alcoholPrice = 7.650,
        dieselPrice = 6.658,
        lastUpdateDate = Date(20210503)
    )
    private val price2 = Price(
        idPrice = 2,
        idGasStation = 2,
        gasolinePrice = 6.564,
        alcoholPrice = 7.521,
        dieselPrice = 7.123,
        lastUpdateDate = Date(20210502)
    )

    private val gasStationAndPrice1 = GasStationAndPrice(
        gasStation = gasStation1,
        price = price1
    )
    private val gasStationAndPrice2 = GasStationAndPrice(
        gasStation = gasStation2,
        price = price2
    )
    private val listOfAllGasStationsAndPriceInDb = listOf(
        gasStationAndPrice1, gasStationAndPrice2
    )

    @Test
    fun `when calling getAllGasStationsAndPrice it should save all gas stations and their price of DB in gasStationsCompleteList`() {
        // Act
        coEvery {
            gasStationDetailsImpl.getAllGasStationAndPrice()
        } returns listOfAllGasStationsAndPriceInDb

        val gasStationsViewModel = GasStationsViewModel()
        gasStationsViewModel.gasStationDetailsImpl = gasStationDetailsImpl

        gasStationsViewModel.gasStationsCompleteList.observeForever(
            gasStationsCompleteList
        )
        gasStationsViewModel.getAllGasStationsAndPrice()

        // Assert
        verify(exactly = 1) {
            gasStationsCompleteList.onChanged(listOfAllGasStationsAndPriceInDb)
        }
    }

    @Test
    fun `when calling getAllGeneralScoresTexts it should return all general scores of DB`(){
        val listOfGasStationIds = listOf(1L, 2L, 3L)
        val mapOfAverageScores = mapOf(
            "generalScore" to "0.0/5.0",
            "attendanceScore" to "0.0/5.0",
            "qualityScore" to "0.0/5.0",
            "waitingTimeScore" to "0.0/5.0",
            "serviceScore" to "0.0/5.0",
            "safetyScore" to "0.0/5.0",
        )
        val expectedListOfMapAverages = listOf(
            mapOfAverageScores, mapOfAverageScores, mapOfAverageScores
        )

        coEvery {
            gasStationDetailsImpl.getScoreAverageTexts(listOfGasStationIds[0])
        } returns mapOfAverageScores
        coEvery {
            gasStationDetailsImpl.getScoreAverageTexts(listOfGasStationIds[1])
        } returns mapOfAverageScores
        coEvery {
            gasStationDetailsImpl.getScoreAverageTexts(listOfGasStationIds[2])
        } returns mapOfAverageScores

        val gasStationsViewModel = GasStationsViewModel()
        gasStationsViewModel.gasStationDetailsImpl = gasStationDetailsImpl

        gasStationsViewModel.getAllGeneralScoresTexts(listOfGasStationIds).observeForever(
            listOfMapAverages
        )

        verify(exactly = 1){
            listOfMapAverages.onChanged(expectedListOfMapAverages)
        }
    }
}