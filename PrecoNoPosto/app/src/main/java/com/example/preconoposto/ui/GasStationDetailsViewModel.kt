package com.example.preconoposto.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.preconoposto.data.Favorite
import com.example.preconoposto.domain.GasStationDetailsImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GasStationDetailsViewModel : ViewModel() {

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
}