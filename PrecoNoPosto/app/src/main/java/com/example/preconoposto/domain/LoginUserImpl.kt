package com.example.preconoposto.domain

import com.example.preconoposto.data.UserEntity
import com.example.preconoposto.repository.UserRepository

// Classe de domínio

class LoginUserImpl(
    var repository: UserRepository
): LoginUser {
    override suspend fun login(user: UserEntity): UserEntity? {
        return repository.getUser(user.email, user.password)
    }
}