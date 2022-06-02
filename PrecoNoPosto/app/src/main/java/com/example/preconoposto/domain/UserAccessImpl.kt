package com.example.preconoposto.domain

import com.example.preconoposto.data.User
import com.example.preconoposto.repository.UserRepository

class UserAccessImpl(
    var repository: UserRepository
): UserAccess {
    override suspend fun login(user: User): User? {
        return repository.getUser(user.email, user.password)
    }

    override suspend fun signup(user: User) : Long {
        return repository.save(user)
    }
}