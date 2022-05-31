package com.example.preconoposto.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity
data class Price(
    @PrimaryKey(autoGenerate = true)
    val idPrice: Long = 0L,
    val idGasStation: Long,
    val gasolinePrice: Double,
    val alcoholPrice: Double,
    val dieselPrice: Double,
    val lastUpdateDate: Date
)