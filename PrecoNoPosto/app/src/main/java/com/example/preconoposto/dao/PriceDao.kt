package com.example.preconoposto.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.preconoposto.data.Price

@Dao
interface PriceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setPrice(price: Price)
}