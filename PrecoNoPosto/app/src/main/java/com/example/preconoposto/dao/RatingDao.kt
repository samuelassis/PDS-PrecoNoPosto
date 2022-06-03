package com.example.preconoposto.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.preconoposto.data.Rating
import com.example.preconoposto.repository.RatingRepository

@Dao
interface RatingDao: RatingRepository {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun save(rating: Rating) : Long
}