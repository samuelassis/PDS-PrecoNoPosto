package com.example.preconoposto.data.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.preconoposto.data.GasStation
import com.example.preconoposto.data.Rating

data class GasStationWithRatings(
    @Embedded
    val gasStation: GasStation,
    @Relation(
        parentColumn = "idGasStation",
        entityColumn = "idGasStation"
    )
    val ratings: List<Rating>
)