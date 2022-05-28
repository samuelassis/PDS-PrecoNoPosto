package com.example.preconoposto.domain

import com.example.preconoposto.data.User
import com.example.preconoposto.repository.UserRepository
import com.example.preconoposto.repository.UserRepositoryImpl

// Classe de dominio

class SignupUserImpl(
    var userRepository: UserRepositoryImpl
): SignupUser {

    override suspend fun signup(user: User){
        userRepository.save(user)
    }

}