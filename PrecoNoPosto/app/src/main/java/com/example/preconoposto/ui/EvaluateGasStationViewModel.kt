package com.example.preconoposto.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.preconoposto.data.Rating
import com.example.preconoposto.domain.GasStationRatingImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EvaluateGasStationViewModel : ViewModel() {

    lateinit var gasStationRatingImpl: GasStationRatingImpl

    var scoresAreValid: MutableLiveData<Boolean> = MutableLiveData()

    fun saveRating(rating: Rating) {
        if (
            rating.generalScore > 5 ||
            rating.attendanceScore > 5 ||
            rating.qualityScore > 5 ||
            rating.waitingTimeScore > 5 ||
            rating.serviceScore > 5 ||
            rating.safetyScore > 5
        ) scoresAreValid.postValue(false)
        else{
            CoroutineScope(Dispatchers.Default).launch {
                gasStationRatingImpl.save(rating)
                scoresAreValid.postValue(true)
            }
        }
    }
}