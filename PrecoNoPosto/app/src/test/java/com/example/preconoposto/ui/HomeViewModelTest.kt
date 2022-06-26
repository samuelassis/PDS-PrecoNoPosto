package com.example.preconoposto.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.preconoposto.data.*
import com.example.preconoposto.data.relations.GasStationAndAddressAndPriceAndService
import com.example.preconoposto.domain.GasStationFiltersImpl
import com.example.preconoposto.ui.viewmodels.EvaluateGasStationViewModel
import com.example.preconoposto.ui.viewmodels.HomeViewModel
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Rule
import org.junit.Test
import org.junit.rules.Timeout
import java.sql.Date
import java.util.concurrent.TimeUnit
import kotlin.test.assertEquals

class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val rule = Timeout(1, TimeUnit.MINUTES)

    @RelaxedMockK
    private lateinit var gasStationsCompleteList: Observer<List<GasStationAndAddressAndPriceAndService?>>

    @RelaxedMockK
    private lateinit var _gasStationsFilteredSet: Observer<MutableSet<GasStationAndAddressAndPriceAndService>>

    private val gasStationFilters = mockk<GasStationFiltersImpl>()

    private val gasStation1 = GasStation(
        idGasStation = 1,
        idAddress = 1,
        idService = 1,
        idRating = 1,
        idPrice = 1,
        name = "Posto 1"
    )
    private val address1 = Address(
        idAddress = 1,
        street = "Av. Pres. Antônio Carlos",
        number = 6640,
        cep = 30518280,
        city = "Belo Horizonte"
    )
    private val price1 = Price(
        idPrice = 1,
        idGasStation = 1,
        gasolinePrice = 5.849,
        alcoholPrice = 7.650,
        dieselPrice = 6.658,
        lastUpdateDate = Date(20210503)
    )
    private val service1 = Service(
        idService = 1,
        idGasStation = 1,
        hasConvenienceStore = true,
        hasCarWash = true,
        hasCalibrator = true,
        hasOilChange = true,
        hasTireShop = false,
        hasRestaurant = false,
        hasMechanical = false
    )

    private val gasStation2 = GasStation(
        idGasStation = 2,
        idAddress = 2,
        idService = 2,
        idRating = 2,
        idPrice = 2,
        name = "Posto 2"
    )

    private val address2 = Address(
        idAddress = 2,
        street = "Av. Pres. Antônio Carlos",
        number = 6640,
        cep = 30518280,
        city = "Belo Horizonte"
    )

    private val price2 = Price(
        idPrice = 2,
        idGasStation = 2,
        gasolinePrice = 5.849,
        alcoholPrice = 7.650,
        dieselPrice = 6.658,
        lastUpdateDate = Date(20210503)
    )

    private val service2 = Service(
        idService = 2,
        idGasStation = 2,
        hasConvenienceStore = false,
        hasCarWash = false,
        hasCalibrator = false,
        hasOilChange = true,
        hasTireShop = true,
        hasRestaurant = true,
        hasMechanical = true
    )

    init {
        MockKAnnotations.init(this)
    }

    @Test
    fun `getAllGasStationsAndAddressAndPriceAndService should return all occurrences in DB`(){
        val expectedList = listOf(
            GasStationAndAddressAndPriceAndService(
                gasStation1, address1, price1, service1
            ),
            GasStationAndAddressAndPriceAndService(
                gasStation2, address2, price2, service2
            )
        )

        coEvery {
            gasStationFilters.getAllGasStationsAndAddressAndPriceAndService()
        } returns expectedList

        val homeViewModel = HomeViewModel()
        homeViewModel.gasStationFilter = gasStationFilters

        homeViewModel.gasStationsCompleteList.observeForever(
            gasStationsCompleteList
        )
        homeViewModel.getAllGasStationsAndAddressAndPriceAndService()

        verify(exactly = 1){
            gasStationsCompleteList.onChanged(expectedList)
        }
    }

    @Test
    fun `getAllGasStationsThatHaveConvenienceStore should return all gas stations with Convenience Store in DB`(){
        val completeList = listOf(
            GasStationAndAddressAndPriceAndService(
                gasStation1, address1, price1, service1
            ),
            GasStationAndAddressAndPriceAndService(
                gasStation2, address2, price2, service2
            )
        )
        val expectedList = listOf(
            GasStationAndAddressAndPriceAndService(
                gasStation1, address1, price1, service1
            )
        )

        val homeViewModel = HomeViewModel()

        coEvery {
            gasStationFilters.getAllGasStationsThatHaveConvenienceStore(completeList)
        } returns expectedList

        homeViewModel.aux = completeList
        homeViewModel.gasStationFilter = gasStationFilters
        homeViewModel._gasStationsCompleteList.value = completeList

        homeViewModel.getAllGasStationsThatHaveConvenienceStore()

        assertEquals(expectedList, homeViewModel.aux)
    }

    @Test
    fun `getAllGasStationsThatHaveCarWash should return all gas stations with Car Wash in DB`(){
        val completeList = listOf(
            GasStationAndAddressAndPriceAndService(
                gasStation1, address1, price1, service1
            ),
            GasStationAndAddressAndPriceAndService(
                gasStation2, address2, price2, service2
            )
        )
        val expectedList = listOf(
            GasStationAndAddressAndPriceAndService(
                gasStation1, address1, price1, service1
            )
        )

        val homeViewModel = HomeViewModel()

        coEvery {
            gasStationFilters.getAllGasStationsThatHaveCarWash(completeList)
        } returns expectedList

        homeViewModel.aux = completeList
        homeViewModel.gasStationFilter = gasStationFilters
        homeViewModel._gasStationsCompleteList.value = completeList

        homeViewModel.getAllGasStationsThatHaveCarWash()

        assertEquals(expectedList, homeViewModel.aux)
    }

    @Test
    fun `getAllGasStationsThatHaveCalibrator should return all gas stations with Calibrator in DB`(){
        val completeList = listOf(
            GasStationAndAddressAndPriceAndService(
                gasStation1, address1, price1, service1
            ),
            GasStationAndAddressAndPriceAndService(
                gasStation2, address2, price2, service2
            )
        )
        val expectedList = listOf(
            GasStationAndAddressAndPriceAndService(
                gasStation1, address1, price1, service1
            )
        )

        val homeViewModel = HomeViewModel()

        coEvery {
            gasStationFilters.getAllGasStationsThatHaveCalibrator(completeList)
        } returns expectedList

        homeViewModel.aux = completeList
        homeViewModel.gasStationFilter = gasStationFilters
        homeViewModel._gasStationsCompleteList.value = completeList

        homeViewModel.getAllGasStationsThatHaveCalibrator()

        assertEquals(expectedList, homeViewModel.aux)
    }

    @Test
    fun `getAllGasStationsThatHaveOilChange should return all gas stations with Oil Change in DB`(){
        val completeList = listOf(
            GasStationAndAddressAndPriceAndService(
                gasStation1, address1, price1, service1
            ),
            GasStationAndAddressAndPriceAndService(
                gasStation2, address2, price2, service2
            )
        )
        val expectedList = listOf(
            GasStationAndAddressAndPriceAndService(
                gasStation1, address1, price1, service1
            ),
            GasStationAndAddressAndPriceAndService(
                gasStation2, address2, price2, service2
            )
        )

        val homeViewModel = HomeViewModel()

        coEvery {
            gasStationFilters.getAllGasStationsThatHaveOilChange(completeList)
        } returns expectedList

        homeViewModel.aux = completeList
        homeViewModel.gasStationFilter = gasStationFilters
        homeViewModel._gasStationsCompleteList.value = completeList

        homeViewModel.getAllGasStationsThatHaveOilChange()

        assertEquals(expectedList, homeViewModel.aux)
    }

    @Test
    fun `getAllGasStationsThatHaveTireShop should return all gas stations with Tire Shop in DB`(){
        val completeList = listOf(
            GasStationAndAddressAndPriceAndService(
                gasStation1, address1, price1, service1
            ),
            GasStationAndAddressAndPriceAndService(
                gasStation2, address2, price2, service2
            )
        )
        val expectedList = listOf(
            GasStationAndAddressAndPriceAndService(
                gasStation2, address2, price2, service2
            )
        )

        val homeViewModel = HomeViewModel()

        coEvery {
            gasStationFilters.getAllGasStationsThatHaveTireShop(completeList)
        } returns expectedList

        homeViewModel.aux = completeList
        homeViewModel.gasStationFilter = gasStationFilters
        homeViewModel._gasStationsCompleteList.value = completeList

        homeViewModel.getAllGasStationsThatHaveTireShop()

        assertEquals(expectedList, homeViewModel.aux)
    }

    @Test
    fun `getAllGasStationsThatHaveRestaurant should return all gas stations with Restaurant in DB`(){
        val completeList = listOf(
            GasStationAndAddressAndPriceAndService(
                gasStation1, address1, price1, service1
            ),
            GasStationAndAddressAndPriceAndService(
                gasStation2, address2, price2, service2
            )
        )
        val expectedList = listOf(
            GasStationAndAddressAndPriceAndService(
                gasStation2, address2, price2, service2
            )
        )

        val homeViewModel = HomeViewModel()

        coEvery {
            gasStationFilters.getAllGasStationsThatHaveRestaurant(completeList)
        } returns expectedList

        homeViewModel.aux = completeList
        homeViewModel.gasStationFilter = gasStationFilters
        homeViewModel._gasStationsCompleteList.value = completeList

        homeViewModel.getAllGasStationsThatHaveRestaurant()

        assertEquals(expectedList, homeViewModel.aux)
    }

    @Test
    fun `getAllGasStationsThatHaveMechanical should return all gas stations with Mechanical in DB`(){
        val completeList = listOf(
            GasStationAndAddressAndPriceAndService(
                gasStation1, address1, price1, service1
            ),
            GasStationAndAddressAndPriceAndService(
                gasStation2, address2, price2, service2
            )
        )
        val expectedList = listOf(
            GasStationAndAddressAndPriceAndService(
                gasStation2, address2, price2, service2
            )
        )

        val homeViewModel = HomeViewModel()

        coEvery {
            gasStationFilters.getAllGasStationsThatHaveMechanical(completeList)
        } returns expectedList

        homeViewModel.aux = completeList
        homeViewModel.gasStationFilter = gasStationFilters
        homeViewModel._gasStationsCompleteList.value = completeList

        homeViewModel.getAllGasStationsThatHaveMechanical()

        assertEquals(expectedList, homeViewModel.aux)
    }

    @Test
    fun `clearGasStationFilter should set _gasStationsFilteredSetAux to a empty mutable set`() {
        val notEmptyMutableSet = mutableSetOf(
            GasStationAndAddressAndPriceAndService(
                gasStation1, address1, price1, service1
            ),
            GasStationAndAddressAndPriceAndService(
                gasStation2, address2, price2, service2
            )
        )
        val emptyMutableSet = mutableSetOf<GasStationAndAddressAndPriceAndService>()
        val homeViewModel = HomeViewModel()
        homeViewModel._gasStationsFilteredSetAux = notEmptyMutableSet

        homeViewModel.clearGasStationFilter()
        assertEquals(homeViewModel._gasStationsFilteredSetAux, emptyMutableSet)
    }

    @Test
    fun `setFilteredList should set _gasStationsFilteredSet to _gasStationsFilteredSetAux value`() {
        val notEmptyMutableSet = mutableSetOf(
            GasStationAndAddressAndPriceAndService(
                gasStation1, address1, price1, service1
            ),
            GasStationAndAddressAndPriceAndService(
                gasStation2, address2, price2, service2
            )
        )

        val homeViewModel = HomeViewModel()

        homeViewModel._gasStationsFilteredSet.observeForever(
            _gasStationsFilteredSet
        )

        homeViewModel._gasStationsFilteredSet.postValue(null)
        homeViewModel._gasStationsFilteredSetAux = notEmptyMutableSet

        homeViewModel.setFilteredList()

        verify(exactly = 1) {
            _gasStationsFilteredSet.onChanged(notEmptyMutableSet)
        }
    }
}