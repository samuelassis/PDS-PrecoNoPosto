package com.example.preconoposto.data.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.preconoposto.data.Rating
import com.example.preconoposto.data.User

data class UserWithRatings(
    @Embedded
    val user: User,
    @Relation(
        parentColumn = "idUser",
        entityColumn = "idUser"
    )
    val ratings: List<Rating>
)