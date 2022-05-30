package com.example.preconoposto.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.preconoposto.data.GasStation
import com.example.preconoposto.data.Service
import com.example.preconoposto.data.relations.GasStationAndAddressAndPriceAndService
import com.example.preconoposto.data.relations.GasStationWithRatings
import com.example.preconoposto.data.relations.GasStationWithRatingsAndUser

@Dao
interface GasStationDao {
    @Transaction
    @Query("SELECT * FROM GasStation WHERE idGasStation == :id")
    suspend fun getGasStationWithRatings(id: Long): GasStationWithRatings

    @Transaction
    @Query("SELECT * FROM GasStation WHERE idGasStation == :id")
    suspend fun getGasStationWithRatingsAndUser(id: Long): GasStationWithRatingsAndUser

    @Transaction
    @Query("SELECT * FROM GasStation")
    suspend fun getAllGasStationsWithRatings(): List<GasStationWithRatings>

    @Query("SELECT * FROM GasStation")
    suspend fun getAllGasStationsAndAddressAndPriceAndService(): List<GasStationAndAddressAndPriceAndService>
}