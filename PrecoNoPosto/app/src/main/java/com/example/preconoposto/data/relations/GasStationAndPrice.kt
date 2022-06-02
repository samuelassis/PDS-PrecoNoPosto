package com.example.preconoposto.data.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.preconoposto.data.GasStation
import com.example.preconoposto.data.Price

data class GasStationAndPrice(
    @Embedded
    val price: Price?,
    @Relation(
        parentColumn = "idGasStation",
        entityColumn = "idGasStation"
    )
    val gasStation: GasStation
)