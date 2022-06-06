package com.example.preconoposto.dao

import androidx.room.*
import com.example.preconoposto.data.Favorite
import com.example.preconoposto.data.relations.UserWithFavoritesAndGasStation
import com.example.preconoposto.repository.FavoriteRepository

@Dao
interface FavoriteDao: FavoriteRepository {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun save(favorite: Favorite)

    @Delete
    override suspend fun delete(favorite: Favorite)

    @Query("SELECT * FROM favorite WHERE idUser=:userId AND idGasStation=:gasStationId")
    override suspend fun getFavoriteByUserIdAndGasStationId(userId: Long, gasStationId: Long): Favorite
}