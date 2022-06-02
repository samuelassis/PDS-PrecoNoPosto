package com.example.preconoposto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.preconoposto.data.GasStation
import com.example.preconoposto.data.Price
import com.example.preconoposto.data.Rating
import com.example.preconoposto.data.User
import com.example.preconoposto.database.AppDatabase
import com.example.preconoposto.ui.GasStationDetailsFragment
import com.example.preconoposto.ui.LoginFragment
import com.example.preconoposto.ui.main.MainFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.sql.Date

// Adaptador de entrada

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val db = AppDatabase.getInstance(this)
        val gasStationDao = db.gasStationDao
        val priceDao = db.priceDao
        val ratingDao = db.ratingDao
        val userDao = db.userDao

        val gasStation = GasStation(
            idGasStation = 1,
            idAddress = 0,
            idService = 0,
            idRating = 0,
            idPrice = 0,
            name = "Posto Ipiranga Anel 2003"
        )

        val price = Price(
            idPrice = 0,
            idGasStation = 1,
            gasolinePrice = 5.165,
            alcoholPrice = 4.354,
            dieselPrice = 4.665,
            lastUpdateDate = Date(20190731)
        )

        val rating = Rating(
            idRating = 0,
            idGasStation = 1,
            idUser = 1,
            generalScore = 4.7,
            attendanceScore = 4.3,
            qualityScore = 4.1,
            waitingTimeScore = 4.9,
            serviceScore = 3.8,
            safetyScore = 5.0,
            commentary = "Este é um exemplo de comentário feito por um usuário qualquer",
            date = Date(20190731)
        )
        val rating2 = Rating(
            idRating = 1,
            idGasStation = 1,
            idUser = 2,
            generalScore = 4.5,
            attendanceScore = 4.5,
            qualityScore = 4.3,
            waitingTimeScore = 4.7,
            serviceScore = 4.0,
            safetyScore = 4.8,
            commentary = "Exemplo de comentário muito grande: lalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalala",
            date = Date(20190731)
        )
        val rating3 = Rating(
            idRating = 2,
            idGasStation = 1,
            idUser = 2,
            generalScore = 2.1,
            attendanceScore = 1.1,
            qualityScore = 1.3,
            waitingTimeScore = 0.7,
            serviceScore = 1.0,
            safetyScore = 2.8,
            commentary = "Terceiro comentário",
            date = Date(20190731)
        )

        val user = User(
            idUser = 1,
            name = "Fábio Brum",
            email = "email@gmail.com",
            password = "123456",
            birthday = "31/12/1999"
        )
        val user2 = User(
            idUser = 2,
            name = "Emerson Gouveia",
            email = "email@gmail.com",
            password = "123456",
            birthday = "31/12/1999"
        )

        val ioScope = (CoroutineScope(Dispatchers.Default))
        ioScope.launch {
            db.clearAllTables()
            gasStationDao.setGasStation(gasStation)
            priceDao.setPrice(price)
            ratingDao.setRating(rating)
            ratingDao.setRating(rating2)
            ratingDao.setRating(rating3)
            userDao.save(user)
            userDao.save(user2)
        }
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, GasStationDetailsFragment.newInstance())
                .commitNow()
        }
    }
}