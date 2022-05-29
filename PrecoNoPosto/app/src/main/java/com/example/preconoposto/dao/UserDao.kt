package com.example.preconoposto.dao

import androidx.room.*
import com.example.preconoposto.data.UserEntity
import com.example.preconoposto.repository.UserRepository

// Adaptador de saída
@Dao
interface UserDao: UserRepository {

    @Delete
    override suspend fun delete(user: UserEntity)

    @Insert
    override suspend fun save(user: UserEntity)

    @Update
    override suspend fun update(user: UserEntity)

    @Query("SELECT * FROM user_table WHERE email = :email AND password = :password")
    override suspend fun getUser(email: String, password: String): UserEntity?
}
