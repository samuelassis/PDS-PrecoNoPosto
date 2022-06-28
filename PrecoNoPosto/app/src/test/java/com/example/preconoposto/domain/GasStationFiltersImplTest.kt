package com.example.preconoposto.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.preconoposto.MainCoroutineRule
import com.example.preconoposto.data.Address
import com.example.preconoposto.data.GasStation
import com.example.preconoposto.data.Price
import com.example.preconoposto.data.Service
import com.example.preconoposto.data.relations.GasStationAndAddressAndPriceAndService
import com.example.preconoposto.data.relations.GasStationAndPrice
import com.example.preconoposto.data.relations.UserWithFavoritesAndGasStation
import com.example.preconoposto.repository.FavoriteRepository
import com.example.preconoposto.repository.GasStationRepository
import com.example.preconoposto.repository.UserRepository
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.rules.Timeout
import java.sql.Date
import java.util.concurrent.TimeUnit
import kotlin.test.assertEquals

class GasStationFiltersImplTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val rule = Timeout(1, TimeUnit.MINUTES)

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @RelaxedMockK
    val userRepository = mockk<UserRepository>()

    @RelaxedMockK
    val favoriteRepository = mockk<FavoriteRepository>()

    @RelaxedMockK
    val gasStationRepository = mockk<GasStationRepository>()

    private val gasStation1 = mockk<GasStation>()
    private val gasStation2 = mockk<GasStation>()
    private val address1 = mockk<Address>()
    private val address2 = mockk<Address>()
    private val price1 = mockk<Price>()
    private val price2 = mockk<Price>()

    @Test
    fun `calling getAllGasStationsAndAddressAndPriceAndService should return all gas stations with its relations`() = runBlocking {
        val listOfAllGasStationsAndAddressAndPriceAndService = mockk<List<GasStationAndAddressAndPriceAndService>>()

        coEvery {
            gasStationRepository.getAllGasStationsAndAddressAndPriceAndService()
        } returns listOfAllGasStationsAndAddressAndPriceAndService

        val gasStationFiltersImpl = GasStationFiltersImpl(userRepository, favoriteRepository, gasStationRepository)
        val returnedList = gasStationFiltersImpl.getAllGasStationsAndAddressAndPriceAndService()

        assertEquals(listOfAllGasStationsAndAddressAndPriceAndService, returnedList)
    }

    @Test
    fun `calling getGasStationsAndAddressAndPriceAndService should return the gas station with that id and its relations`() = runBlocking {
        val gasStationsAndAddressAndPriceAndService = mockk<GasStationAndAddressAndPriceAndService>()
        val idOfMockedGasStation = 1L

        coEvery {
            gasStationRepository.getGasStationsAndAddressAndPriceAndService(idOfMockedGasStation)
        } returns gasStationsAndAddressAndPriceAndService

        val gasStationFiltersImpl = GasStationFiltersImpl(userRepository, favoriteRepository, gasStationRepository)
        val returnedGasStation = gasStationFiltersImpl.getGasStationsAndAddressAndPriceAndService(
            idOfMockedGasStation
        )

        assertEquals(gasStationsAndAddressAndPriceAndService, returnedGasStation)
    }

    @Test
    fun `calling getAllGasStationsThatHaveConvenienceStore should return all gas stations with Convenience Store and its relations`() = runBlocking {
        val service1 = Service(
            idService = 1,
            idGasStation = 1,
            hasConvenienceStore = true,
            hasCarWash = false,
            hasCalibrator = false,
            hasOilChange = false,
            hasTireShop = false,
            hasRestaurant = false,
            hasMechanical = false
        )
        val service2 = Service(
            idService = 1,
            idGasStation = 1,
            hasConvenienceStore = false,
            hasCarWash = false,
            hasCalibrator = false,
            hasOilChange = false,
            hasTireShop = false,
            hasRestaurant = false,
            hasMechanical = false
        )
        val allGasStationsAndAddressAndPriceAndService = listOf(
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
        val gasStationFiltersImpl = GasStationFiltersImpl(userRepository, favoriteRepository, gasStationRepository)
        val returnedList = gasStationFiltersImpl.getAllGasStationsThatHaveConvenienceStore(allGasStationsAndAddressAndPriceAndService)

        assertEquals(expectedList, returnedList)
    }

    @Test
    fun `calling getAllGasStationsThatHaveCarWash should return all gas stations with Car Wash and its relations`() = runBlocking {
        val service1 = Service(
            idService = 1,
            idGasStation = 1,
            hasConvenienceStore = false,
            hasCarWash = true,
            hasCalibrator = false,
            hasOilChange = false,
            hasTireShop = false,
            hasRestaurant = false,
            hasMechanical = false
        )
        val service2 = Service(
            idService = 1,
            idGasStation = 1,
            hasConvenienceStore = false,
            hasCarWash = false,
            hasCalibrator = false,
            hasOilChange = false,
            hasTireShop = false,
            hasRestaurant = false,
            hasMechanical = false
        )
        val allGasStationsAndAddressAndPriceAndService = listOf(
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
        val gasStationFiltersImpl = GasStationFiltersImpl(userRepository, favoriteRepository, gasStationRepository)
        val returnedList = gasStationFiltersImpl.getAllGasStationsThatHaveCarWash(allGasStationsAndAddressAndPriceAndService)

        assertEquals(expectedList, returnedList)
    }

    @Test
    fun `calling getAllGasStationsThatHaveCalibrator should return all gas stations with Calibrator and its relations`() = runBlocking {
        val service1 = Service(
            idService = 1,
            idGasStation = 1,
            hasConvenienceStore = false,
            hasCarWash = false,
            hasCalibrator = true,
            hasOilChange = false,
            hasTireShop = false,
            hasRestaurant = false,
            hasMechanical = false
        )
        val service2 = Service(
            idService = 1,
            idGasStation = 1,
            hasConvenienceStore = false,
            hasCarWash = false,
            hasCalibrator = false,
            hasOilChange = false,
            hasTireShop = false,
            hasRestaurant = false,
            hasMechanical = false
        )
        val allGasStationsAndAddressAndPriceAndService = listOf(
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
        val gasStationFiltersImpl = GasStationFiltersImpl(userRepository, favoriteRepository, gasStationRepository)
        val returnedList = gasStationFiltersImpl.getAllGasStationsThatHaveCalibrator(allGasStationsAndAddressAndPriceAndService)

        assertEquals(expectedList, returnedList)
    }

    @Test
    fun `calling getAllGasStationsThatHaveOilChange should return all gas stations with Oil Change and its relations`() = runBlocking {
        val service1 = Service(
            idService = 1,
            idGasStation = 1,
            hasConvenienceStore = false,
            hasCarWash = false,
            hasCalibrator = false,
            hasOilChange = true,
            hasTireShop = false,
            hasRestaurant = false,
            hasMechanical = false
        )
        val service2 = Service(
            idService = 1,
            idGasStation = 1,
            hasConvenienceStore = false,
            hasCarWash = false,
            hasCalibrator = false,
            hasOilChange = false,
            hasTireShop = false,
            hasRestaurant = false,
            hasMechanical = false
        )
        val allGasStationsAndAddressAndPriceAndService = listOf(
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
        val gasStationFiltersImpl = GasStationFiltersImpl(userRepository, favoriteRepository, gasStationRepository)
        val returnedList = gasStationFiltersImpl.getAllGasStationsThatHaveOilChange(allGasStationsAndAddressAndPriceAndService)

        assertEquals(expectedList, returnedList)
    }

    @Test
    fun `calling getAllGasStationsThatHaveTireShop should return all gas stations with Tire Shop and its relations`() = runBlocking {
        val service1 = Service(
            idService = 1,
            idGasStation = 1,
            hasConvenienceStore = false,
            hasCarWash = false,
            hasCalibrator = false,
            hasOilChange = false,
            hasTireShop = true,
            hasRestaurant = false,
            hasMechanical = false
        )
        val service2 = Service(
            idService = 1,
            idGasStation = 1,
            hasConvenienceStore = false,
            hasCarWash = false,
            hasCalibrator = false,
            hasOilChange = false,
            hasTireShop = false,
            hasRestaurant = false,
            hasMechanical = false
        )
        val allGasStationsAndAddressAndPriceAndService = listOf(
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
        val gasStationFiltersImpl = GasStationFiltersImpl(userRepository, favoriteRepository, gasStationRepository)
        val returnedList = gasStationFiltersImpl.getAllGasStationsThatHaveTireShop(allGasStationsAndAddressAndPriceAndService)

        assertEquals(expectedList, returnedList)
    }

    @Test
    fun `calling getAllGasStationsThatHaveRestaurant should return all gas stations with Restaurant and its relations`() = runBlocking {
        val service1 = Service(
            idService = 1,
            idGasStation = 1,
            hasConvenienceStore = false,
            hasCarWash = false,
            hasCalibrator = false,
            hasOilChange = false,
            hasTireShop = false,
            hasRestaurant = true,
            hasMechanical = false
        )
        val service2 = Service(
            idService = 1,
            idGasStation = 1,
            hasConvenienceStore = false,
            hasCarWash = false,
            hasCalibrator = false,
            hasOilChange = false,
            hasTireShop = false,
            hasRestaurant = false,
            hasMechanical = false
        )
        val allGasStationsAndAddressAndPriceAndService = listOf(
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
        val gasStationFiltersImpl = GasStationFiltersImpl(userRepository, favoriteRepository, gasStationRepository)
        val returnedList = gasStationFiltersImpl.getAllGasStationsThatHaveRestaurant(allGasStationsAndAddressAndPriceAndService)

        assertEquals(expectedList, returnedList)
    }

    @Test
    fun `calling getAllGasStationsThatHaveMechanical should return all gas stations with Mechanical and its relations`() = runBlocking {
        val service1 = Service(
            idService = 1,
            idGasStation = 1,
            hasConvenienceStore = false,
            hasCarWash = false,
            hasCalibrator = false,
            hasOilChange = false,
            hasTireShop = false,
            hasRestaurant = false,
            hasMechanical = true
        )
        val service2 = Service(
            idService = 1,
            idGasStation = 1,
            hasConvenienceStore = false,
            hasCarWash = false,
            hasCalibrator = false,
            hasOilChange = false,
            hasTireShop = false,
            hasRestaurant = false,
            hasMechanical = false
        )
        val allGasStationsAndAddressAndPriceAndService = listOf(
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
        val gasStationFiltersImpl = GasStationFiltersImpl(userRepository, favoriteRepository, gasStationRepository)
        val returnedList = gasStationFiltersImpl.getAllGasStationsThatHaveMechanical(allGasStationsAndAddressAndPriceAndService)

        assertEquals(expectedList, returnedList)
    }

    @Test
    fun `calling getAllGasStationsOrderedByGasPrice should return all gas stations ordered by Gas Price`() = runBlocking {
        val cheapestGasPrice = Price(
            idPrice = 1,
            idGasStation = 1,
            gasolinePrice = 1.000,
            alcoholPrice = 10.000,
            dieselPrice = 10.000,
            lastUpdateDate = Date(20210503)
        )
        val moreExpensiveGasPrice = Price(
            idPrice = 2,
            idGasStation = 2,
            gasolinePrice = 10.000,
            alcoholPrice = 1.000,
            dieselPrice = 1.000,
            lastUpdateDate = Date(20210503)
        )
        val allGasStationsAndPrice = listOf(
            GasStationAndPrice(
                moreExpensiveGasPrice, gasStation1
            ),
            GasStationAndPrice(
                cheapestGasPrice, gasStation1
            )
        )
        val expectedList = listOf(
            GasStationAndPrice(
                cheapestGasPrice, gasStation1
            ),
            GasStationAndPrice(
                moreExpensiveGasPrice, gasStation1
            )
        )
        val gasStationFiltersImpl = GasStationFiltersImpl(userRepository, favoriteRepository, gasStationRepository)
        val returnedList = gasStationFiltersImpl.getAllGasStationsOrderedByGasPrice(allGasStationsAndPrice)

        assertEquals(expectedList, returnedList)
    }

    @Test
    fun `calling getAllGasStationsOrderedByAlcoholPrice should return all gas stations ordered by Alcohol Price`() = runBlocking {
        val cheapestAlcoholPrice = Price(
            idPrice = 1,
            idGasStation = 1,
            gasolinePrice = 10.000,
            alcoholPrice = 1.000,
            dieselPrice = 10.000,
            lastUpdateDate = Date(20210503)
        )
        val moreExpensiveAlcoholPrice = Price(
            idPrice = 2,
            idGasStation = 2,
            gasolinePrice = 1.000,
            alcoholPrice = 10.000,
            dieselPrice = 1.000,
            lastUpdateDate = Date(20210503)
        )
        val allGasStationsAndPrice = listOf(
            GasStationAndPrice(
                moreExpensiveAlcoholPrice, gasStation1
            ),
            GasStationAndPrice(
                cheapestAlcoholPrice, gasStation1
            )
        )
        val expectedList = listOf(
            GasStationAndPrice(
                cheapestAlcoholPrice, gasStation1
            ),
            GasStationAndPrice(
                moreExpensiveAlcoholPrice, gasStation1
            )
        )
        val gasStationFiltersImpl = GasStationFiltersImpl(userRepository, favoriteRepository, gasStationRepository)
        val returnedList = gasStationFiltersImpl.getAllGasStationsOrderedByAlcoholPrice(allGasStationsAndPrice)

        assertEquals(expectedList, returnedList)
    }

    @Test
    fun `calling getAllGasStationsOrderedByDieselPrice should return all gas stations ordered by Diesel Price`() = runBlocking {
        val cheapestDieselPrice = Price(
            idPrice = 1,
            idGasStation = 1,
            gasolinePrice = 10.000,
            alcoholPrice = 10.000,
            dieselPrice = 1.000,
            lastUpdateDate = Date(20210503)
        )
        val moreExpensiveDieselPrice = Price(
            idPrice = 2,
            idGasStation = 2,
            gasolinePrice = 1.000,
            alcoholPrice = 1.000,
            dieselPrice = 10.000,
            lastUpdateDate = Date(20210503)
        )
        val allGasStationsAndPrice = listOf(
            GasStationAndPrice(
                moreExpensiveDieselPrice, gasStation1
            ),
            GasStationAndPrice(
                cheapestDieselPrice, gasStation1
            )
        )
        val expectedList = listOf(
            GasStationAndPrice(
                cheapestDieselPrice, gasStation1
            ),
            GasStationAndPrice(
                moreExpensiveDieselPrice, gasStation1
            )
        )
        val gasStationFiltersImpl = GasStationFiltersImpl(userRepository, favoriteRepository, gasStationRepository)
        val returnedList = gasStationFiltersImpl.getAllGasStationsOrderedByDieselPrice(allGasStationsAndPrice)

        assertEquals(expectedList, returnedList)
    }

    @Test
    fun `calling getAllUserFavorites should return the user with its favorite gas stations`() = runBlocking {
        val userWithFavoritesAndGasStation = mockk<UserWithFavoritesAndGasStation>()
        val idOfMockedUser = 1L

        coEvery {
            userRepository.getFavorites(idOfMockedUser)
        } returns userWithFavoritesAndGasStation

        val gasStationFiltersImpl = GasStationFiltersImpl(userRepository, favoriteRepository, gasStationRepository)
        val returnedUserWithFavoritesAndGasStation = gasStationFiltersImpl.getAllUserFavorites(idOfMockedUser)

        assertEquals(userWithFavoritesAndGasStation, returnedUserWithFavoritesAndGasStation)
    }
}