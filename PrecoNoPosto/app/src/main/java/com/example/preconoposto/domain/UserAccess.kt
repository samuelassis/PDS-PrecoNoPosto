package com.example.preconoposto.domain

import com.example.preconoposto.data.User

interface UserAccess {
    suspend fun login(user: User): User?

    suspend fun signup(user: User) : Long
}