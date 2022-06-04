package com.example.preconoposto.data.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.preconoposto.data.Favorite
import com.example.preconoposto.data.GasStation
import com.example.preconoposto.data.User

data class UserWithFavoritesAndGasStation(
    @Embedded
    val user: User,
    @Relation(
        entity = Favorite::class,
        parentColumn = "idUser",
        entityColumn = "idUser"
    )
    val favorites: List<FavoriteAndGasStation>
)

data class FavoriteAndGasStation(
    @Embedded
    val favorite: Favorite,
    @Relation(
        parentColumn = "idGasStation",
        entityColumn = "idGasStation",
    )
    val gasStation: GasStation
)