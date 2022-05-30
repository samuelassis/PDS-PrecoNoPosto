package com.example.preconoposto.data.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.preconoposto.data.Service
import com.example.preconoposto.data.GasStation

data class GasStationAndService(
    @Embedded
    val service: Service,
    @Relation(
        parentColumn = "idService",
        entityColumn = "idService"
    )
    val gasStation: GasStation
)
