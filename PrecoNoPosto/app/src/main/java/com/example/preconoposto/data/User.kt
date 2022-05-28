package com.example.preconoposto.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name = "user")
    var email: String,
    var password: String,
    var name: String,
    var surname: String,
    var birthDate: String
)
