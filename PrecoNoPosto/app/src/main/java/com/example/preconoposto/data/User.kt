package com.example.preconoposto.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    var idUser: Long = 0L,
    var email: String,
    var password: String,
    var name: String,
    var birthDate: String
)