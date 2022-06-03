package com.example.preconoposto.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.preconoposto.domain.GasStationDetailsImpl

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
}