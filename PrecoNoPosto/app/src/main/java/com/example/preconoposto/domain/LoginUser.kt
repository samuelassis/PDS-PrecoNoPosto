package com.example.preconoposto.domain

import com.example.preconoposto.data.User

interface LoginUser {
    suspend fun login(user: User): User?
}