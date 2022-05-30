package com.example.preconoposto.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Service(
    @PrimaryKey(autoGenerate = true)
    val idService: Long = 0L,
    val idGasStation: Long,
    val hasConvenienceStore: Boolean,
    val hasCarWash: Boolean,
    val hasCalibrator: Boolean,
    val hasOilChange: Boolean,
    val hasTireShop: Boolean,
    val hasRestaurant: Boolean,
    val hasMechanical: Boolean
)