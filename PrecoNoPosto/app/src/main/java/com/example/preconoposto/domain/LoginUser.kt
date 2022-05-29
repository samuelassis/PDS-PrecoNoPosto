package com.example.preconoposto.domain

import com.example.preconoposto.data.UserEntity

interface LoginUser {
    suspend fun login(user: UserEntity): UserEntity?
}