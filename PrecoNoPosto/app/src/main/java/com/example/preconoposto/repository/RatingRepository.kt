package com.example.preconoposto.repository

import com.example.preconoposto.data.Rating

interface RatingRepository {
    suspend fun save(rating: Rating): Long
}