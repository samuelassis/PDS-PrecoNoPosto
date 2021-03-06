package com.example.preconoposto.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.preconoposto.data.relations.GasStationAndAddressAndPriceAndService
import com.example.preconoposto.domain.GasStationFiltersImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    var _gasStationsCompleteList:
            MutableLiveData<List<GasStationAndAddressAndPriceAndService?>> = MutableLiveData()

    val gasStationsCompleteList: LiveData<List<GasStationAndAddressAndPriceAndService?>> get() = _gasStationsCompleteList

    var _gasStationsFilteredSet: MutableLiveData<MutableSet<GasStationAndAddressAndPriceAndService>> = MutableLiveData()

    val gasStationsFilteredSet: LiveData<MutableSet<GasStationAndAddressAndPriceAndService>> get() = _gasStationsFilteredSet

    var _gasStationsFilteredSetAux:
            MutableSet<GasStationAndAddressAndPriceAndService> = mutableSetOf()

    var aux = listOf<GasStationAndAddressAndPriceAndService?>()

    lateinit var gasStationFilter: GasStationFiltersImpl

    fun setFilteredList(){
        _gasStationsFilteredSet.postValue(_gasStationsFilteredSetAux)
    }
    fun getAllGasStationsAndAddressAndPriceAndService() {
        CoroutineScope(Dispatchers.IO).launch {
            val gasStations =
                gasStationFilter.getAllGasStationsAndAddressAndPriceAndService()
            _gasStationsCompleteList.postValue(gasStations)
        }
    }
    fun getAllGasStationsThatHaveConvenienceStore(){
        gasStationsCompleteList.value?.let {
            _gasStationsFilteredSetAux = gasStationFilter
                .getAllGasStationsThatHaveConvenienceStore(aux)
                .filterNotNull()
                .toMutableSet()
        }
        aux = _gasStationsFilteredSetAux.toList()
    }
    fun getAllGasStationsThatHaveCarWash(){
        gasStationsCompleteList.value?.let {
            _gasStationsFilteredSetAux =
                gasStationFilter
                    .getAllGasStationsThatHaveCarWash(aux)
                    .filterNotNull()
                    .toMutableSet()
        }
        aux = _gasStationsFilteredSetAux.toList()
    }
    fun getAllGasStationsThatHaveCalibrator(){
        gasStationsCompleteList.value?.let {
            _gasStationsFilteredSetAux =
                gasStationFilter
                    .getAllGasStationsThatHaveCalibrator(aux)
                    .filterNotNull()
                    .toMutableSet()
        }
        aux = _gasStationsFilteredSetAux.toList()
    }
    fun getAllGasStationsThatHaveOilChange(){
        gasStationsCompleteList.value?.let {
            _gasStationsFilteredSetAux =
                gasStationFilter
                    .getAllGasStationsThatHaveOilChange(aux)
                    .filterNotNull()
                    .toMutableSet()
        }
        aux = _gasStationsFilteredSetAux.toList()
    }
    fun getAllGasStationsThatHaveTireShop(){
        gasStationsCompleteList.value?.let {
            _gasStationsFilteredSetAux =
                gasStationFilter
                    .getAllGasStationsThatHaveTireShop(aux)
                    .filterNotNull()
                    .toMutableSet()
        }
        aux = _gasStationsFilteredSetAux.toList()
    }
    fun getAllGasStationsThatHaveRestaurant(){
        gasStationsCompleteList.value?.let {
            _gasStationsFilteredSetAux =
                gasStationFilter
                    .getAllGasStationsThatHaveRestaurant(aux)
                    .filterNotNull()
                    .toMutableSet()
        }
        aux = _gasStationsFilteredSetAux.toList()
    }
    fun getAllGasStationsThatHaveMechanical(){
        gasStationsCompleteList.value?.let {
            _gasStationsFilteredSetAux =
                gasStationFilter
                    .getAllGasStationsThatHaveMechanical(aux)
                    .filterNotNull()
                    .toMutableSet()
        }
        aux = _gasStationsFilteredSetAux.toList()
    }
    fun clearGasStationFilter(){
        this._gasStationsFilteredSetAux = mutableSetOf()
    }
}