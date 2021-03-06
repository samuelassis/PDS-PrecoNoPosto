package com.example.preconoposto.dao

import androidx.room.*
import com.example.preconoposto.data.User
import com.example.preconoposto.data.relations.UserWithFavoritesAndGasStation
import com.example.preconoposto.repository.UserRepository

// Adaptador de saída
@Dao
interface UserDao: UserRepository {

    @Delete
    override suspend fun delete(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun save(user: User) : Long

    @Update
    override suspend fun update(user: User)

    @Query("SELECT * FROM user WHERE email = :email AND password = :password")
    override suspend fun getUser(email: String, password: String): User?

    @Transaction
    @Query("SELECT * FROM user WHERE idUser = :id")
    override suspend fun getFavorites(id: Long): UserWithFavoritesAndGasStation?
}
