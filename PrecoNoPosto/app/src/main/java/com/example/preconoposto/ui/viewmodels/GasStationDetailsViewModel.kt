package com.example.preconoposto.ui.viewmodels

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import com.example.preconoposto.data.Favorite
import com.example.preconoposto.domain.GasStationDetailsImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class GasStationDetailsViewModel : ViewModel() {

    var priceGasoline = MutableLiveData<String>("0")
    var priceAlcohol = MutableLiveData<String>("0")
    var priceDiesel = MutableLiveData<String>("0")

    lateinit var gasStationDetailsImpl: GasStationDetailsImpl

    fun getScoreAverageTexts(id: Long) = liveData {
        emit(gasStationDetailsImpl.getScoreAverageTexts(id))
    }

    fun getGasStationWithRatingsAndUser(id: Long) = liveData {
        emit(gasStationDetailsImpl.getGasStationWithRatingsAndUser(id))
    }

    fun getPriceTexts(id: Long) = liveData {
        emit(gasStationDetailsImpl.getPriceTexts(id))
    }

    fun saveFavorite(favorite: Favorite){
        CoroutineScope(Dispatchers.IO).launch {
            gasStationDetailsImpl.saveFavorite(favorite)
        }
    }

    fun deleteFavorite(userId: Long, gasStationId: Long){
        CoroutineScope(Dispatchers.IO).launch {
            gasStationDetailsImpl.deleteFavorite(userId, gasStationId)
        }
    }

    fun setNewPrice(item: String, price: String) {
        when(item) {
            "Gasolina" -> priceGasoline.value = "$price/L"
            "Alcool" -> priceAlcohol.value = "$price/L"
            "Diesel" -> priceDiesel.value = "$price/L"
        }
    }

    fun getTodayDateTime(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val timestamp = Timestamp(System.currentTimeMillis())
        return sdf.format(timestamp)
    }
}