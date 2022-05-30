package com.example.preconoposto.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Address(
    @PrimaryKey(autoGenerate = true)
    var idAddress: Long = 0L,
    var street: String,
    var number: Int,
    val cep: Int,
    val city: String
)