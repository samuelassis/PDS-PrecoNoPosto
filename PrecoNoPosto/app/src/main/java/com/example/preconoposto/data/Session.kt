package com.example.preconoposto.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Session(
    @PrimaryKey(autoGenerate = true)
    val idSession: Long = 0L,
    val idUser: Long,
    val isActive: Boolean
)