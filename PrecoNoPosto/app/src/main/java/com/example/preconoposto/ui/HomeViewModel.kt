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
            MutableLiveData<Set<GasStationAndAddressAndPriceAndService>> = MutableLiveData()

    val gasStationsFilteredSet:
            LiveData<Set<GasStationAndAddressAndPriceAndService>> get() = _gasStationsFilteredSet

    lateinit var gasStationFilter: GasStationFiltersImpl

    fun getAllGasStationsAndAddressAndPriceAndService() {
        CoroutineScope(Dispatchers.IO).launch {
            gasStationsCompleteList.value?.let {
                _gasStationsCompleteList.postValue(
                    gasStationFilter.getAllGasStationsAndAddressAndPriceAndService()
                )
            }
        }
    }

    fun getAllGasStationsThatHaveConvenienceStore(){
        CoroutineScope(Dispatchers.IO).launch {
            gasStationsCompleteList.value?.let {
                val gasStationsFilteredSetAux =
                    gasStationFilter
                    .getAllGasStationsThatHaveConvenienceStore(it)
                    .filterNotNull()
                    .toMutableSet()

                gasStationsFilteredSetAux += gasStationsFilteredSet.value.orEmpty()

                _gasStationsFilteredSet.postValue(gasStationsFilteredSetAux)
            }
        }
    }

    fun getAllGasStationsThatHaveCarWash(){
        CoroutineScope(Dispatchers.IO).launch {
            gasStationsCompleteList.value?.let {
                val gasStationsFilteredSetAux =
                    gasStationFilter
                        .getAllGasStationsThatHaveCarWash(it)
                        .filterNotNull()
                        .toMutableSet()

                gasStationsFilteredSetAux += gasStationsFilteredSet.value.orEmpty()

                _gasStationsFilteredSet.postValue(gasStationsFilteredSetAux)
            }
        }
    }

    fun getAllGasStationsThatHaveCalibrator(){
        CoroutineScope(Dispatchers.IO).launch {
            gasStationsCompleteList.value?.let {
                val gasStationsFilteredSetAux =
                    gasStationFilter
                        .getAllGasStationsThatHaveCalibrator(it)
                        .filterNotNull()
                        .toMutableSet()

                gasStationsFilteredSetAux += gasStationsFilteredSet.value.orEmpty()

                _gasStationsFilteredSet.postValue(gasStationsFilteredSetAux)
            }
        }
    }

    fun getAllGasStationsThatHaveOilChange(){
        CoroutineScope(Dispatchers.IO).launch {
            gasStationsCompleteList.value?.let {
                val gasStationsFilteredSetAux =
                    gasStationFilter
                        .getAllGasStationsThatHaveOilChange(it)
                        .filterNotNull()
                        .toMutableSet()

                gasStationsFilteredSetAux += gasStationsFilteredSet.value.orEmpty()

                _gasStationsFilteredSet.postValue(gasStationsFilteredSetAux)
            }
        }
    }

    fun getAllGasStationsThatHaveTireShop(){
        CoroutineScope(Dispatchers.IO).launch {
            gasStationsCompleteList.value?.let {
                val gasStationsFilteredSetAux =
                    gasStationFilter
                        .getAllGasStationsThatHaveTireShop(it)
                        .filterNotNull()
                        .toMutableSet()

                gasStationsFilteredSetAux += gasStationsFilteredSet.value.orEmpty()

                _gasStationsFilteredSet.postValue(gasStationsFilteredSetAux)
            }
        }
    }

    fun getAllGasStationsThatHaveRestaurant(){
        CoroutineScope(Dispatchers.IO).launch {
            gasStationsCompleteList.value?.let {
                val gasStationsFilteredSetAux =
                    gasStationFilter
                        .getAllGasStationsThatHaveRestaurant(it)
                        .filterNotNull()
                        .toMutableSet()

                gasStationsFilteredSetAux += gasStationsFilteredSet.value.orEmpty()

                _gasStationsFilteredSet.postValue(gasStationsFilteredSetAux)
            }
        }
    }

    fun getAllGasStationsThatHaveMechanical(){
        CoroutineScope(Dispatchers.IO).launch {
            gasStationsCompleteList.value?.let {
                val gasStationsFilteredSetAux =
                    gasStationFilter
                        .getAllGasStationsThatHaveMechanical(it)
                        .filterNotNull()
                        .toMutableSet()

                gasStationsFilteredSetAux += gasStationsFilteredSet.value.orEmpty()

                _gasStationsFilteredSet.postValue(gasStationsFilteredSetAux)
            }
        }
    }

    fun getAllGasStationsOrderedByGasPrice(){
        CoroutineScope(Dispatchers.IO).launch {
            gasStationsCompleteList.value?.let {
                _gasStationsCompleteList.postValue(
                    gasStationFilter.getAllGasStationsOrderedByGasPrice(it)
                )
            }
        }
    }

    fun getAllGasStationsOrderedByAlcoholPrice(){
        CoroutineScope(Dispatchers.IO).launch {
            gasStationsCompleteList.value?.let {
                _gasStationsCompleteList.postValue(
                    gasStationFilter.getAllGasStationsOrderedByAlcoholPrice(it)
                )
            }
        }
    }

    fun getAllGasStationsOrderedByDieselPrice(){
        CoroutineScope(Dispatchers.IO).launch {
            gasStationsCompleteList.value?.let {
                _gasStationsCompleteList.postValue(
                    gasStationFilter.getAllGasStationsOrderedByDieselPrice(it)
                )
            }
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
}