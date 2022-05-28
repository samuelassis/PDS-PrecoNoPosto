package com.example.preconoposto.repository

import com.example.preconoposto.dao.UserDao
import com.example.preconoposto.data.User

class UserRepositoryImpl(
    private val userDao: UserDao
) : UserRepository {
    override suspend fun save(user: User) {
        userDao.save(user)
    }

    override suspend fun delete(user: User) {
        userDao.delete(user)
    }

    override suspend fun update(user: User) {
        userDao.update(user)
    }
}