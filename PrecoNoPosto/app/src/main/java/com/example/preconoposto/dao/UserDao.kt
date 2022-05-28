package com.example.preconoposto.dao

import androidx.room.*
import com.example.preconoposto.data.User
import com.example.preconoposto.repository.UserRepository

// Adaptador de saída
@Dao
interface UserDao: UserRepository {

    @Delete
    override suspend fun delete(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE) //Substitui o objeto caso haja confluto
    override suspend fun save(user: User)

    @Update
    override suspend fun update(user: User)
}
