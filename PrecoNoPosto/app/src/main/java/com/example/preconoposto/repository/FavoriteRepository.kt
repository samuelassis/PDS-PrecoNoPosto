package com.example.preconoposto.repository

import com.example.preconoposto.data.Favorite

interface FavoriteRepository {
    suspend fun save(favorite: Favorite)
    suspend fun delete(favorite: Favorite)
    suspend fun getFavoriteByUserIdAndGasStationId(userId: Long, gasStationId: Long): Favorite
}