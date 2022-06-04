package com.example.preconoposto.dao

import androidx.room.*
import com.example.preconoposto.data.GasStation
import com.example.preconoposto.data.relations.GasStationAndAddressAndPriceAndService
import com.example.preconoposto.data.relations.GasStationAndPrice
import com.example.preconoposto.data.relations.GasStationWithRatings
import com.example.preconoposto.data.relations.GasStationWithRatingsAndUser
import com.example.preconoposto.repository.GasStationRepository

@Dao
interface GasStationDao: GasStationRepository {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun setGasStation(gasStation: GasStation)

    @Transaction
    @Query("SELECT * FROM GasStation WHERE idGasStation == :id")
    override suspend fun getGasStationWithRatings(id: Long): GasStationWithRatings

    @Transaction
    @Query("SELECT * FROM GasStation WHERE idGasStation == :id")
    override suspend fun getGasStationWithRatingsAndUser(id: Long): GasStationWithRatingsAndUser

    @Transaction
    @Query("SELECT * FROM GasStation")
    override suspend fun getAllGasStationsWithRatings(): List<GasStationWithRatings>

    @Transaction
    @Query("SELECT * FROM GasStation")
    override suspend fun getAllGasStationsAndAddressAndPriceAndService(): List<GasStationAndAddressAndPriceAndService>

    @Transaction
    @Query("SELECT * FROM GasStation WHERE idGasStation == :id")
    override suspend fun getGasStationsAndAddressAndPriceAndService(id: Long): GasStationAndAddressAndPriceAndService?

    @Transaction
    @Query("SELECT * FROM GasStation INNER JOIN Price WHERE GasStation.idGasStation == Price.idGasStation AND GasStation.idGasStation == :id")
    override suspend fun getGasStationAndPrice(id: Long): GasStationAndPrice
}