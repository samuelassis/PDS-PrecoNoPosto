package com.example.preconoposto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.preconoposto.data.*
import com.example.preconoposto.database.AppDatabase
import com.example.preconoposto.databinding.MainActivityBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.sql.Date
import com.example.preconoposto.ui.SignupFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addMockData()
        setupNavBar()


//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.container, SignupFragment.newInstance())
//                .commitNow()
//        }
    }

    private fun setupNavBar() {
        val navHostFragment = (supportFragmentManager.findFragmentById(binding.containerViewMain.id)) as NavHostFragment
        val navController = navHostFragment.navController

        val appBarConfig = AppBarConfiguration(navController.graph)
        binding.mainToolbar.setupWithNavController(navController, appBarConfig)

        navController.addOnDestinationChangedListener { _, destination, args ->

            when(destination.id) {
                R.id.signupFragment -> {
                    binding.mainToolbar.visibility = View.VISIBLE
                    binding.mainToolbar.visibility = View.VISIBLE
                }
                else -> {
                    binding.mainToolbar.visibility = View.GONE
                    binding.mainToolbar.visibility = View.GONE
                }
            }
        }
    }

    private fun addMockData(){
        val db = AppDatabase.getInstance(this)
        val priceDao = db.priceDao
        val serviceDao = db.serviceDao
        val gasStationDao = db.gasStationDao
        val addressDao = db.addressDao
        val ratingDao = db.ratingDao
        val userDao = db.userDao

        val gasStation1 = GasStation(
            idGasStation = 1,
            idAddress = 1,
            idService = 1,
            idRating = 1,
            idPrice = 1,
            name = "Posto 1"
        )
        val gasStation2 = GasStation(
            idGasStation = 2,
            idAddress = 2,
            idService = 2,
            idRating = 1,
            idPrice = 2,
            name = "Posto 2"
        )
        val gasStation3 = GasStation(
            idGasStation = 3,
            idAddress = 3,
            idService = 3,
            idRating = 1,
            idPrice = 3,
            name = "Posto 3"
        )
        val gasStation4 = GasStation(
            idGasStation = 4,
            idAddress = 4,
            idService = 4,
            idRating = 1,
            idPrice = 4,
            name = "Posto 4"
        )
        val gasStation5 = GasStation(
            idGasStation = 5,
            idAddress = 5,
            idService = 5,
            idRating = 1,
            idPrice = 5,
            name = "Posto 5"
        )
        val gasStation6 = GasStation(
            idGasStation = 6,
            idAddress = 6,
            idService = 6,
            idRating = 1,
            idPrice = 6,
            name = "Posto 6"
        )
        val gasStation7 = GasStation(
            idGasStation = 7,
            idAddress = 7,
            idService = 7,
            idRating = 1,
            idPrice = 7,
            name = "Posto 7"
        )
        val gasStation8 = GasStation(
            idGasStation = 8,
            idAddress = 8,
            idService = 8,
            idRating = 1,
            idPrice = 8,
            name = "Posto 8"
        )
        val gasStation9 = GasStation(
            idGasStation = 9,
            idAddress = 9,
            idService = 9,
            idRating = 1,
            idPrice = 9,
            name = "Posto 9"
        )
        val gasStation10 = GasStation(
            idGasStation = 10,
            idAddress = 10,
            idService = 10,
            idRating = 1,
            idPrice = 10,
            name = "Posto 10"
        )

        val address1 = Address(
            idAddress = 1,
            street = "Av. Pres. Antônio Carlos",
            number = 6640,
            cep = 30518280,
            city = "Belo Horizonte"
        )
        val address2 = Address(
            idAddress = 2,
            street = "R. Henrique Cabral, 19",
            number = 19,
            cep = 30518280,
            city = "Belo Horizonte"
        )
        val address3 = Address(
            idAddress = 3,
            street = "Av. Pres. Antônio Carlos",
            number = 7826,
            cep = 30518280,
            city = "Belo Horizonte"
        )
        val address4 = Address(
            idAddress = 4,
            street = "R. Conceição do Mato Dentro",
            number = 335,
            cep = 30518280,
            city = "Belo Horizonte"
        )
        val address5 = Address(
            idAddress = 5,
            street = "R. Presidente Carlos Luz",
            number = 4055,
            cep = 30518280,
            city = "Belo Horizonte"
        )
        val address6 = Address(
            idAddress = 6,
            street = "R. Maj. Delfino de Paula",
            number = 2745,
            cep = 30518280,
            city = "Belo Horizonte"
        )
        val address7 = Address(
            idAddress = 7,
            street = "R. Pinto de Alpoim",
            number = 77,
            cep = 30518280,
            city = "Belo Horizonte"
        )
        val address8 = Address(
            idAddress = 8,
            street = "R. Pres. Antônio Carlos",
            number = 3558,
            cep = 30518280,
            city = "Belo Horizonte"
        )
        val address9 = Address(
            idAddress = 9,
            street = "R. Izabel Bueno",
            number = 20,
            cep = 30518280,
            city = "Belo Horizonte"
        )
        val address10 = Address(
            idAddress = 10,
            street = "Av. José Cleto",
            number = 1232,
            cep = 30518280,
            city = "Belo Horizonte"
        )

        val price1 = Price(
            idPrice = 1,
            idGasStation = 1,
            gasolinePrice = 5.849,
            alcoholPrice = 7.650,
            dieselPrice = 6.658,
            lastUpdateDate = Date(20210503)
        )
        val price2 = Price(
            idPrice = 2,
            idGasStation = 2,
            gasolinePrice = 6.564,
            alcoholPrice = 7.521,
            dieselPrice = 7.123,
            lastUpdateDate = Date(20210502)
        )
        val price3 = Price(
            idPrice = 3,
            idGasStation = 3,
            gasolinePrice = 6.321,
            alcoholPrice = 5.312,
            dieselPrice = 7.540,
            lastUpdateDate = Date(20210503)
        )
        val price4 = Price(
            idPrice = 4,
            idGasStation = 4,
            gasolinePrice = 6.31,
            alcoholPrice = 7.345,
            dieselPrice = 7.354,
            lastUpdateDate = Date(20210503)
        )
        val price5 = Price(
            idPrice = 5,
            idGasStation = 5,
            gasolinePrice = 8.54,
            alcoholPrice = 6.352,
            dieselPrice = 9.325,
            lastUpdateDate = Date(20210503)
        )
        val price6 = Price(
            idPrice = 6,
            idGasStation = 6,
            gasolinePrice = 7.354,
            alcoholPrice = 6.54,
            dieselPrice = 6.58,
            lastUpdateDate = Date(20210503)
        )
        val price7 = Price(
            idPrice = 7,
            idGasStation = 7,
            gasolinePrice = 7.984,
            alcoholPrice = 7.645,
            dieselPrice = 7.198,
            lastUpdateDate = Date(20210503)
        )
        val price8 = Price(
            idPrice = 8,
            idGasStation = 8,
            gasolinePrice = 8.64,
            alcoholPrice = 4.354,
            dieselPrice = 6.345,
            lastUpdateDate = Date(20210503)
        )
        val price9 = Price(
            idPrice = 9,
            idGasStation = 9,
            gasolinePrice = 5.354,
            alcoholPrice = 6.59,
            dieselPrice = 7.546,
            lastUpdateDate = Date(20210503)
        )
        val price10 = Price(
            idPrice = 10,
            idGasStation = 10,
            gasolinePrice = 6.647,
            alcoholPrice = 7.543,
            dieselPrice = 8.15,
            lastUpdateDate = Date(20210503)
        )

        val service1 = Service(
            idService = 1,
            idGasStation = 1,
            hasConvenienceStore = true,
            hasCarWash = true,
            hasCalibrator = false,
            hasOilChange = false,
            hasTireShop = false,
            hasRestaurant = false,
            hasMechanical = false
        )
        val service2 = Service(
            idService = 2,
            idGasStation = 2,
            hasConvenienceStore = false,
            hasCarWash = true,
            hasCalibrator = false,
            hasOilChange = false,
            hasTireShop = false,
            hasRestaurant = false,
            hasMechanical = false
        )
        val service3 = Service(
            idService = 3,
            idGasStation = 3,
            hasConvenienceStore = true,
            hasCarWash = false,
            hasCalibrator = true,
            hasOilChange = false,
            hasTireShop = false,
            hasRestaurant = true,
            hasMechanical = false
        )
        val service4 = Service(
            idService = 4,
            idGasStation = 4,
            hasConvenienceStore = true,
            hasCarWash = false,
            hasCalibrator = true,
            hasOilChange = true,
            hasTireShop = false,
            hasRestaurant = true,
            hasMechanical = false
        )
        val service5 = Service(
            idService = 5,
            idGasStation = 5,
            hasConvenienceStore = false,
            hasCarWash = true,
            hasCalibrator = false,
            hasOilChange = false,
            hasTireShop = true,
            hasRestaurant = false,
            hasMechanical = true
        )
        val service6 = Service(
            idService = 6,
            idGasStation = 6,
            hasConvenienceStore = false,
            hasCarWash = false,
            hasCalibrator = false,
            hasOilChange = true,
            hasTireShop = false,
            hasRestaurant = true,
            hasMechanical = true
        )
        val service7 = Service(
            idService = 7,
            idGasStation = 7,
            hasConvenienceStore = false,
            hasCarWash = true,
            hasCalibrator = false,
            hasOilChange = true,
            hasTireShop = false,
            hasRestaurant = false,
            hasMechanical = true
        )
        val service8 = Service(
            idService = 8,
            idGasStation = 8,
            hasConvenienceStore = true,
            hasCarWash = true,
            hasCalibrator = true,
            hasOilChange = true,
            hasTireShop = true,
            hasRestaurant = true,
            hasMechanical = true
        )
        val service9 = Service(
            idService = 9,
            idGasStation = 9,
            hasConvenienceStore = true,
            hasCarWash = true,
            hasCalibrator = true,
            hasOilChange = true,
            hasTireShop = true,
            hasRestaurant = true,
            hasMechanical = true
        )
        val service10 = Service(
            idService = 10,
            idGasStation = 10,
            hasConvenienceStore = true,
            hasCarWash = true,
            hasCalibrator = true,
            hasOilChange = true,
            hasTireShop = true,
            hasRestaurant = true,
            hasMechanical = true
        )

        val user1 = User(
            idUser = 1,
            email = "a",
            password = "a",
            name = "Usuário Um",
            birthDate = "31/02/99"
        )
        val user2 = User(
            idUser = 2,
            email = "a",
            password = "a",
            name = "Usuário Dois",
            birthDate = "31/02/99"
        )
        val user3 = User(
            idUser = 3,
            email = "a",
            password = "a",
            name = "Usuário Três",
            birthDate = "31/02/99"
        )

        val rating1 = Rating(
            idRating = 1,
            idGasStation = 1,
            idUser = 1,
            generalScore = 4.0,
            attendanceScore = 4.0,
            qualityScore = 4.0,
            waitingTimeScore = 4.0,
            serviceScore = 4.0,
            safetyScore = 4.0,
            commentary = "Lorem ipsum dolor sit amet. Quo aspernatur incidunt ut dignissimos galisum et culpa galisum At dolorem quasi est consequuntur quidem. Ad ullam sint ea quasi quia nam sapiente nesciunt et laudantium iusto. Aut aliquid fuga qui velit ipsum qui nemo nisi sit minus voluptatem cum maiores exercitationem et earum labore qui magnam itaque.",
            date = Date(20211010)
        )
        val rating2 = Rating(
            idRating = 2,
            idGasStation = 1,
            idUser = 2,
            generalScore = 5.0,
            attendanceScore = 5.0,
            qualityScore = 5.0,
            waitingTimeScore = 5.0,
            serviceScore = 5.0,
            safetyScore = 5.0,
            commentary = "Lorem ipsum dolor sit amet. Quo aspernatur incidunt ut dignissimos galisum et culpa galisum At dolorem quasi est consequuntur quidem. Ad ullam sint ea quasi quia nam sapiente nesciunt et laudantium iusto. Aut aliquid fuga qui velit ipsum qui nemo nisi sit minus voluptatem cum maiores exercitationem et earum labore qui magnam itaque.",
            date = Date(20211010)
        )
        val rating3 = Rating(
            idRating = 3,
            idGasStation = 7,
            idUser = 3,
            generalScore = 4.6,
            attendanceScore = 5.0,
            qualityScore = 4.9,
            waitingTimeScore = 2.9,
            serviceScore = 4.3,
            safetyScore = 2.3,
            commentary = "Lorem ipsum dolor sit amet. Quo aspernatur incidunt ut dignissimos galisum et culpa galisum At dolorem quasi est consequuntur quidem. Ad ullam sint ea quasi quia nam sapiente nesciunt et laudantium iusto. Aut aliquid fuga qui velit ipsum qui nemo nisi sit minus voluptatem cum maiores exercitationem et earum labore qui magnam itaque.",
            date = Date(20211010)
        )
        val rating4 = Rating(
            idRating = 4,
            idGasStation = 2,
            idUser = 2,
            generalScore = 4.0,
            attendanceScore = 3.2,
            qualityScore = 4.8,
            waitingTimeScore = 3.1,
            serviceScore = 4.1,
            safetyScore = 5.0,
            commentary = "Lorem ipsum dolor sit amet. Quo aspernatur incidunt ut dignissimos galisum et culpa galisum At dolorem quasi est consequuntur quidem. Ad ullam sint ea quasi quia nam sapiente nesciunt et laudantium iusto. Aut aliquid fuga qui velit ipsum qui nemo nisi sit minus voluptatem cum maiores exercitationem et earum labore qui magnam itaque.",
            date = Date(20211010)
        )
        val rating5 = Rating(
            idRating = 5,
            idGasStation = 3,
            idUser = 2,
            generalScore = 3.1,
            attendanceScore = 4.2,
            qualityScore = 5.0,
            waitingTimeScore = 2.9,
            serviceScore = 3.8,
            safetyScore = 4.8,
            commentary = "Lorem ipsum dolor sit amet. Quo aspernatur incidunt ut dignissimos galisum et culpa galisum At dolorem quasi est consequuntur quidem. Ad ullam sint ea quasi quia nam sapiente nesciunt et laudantium iusto. Aut aliquid fuga qui velit ipsum qui nemo nisi sit minus voluptatem cum maiores exercitationem et earum labore qui magnam itaque.",
            date = Date(20211010)
        )
        val rating6 = Rating(
            idRating = 6,
            idGasStation = 4,
            idUser = 2,
            generalScore = 4.6,
            attendanceScore = 5.0,
            qualityScore = 4.8,
            waitingTimeScore = 4.7,
            serviceScore = 4.6,
            safetyScore = 4.0,
            commentary = "Lorem ipsum dolor sit amet. Quo aspernatur incidunt ut dignissimos galisum et culpa galisum At dolorem quasi est consequuntur quidem. Ad ullam sint ea quasi quia nam sapiente nesciunt et laudantium iusto. Aut aliquid fuga qui velit ipsum qui nemo nisi sit minus voluptatem cum maiores exercitationem et earum labore qui magnam itaque.",
            date = Date(20211010)
        )
        val rating7 = Rating(
            idRating = 7,
            idGasStation = 5,
            idUser = 2,
            generalScore = 4.0,
            attendanceScore = 2.2,
            qualityScore = 3.8,
            waitingTimeScore = 4.1,
            serviceScore = 1.1,
            safetyScore = 4.2,
            commentary = "Lorem ipsum dolor sit amet. Quo aspernatur incidunt ut dignissimos galisum et culpa galisum At dolorem quasi est consequuntur quidem. Ad ullam sint ea quasi quia nam sapiente nesciunt et laudantium iusto. Aut aliquid fuga qui velit ipsum qui nemo nisi sit minus voluptatem cum maiores exercitationem et earum labore qui magnam itaque.",
            date = Date(20211010)
        )
        val rating8 = Rating(
            idRating = 8,
            idGasStation = 6,
            idUser = 2,
            generalScore = 4.0,
            attendanceScore = 3.2,
            qualityScore = 4.8,
            waitingTimeScore = 3.1,
            serviceScore = 4.1,
            safetyScore = 5.0,
            commentary = "Lorem ipsum dolor sit amet. Quo aspernatur incidunt ut dignissimos galisum et culpa galisum At dolorem quasi est consequuntur quidem. Ad ullam sint ea quasi quia nam sapiente nesciunt et laudantium iusto. Aut aliquid fuga qui velit ipsum qui nemo nisi sit minus voluptatem cum maiores exercitationem et earum labore qui magnam itaque.",
            date = Date(20211010)
        )
        val rating9 = Rating(
            idRating = 9,
            idGasStation = 8,
            idUser = 2,
            generalScore = 3.2,
            attendanceScore = 4.2,
            qualityScore = 3.8,
            waitingTimeScore = 4.1,
            serviceScore = 4.1,
            safetyScore = 4.0,
            commentary = "Lorem ipsum dolor sit amet. Quo aspernatur incidunt ut dignissimos galisum et culpa galisum At dolorem quasi est consequuntur quidem. Ad ullam sint ea quasi quia nam sapiente nesciunt et laudantium iusto. Aut aliquid fuga qui velit ipsum qui nemo nisi sit minus voluptatem cum maiores exercitationem et earum labore qui magnam itaque.",
            date = Date(20211010)
        )
        val rating10 = Rating(
            idRating = 10,
            idGasStation = 9,
            idUser = 2,
            generalScore = 3.0,
            attendanceScore = 4.2,
            qualityScore = 3.8,
            waitingTimeScore = 4.1,
            serviceScore = 4.9,
            safetyScore = 3.9,
            commentary = "Lorem ipsum dolor sit amet. Quo aspernatur incidunt ut dignissimos galisum et culpa galisum At dolorem quasi est consequuntur quidem. Ad ullam sint ea quasi quia nam sapiente nesciunt et laudantium iusto. Aut aliquid fuga qui velit ipsum qui nemo nisi sit minus voluptatem cum maiores exercitationem et earum labore qui magnam itaque.",
            date = Date(20211010)
        )
        val rating11 = Rating(
            idRating = 11,
            idGasStation = 10,
            idUser = 2,
            generalScore = 4.3,
            attendanceScore = 4.2,
            qualityScore = 4.8,
            waitingTimeScore = 4.1,
            serviceScore = 4.9,
            safetyScore = 5.0,
            commentary = "Lorem ipsum dolor sit amet. Quo aspernatur incidunt ut dignissimos galisum et culpa galisum At dolorem quasi est consequuntur quidem. Ad ullam sint ea quasi quia nam sapiente nesciunt et laudantium iusto. Aut aliquid fuga qui velit ipsum qui nemo nisi sit minus voluptatem cum maiores exercitationem et earum labore qui magnam itaque.",
            date = Date(20211010)
        )

        CoroutineScope(Dispatchers.IO).launch {
            gasStationDao.setGasStation(gasStation1)
            gasStationDao.setGasStation(gasStation2)
            gasStationDao.setGasStation(gasStation3)
            gasStationDao.setGasStation(gasStation4)
            gasStationDao.setGasStation(gasStation5)
            gasStationDao.setGasStation(gasStation6)
            gasStationDao.setGasStation(gasStation7)
            gasStationDao.setGasStation(gasStation8)
            gasStationDao.setGasStation(gasStation9)
            gasStationDao.setGasStation(gasStation10)

            addressDao.saveAddress(address1)
            addressDao.saveAddress(address2)
            addressDao.saveAddress(address3)
            addressDao.saveAddress(address4)
            addressDao.saveAddress(address5)
            addressDao.saveAddress(address6)
            addressDao.saveAddress(address7)
            addressDao.saveAddress(address8)
            addressDao.saveAddress(address9)
            addressDao.saveAddress(address10)

            priceDao.setPrice(price1)
            priceDao.setPrice(price2)
            priceDao.setPrice(price3)
            priceDao.setPrice(price4)
            priceDao.setPrice(price5)
            priceDao.setPrice(price6)
            priceDao.setPrice(price7)
            priceDao.setPrice(price8)
            priceDao.setPrice(price9)
            priceDao.setPrice(price10)

            serviceDao.save(service1)
            serviceDao.save(service2)
            serviceDao.save(service3)
            serviceDao.save(service4)
            serviceDao.save(service5)
            serviceDao.save(service6)
            serviceDao.save(service7)
            serviceDao.save(service8)
            serviceDao.save(service9)
            serviceDao.save(service10)

            userDao.save(user1)
            userDao.save(user2)
            userDao.save(user3)

            ratingDao.save(rating1)
            ratingDao.save(rating2)
            ratingDao.save(rating3)
            ratingDao.save(rating4)
            ratingDao.save(rating5)
            ratingDao.save(rating6)
            ratingDao.save(rating7)
            ratingDao.save(rating8)
            ratingDao.save(rating9)
            ratingDao.save(rating10)
            ratingDao.save(rating11)
        }
    }
}