package com.example.preconoposto.domain

import com.example.preconoposto.data.Rating

interface GasStationRating {
    suspend fun save(rating: Rating)
}