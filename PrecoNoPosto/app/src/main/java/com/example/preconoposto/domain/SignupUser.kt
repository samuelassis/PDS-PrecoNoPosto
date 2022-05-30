package com.example.preconoposto.domain

import com.example.preconoposto.data.User

// Porta de entrada
interface SignupUser {
    suspend fun signup(user: User)
}