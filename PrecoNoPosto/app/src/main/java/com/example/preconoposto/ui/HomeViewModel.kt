package com.example.preconoposto.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.preconoposto.data.relations.GasStationAndAddressAndPriceAndService
import com.example.preconoposto.domain.GasStationFiltersImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private var _gasStationsCompleteList:
            MutableLiveData<List<GasStationAndAddressAndPriceAndService?>> = MutableLiveData()

    val gasStationsCompleteList:
            LiveData<List<GasStationAndAddressAndPriceAndService?>> get() = _gasStationsCompleteList

    private var _gasStationsFilteredSet:
            MutableLiveData<MutableSet<GasStationAndAddressAndPriceAndService>> = MutableLiveData()

    val gasStationsFilteredSet:
            LiveData<MutableSet<GasStationAndAddressAndPriceAndService>> get() = _gasStationsFilteredSet

    private var _gasStationsFilteredSetAux:
            MutableSet<GasStationAndAddressAndPriceAndService> = mutableSetOf()

    lateinit var gasStationFilter: GasStationFiltersImpl

    fun setFilteredList(){
        _gasStationsFilteredSet.postValue(_gasStationsFilteredSetAux)
    }

    fun getAllGasStationsAndAddressAndPriceAndService() {
        //if(_gasStationsCompleteList.value.isNullOrEmpty()){
            CoroutineScope(Dispatchers.IO).launch {
                val gasStations =
                    gasStationFilter.getAllGasStationsAndAddressAndPriceAndService()
                _gasStationsCompleteList.postValue(gasStations)
            }
        //}
        //else _gasStationsCompleteList.postValue(_gasStationsCompleteList.value)
    }

    fun getAllGasStationsThatHaveConvenienceStore(){
        gasStationsCompleteList.value?.let {
            _gasStationsFilteredSetAux += gasStationFilter
                .getAllGasStationsThatHaveConvenienceStore(it)
                .filterNotNull()
                .toMutableSet()
        }
    }

    fun getAllGasStationsThatHaveCarWash(){
        gasStationsCompleteList.value?.let {
            _gasStationsFilteredSetAux +=
                gasStationFilter
                    .getAllGasStationsThatHaveCarWash(it)
                    .filterNotNull()
                    .toMutableSet()
        }
    }

    fun getAllGasStationsThatHaveCalibrator(){
        gasStationsCompleteList.value?.let {
            _gasStationsFilteredSetAux +=
                gasStationFilter
                    .getAllGasStationsThatHaveCalibrator(it)
                    .filterNotNull()
                    .toMutableSet()
        }
    }

    fun getAllGasStationsThatHaveOilChange(){
        gasStationsCompleteList.value?.let {
            _gasStationsFilteredSetAux +=
                gasStationFilter
                    .getAllGasStationsThatHaveOilChange(it)
                    .filterNotNull()
                    .toMutableSet()
        }
    }

    fun getAllGasStationsThatHaveTireShop(){
        gasStationsCompleteList.value?.let {
            _gasStationsFilteredSetAux +=
                gasStationFilter
                    .getAllGasStationsThatHaveTireShop(it)
                    .filterNotNull()
                    .toMutableSet()
        }
    }

    fun getAllGasStationsThatHaveRestaurant(){
        gasStationsCompleteList.value?.let {
            _gasStationsFilteredSetAux +=
                gasStationFilter
                    .getAllGasStationsThatHaveRestaurant(it)
                    .filterNotNull()
                    .toMutableSet()
        }
    }

    fun getAllGasStationsThatHaveMechanical(){
        gasStationsCompleteList.value?.let {
            _gasStationsFilteredSetAux +=
                gasStationFilter
                    .getAllGasStationsThatHaveMechanical(it)
                    .filterNotNull()
                    .toMutableSet()
        }
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

    fun getAllUserFavorites(userId: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            gasStationsCompleteList.value?.let {
                val userFavorites = gasStationFilter.getAllUserFavorites(userId)
                val favoriteGasStationIds = userFavorites?.favorites?.map {
                    it.favorite.idGasStation
                }
                val gasStationsCompleteListAux = favoriteGasStationIds?.map {
                    gasStationFilter.getGasStationsAndAddressAndPriceAndService(it)
                } ?: listOf()

                _gasStationsCompleteList.postValue(gasStationsCompleteListAux)
            }
        }
    }

    fun clearGasStationFilter(){
        this._gasStationsFilteredSetAux = mutableSetOf()
    }
}