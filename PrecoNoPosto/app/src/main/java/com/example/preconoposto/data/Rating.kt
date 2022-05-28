package com.example.preconoposto.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Rating(
    @PrimaryKey
    val idRating: Long = 0L,
    val idGasStation: Long,
    val idUser: Long,
    val generalScore: Double,
    val attendanceScore: Double,
    val qualityScore: Double,
    val waitingTimeScore: Double,
    val serviceScore: Double,
    val safetyScore: Double,
    val commentary: String
)
