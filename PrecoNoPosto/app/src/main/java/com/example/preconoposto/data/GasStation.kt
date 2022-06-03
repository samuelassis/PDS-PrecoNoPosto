package com.example.preconoposto.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GasStation(
    @PrimaryKey(autoGenerate = true)
    val idGasStation: Long = 0L,
    val idAddress: Long,
    val idService: Long,
    val idRating: Long,
    val idPrice: Long,
    val name: String
)