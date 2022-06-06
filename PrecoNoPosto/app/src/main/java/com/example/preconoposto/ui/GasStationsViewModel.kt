package com.example.preconoposto.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.preconoposto.data.relations.GasStationAndAddressAndPriceAndService
import com.example.preconoposto.data.relations.GasStationAndPrice
import com.example.preconoposto.domain.GasStationDetailsImpl
import com.example.preconoposto.domain.GasStationFiltersImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GasStationsViewModel : ViewModel() {

    lateinit var gasStationDetailsImpl: GasStationDetailsImpl
    lateinit var gasStationFilter: GasStationFiltersImpl

    private var _gasStationsCompleteList:
            MutableLiveData<List<GasStationAndPrice?>> = MutableLiveData()

    val gasStationsCompleteList:
            LiveData<List<GasStationAndPrice?>>
        get() = _gasStationsCompleteList

    /*private var _gasStationsFilteredSet:
            MutableLiveData<MutableSet<GasStationAndPrice>> = MutableLiveData()

    val gasStationsFilteredSet:
            LiveData<MutableSet<GasStationAndPrice>> get() = _gasStationsFilteredSet

    private var _gasStationsFilteredSetAux:
            MutableSet<GasStationAndPrice> = mutableSetOf()*/

    fun getAllGasStationsAndPrice()  {
        CoroutineScope(Dispatchers.IO).launch {
            _gasStationsCompleteList.postValue(gasStationDetailsImpl.getAllGasStationAndPrice())
        }
    }

    fun getAllGeneralScoresTexts(idList: List<Long>) = liveData {
        val texts = idList.map {
            gasStationDetailsImpl.getScoreAverageTexts(it)
        }
        emit(texts)
    }

    fun getAllGasStationsOrderedByGasPrice(){
        gasStationsCompleteList.value?.let {
            _gasStationsCompleteList.postValue(
                gasStationFilter.getAllGasStationsOrderedByGasPrice(it)
            )
        }
    }

    fun getAllGasStationsOrderedByAlcoholPrice(){
        gasStationsCompleteList.value?.let {
            _gasStationsCompleteList.postValue(
                gasStationFilter.getAllGasStationsOrderedByAlcoholPrice(it)
            )
        }
    }

    fun getAllGasStationsOrderedByDieselPrice(){
        gasStationsCompleteList.value?.let {
            _gasStationsCompleteList.postValue(
                gasStationFilter.getAllGasStationsOrderedByDieselPrice(it)
            )
        }
    }

    /*fun getAllUserFavorites(userId: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            gasStationsCompleteList.value?.let {
                val userFavorites = gasStationFilter.getAllUserFavorites(userId)
                val favoriteGasStationIds = userFavorites?.favorites?.map {
                    it.favorite.idGasStation
                }
                val aux = favoriteGasStationIds?.map {
                    gasStationFilter.getGasStationsAndAddressAndPriceAndService(it)
                }
                aux?.forEach { gasStation ->
                    gasStation?.let {
                        _gasStationsFilteredSetAux += mutableSetOf(gasStation)
                    }

                }

                setFilteredList()
            }
        }
    }

    fun setFilteredList(){
        _gasStationsFilteredSet.postValue(_gasStationsFilteredSetAux)
    }*/
}