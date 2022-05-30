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
    val ratings: List<RatingAndUser>
)

@Entity(primaryKeys = ["idRating", "idUser"])
data class RatingUserCrossRef(
    val idRating: Long,
    val idUser: Long
)

data class RatingAndUser(
    @Embedded
    val rating: Rating,
    @Relation(
        parentColumn = "idRating",
        entityColumn = "idUser",
        associateBy = Junction(RatingUserCrossRef::class)
    )
    val user: User
)