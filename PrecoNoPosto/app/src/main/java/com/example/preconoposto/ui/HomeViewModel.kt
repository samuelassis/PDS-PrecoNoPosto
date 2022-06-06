package com.example.preconoposto.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.preconoposto.data.relations.GasStationAndAddressAndPriceAndService
import com.example.preconoposto.database.dataStore
import com.example.preconoposto.database.loggedUserIdPreference
import com.example.preconoposto.domain.GasStationFiltersImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
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

    var aux = listOf<GasStationAndAddressAndPriceAndService?>()

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

    fun clearGasStationFilter(){
        this._gasStationsFilteredSetAux = mutableSetOf()
    }
}