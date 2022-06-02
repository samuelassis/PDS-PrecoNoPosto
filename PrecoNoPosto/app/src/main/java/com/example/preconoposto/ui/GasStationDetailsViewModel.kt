package com.example.preconoposto.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.preconoposto.data.relations.GasStationAndPrice
import com.example.preconoposto.data.relations.GasStationWithRatingsAndUser

class GasStationDetailsViewModel : ViewModel() {

    private var _gasStationWithRatingsAndUser: MutableLiveData<GasStationWithRatingsAndUser> = MutableLiveData()
    val gasStationWithRatingsAndUser: LiveData<GasStationWithRatingsAndUser> get() = _gasStationWithRatingsAndUser

    private var _gasStationAndPrice: MutableLiveData<GasStationAndPrice> = MutableLiveData()
    val gasStationAndPrice: LiveData<GasStationAndPrice> get() = _gasStationAndPrice

    fun setGasStationWithRatingsAndUser(gasStationWithRatingsAndUser: GasStationWithRatingsAndUser){
        this._gasStationWithRatingsAndUser.postValue(gasStationWithRatingsAndUser)
    }

    fun setGasStationAndPrice(gasStationAndPrice: GasStationAndPrice) {
        this._gasStationAndPrice.postValue(gasStationAndPrice)
    }

    fun getGasStationWithRatingsAndUser() = _gasStationWithRatingsAndUser

    fun getGasStationAndPrice() = _gasStationAndPrice

    fun getGeneralScoreText() =
        gasStationWithRatingsAndUser.value?.ratings?.let { ratingAndUser ->
            val generalScore = ratingAndUser.map {
                it.rating.generalScore
            }
            String.format("%.1f", generalScore.average())+"/5.0"
        } ?: "--/5.0"

    fun getAttendanceScoreText() =
        gasStationWithRatingsAndUser.value?.ratings?.let { ratingAndUser ->
            val attendanceScore = ratingAndUser.map {
                it.rating.attendanceScore
            }
            String.format("%.1f", attendanceScore.average())+"/5.0"
        } ?: "--/5.0"

    fun getQualityScoreText() =
        gasStationWithRatingsAndUser.value?.ratings?.let { ratingAndUser ->
            val qualityScore = ratingAndUser.map {
                it.rating.qualityScore
            }
            String.format("%.1f", qualityScore.average())+"/5.0"
        } ?: "--/5.0"

    fun getWaitingTimeScoreText() =
        gasStationWithRatingsAndUser.value?.ratings?.let { ratingAndUser ->
            val waitingTimeScore = ratingAndUser.map {
                it.rating.waitingTimeScore
            }
            String.format("%.1f", waitingTimeScore.average())+"/5.0"
        } ?: "--/5.0"

    fun getServiceScoreText() =
        gasStationWithRatingsAndUser.value?.ratings?.let { ratingAndUser ->
            val serviceScore = ratingAndUser.map {
                it.rating.serviceScore
            }
            String.format("%.1f", serviceScore.average())+"/5.0"
        } ?: "--/5.0"

    fun getSafetyScoreText() =
        gasStationWithRatingsAndUser.value?.ratings?.let { ratingAndUser ->
            val safetyScore = ratingAndUser.map {
                it.rating.safetyScore
            }
            String.format("%.1f", safetyScore.average())+"/5.0"
        } ?: "--/5.0"

    fun getGasolinePriceText() =
        gasStationAndPrice.value?.price?.let { price ->
            String.format("%.3f", price.gasolinePrice)+"/L"
        } ?: "0.000L"

    fun getAlcoholPriceText() =
        gasStationAndPrice.value?.price?.let { price ->
            String.format("%.3f", price.alcoholPrice)+"/L"
        } ?: "0.000L"

    fun getDieselPriceText() =
        gasStationAndPrice.value?.price?.let { price ->
            String.format("%.3f", price.dieselPrice)+"/L"
        } ?: "0.000L"
}