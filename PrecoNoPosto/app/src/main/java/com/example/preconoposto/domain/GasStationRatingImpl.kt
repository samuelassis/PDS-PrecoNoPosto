package com.example.preconoposto.domain

import com.example.preconoposto.data.Rating
import com.example.preconoposto.repository.RatingRepository

class GasStationRatingImpl(
    val repository: RatingRepository
) : GasStationRating {

    override suspend fun save(rating: Rating) {
        repository.save(rating)
    }
}