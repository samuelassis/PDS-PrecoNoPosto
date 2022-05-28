package com.example.preconoposto.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    var idUser: Long = 0L,
    var name: String,
    var surname: String,
    var email: String,
    var password: String,
    var city: String?
)