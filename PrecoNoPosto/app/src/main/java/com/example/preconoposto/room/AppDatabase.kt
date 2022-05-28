package com.example.preconoposto.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.preconoposto.dao.UserDao
import com.example.preconoposto.data.User

@Database(
    entities = [
        User::class
    ], version = 1
)


abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}