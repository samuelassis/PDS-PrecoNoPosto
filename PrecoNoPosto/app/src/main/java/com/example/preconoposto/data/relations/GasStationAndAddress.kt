package com.example.preconoposto.data.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.preconoposto.data.Address
import com.example.preconoposto.data.GasStation

data class GasStationAndAddress(
    @Embedded
    val address: Address,
    @Relation(
        parentColumn = "idAddress",
        entityColumn = "idAddress"
    )
    val gasStation: GasStation
)