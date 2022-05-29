package com.example.preconoposto.domain

import com.example.preconoposto.data.UserEntity
import com.example.preconoposto.repository.UserRepository

// Classe de dominio

class SignupUserImpl(
    var userRepository: UserRepository
): SignupUser {

    override suspend fun signup(user: UserEntity) {
        userRepository.save(user)
    }

}