package com.example.preconoposto.data.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.preconoposto.data.Address
import com.example.preconoposto.data.GasStation
import com.example.preconoposto.data.Price
import com.example.preconoposto.data.Service

data class GasStationAndAddressAndPriceAndService(
    @Embedded
    val gasStation: GasStation,
    @Relation(
        parentColumn = "idAddress",
        entityColumn = "idAddress"
    )
    val address: Address,
    @Relation(
        parentColumn = "idPrice",
        entityColumn = "idPrice"
    )
    val price: Price,
    @Relation(
        parentColumn = "idService",
        entityColumn = "idService"
    )
    val service: Service,
)