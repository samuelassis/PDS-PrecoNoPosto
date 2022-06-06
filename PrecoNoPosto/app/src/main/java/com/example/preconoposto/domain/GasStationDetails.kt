package com.example.preconoposto.domain

import com.example.preconoposto.data.Favorite
import com.example.preconoposto.data.GasStation
import com.example.preconoposto.data.relations.GasStationAndPrice
import com.example.preconoposto.data.relations.GasStationWithRatingsAndUser

interface GasStationDetails {
    suspend fun getGasStationWithRatingsAndUser(id: Long): GasStationWithRatingsAndUser

    suspend fun getGasStationAndPrice(id: Long): GasStationAndPrice

    suspend fun getScoreAverageTexts(id: Long): Map<String, String>

    suspend fun getPriceTexts(id: Long): Map<String, String>

    suspend fun saveFavorite(favorite: Favorite)

    suspend fun deleteFavorite(userId: Long, gasStationId: Long)
}