package com.example.preconoposto.repository

import com.example.preconoposto.data.UserEntity

// Porta de saida
interface UserRepository {
    suspend fun save(user: UserEntity)
    suspend fun delete(user: UserEntity)
    suspend fun update(user: UserEntity)
    suspend fun getUser(email: String, password: String): UserEntity?
}