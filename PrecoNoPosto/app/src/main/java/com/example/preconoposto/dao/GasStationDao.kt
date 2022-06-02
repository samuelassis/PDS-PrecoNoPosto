package com.example.preconoposto.dao

import androidx.room.*
import com.example.preconoposto.data.GasStation
import com.example.preconoposto.data.Service
import com.example.preconoposto.data.relations.GasStationAndAddressAndPriceAndService
import com.example.preconoposto.data.relations.GasStationAndPrice
import com.example.preconoposto.data.relations.GasStationWithRatings
import com.example.preconoposto.data.relations.GasStationWithRatingsAndUser

@Dao
interface GasStationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setGasStation(gasStation: GasStation)

    @Transaction
    @Query("SELECT * FROM GasStation WHERE idGasStation == :id")
    suspend fun getGasStationWithRatings(id: Long): GasStationWithRatings

    @Transaction
    @Query("SELECT * FROM GasStation WHERE idGasStation == :id")
    suspend fun getGasStationWithRatingsAndUser(id: Long): GasStationWithRatingsAndUser

    @Transaction
    @Query("SELECT * FROM GasStation")
    suspend fun getAllGasStationsWithRatings(): List<GasStationWithRatings>

    @Transaction
    @Query("SELECT * FROM GasStation")
    suspend fun getAllGasStationsAndAddressAndPriceAndService(): List<GasStationAndAddressAndPriceAndService>

    @Transaction
    @Query("SELECT * FROM GasStation INNER JOIN Price WHERE GasStation.idGasStation == Price.idGasStation AND GasStation.idGasStation == :id")
    suspend fun getGasStationAndPrice(id: Long): GasStationAndPrice
}