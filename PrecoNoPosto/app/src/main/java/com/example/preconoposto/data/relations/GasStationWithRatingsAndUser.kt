package com.example.preconoposto.data.relations

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation
import com.example.preconoposto.data.GasStation
import com.example.preconoposto.data.Rating
import com.example.preconoposto.data.User

data class GasStationWithRatingsAndUser(
    @Embedded
    val gasStation: GasStation,
    @Relation(
        entity = Rating::class,
        parentColumn = "idGasStation",
        entityColumn = "idGasStation"
    )
    val ratings: List<RatingAndUser>?
)

data class RatingAndUser(
    @Embedded
    val rating: Rating,
    @Relation(
        parentColumn = "idUser",
        entityColumn = "idUser",
    )
    val user: User
)