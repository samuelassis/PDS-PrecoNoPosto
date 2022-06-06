package com.example.preconoposto.repository
import com.example.preconoposto.data.GasStation
import com.example.preconoposto.data.relations.GasStationAndAddressAndPriceAndService
import com.example.preconoposto.data.relations.GasStationAndPrice
import com.example.preconoposto.data.relations.GasStationWithRatings
import com.example.preconoposto.data.relations.GasStationWithRatingsAndUser

interface GasStationRepository {
    suspend fun setGasStation(gasStation: GasStation)

    suspend fun getGasStationWithRatings(id: Long): GasStationWithRatings

    suspend fun getGasStationWithRatingsAndUser(id: Long): GasStationWithRatingsAndUser

    suspend fun getAllGasStationsWithRatings(): List<GasStationWithRatings>

    suspend fun getAllGasStationsAndAddressAndPriceAndService(): List<GasStationAndAddressAndPriceAndService>

    suspend fun getGasStationAndPrice(id: Long): GasStationAndPrice

    suspend fun getAllGasStationAndPrice(): List<GasStationAndPrice>

    suspend fun getGasStationsAndAddressAndPriceAndService(id: Long): GasStationAndAddressAndPriceAndService?
}