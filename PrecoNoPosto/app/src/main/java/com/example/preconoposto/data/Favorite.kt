package com.example.preconoposto.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Favorite(
    @PrimaryKey(autoGenerate = true)
    val idFavorite: Long = 0L,
    val idUser: Long,
    val idGasStation: Long
)