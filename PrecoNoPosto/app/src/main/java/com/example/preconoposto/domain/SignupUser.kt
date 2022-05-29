package com.example.preconoposto.domain

import com.example.preconoposto.data.UserEntity

// Porta de entrada
interface SignupUser {
    suspend fun signup(user: UserEntity)
}