package com.example.preconoposto.repository

import com.example.preconoposto.data.User

// Porta de saida
interface UserRepository {
    suspend fun save(user: User) : Long
    suspend fun delete(user: User)
    suspend fun update(user: User)
    suspend fun getUser(email: String, password: String): User?
}