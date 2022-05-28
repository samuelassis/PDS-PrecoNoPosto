package com.example.preconoposto.domain

import com.example.preconoposto.data.User
import com.example.preconoposto.repository.UserRepository

// Classe de domínio

class LoginUserImpl(
    var repository: UserRepository
): LoginUser {
    override suspend fun login(user: User): User? {
        return repository.getUser(user.email, user.password)
    }
}