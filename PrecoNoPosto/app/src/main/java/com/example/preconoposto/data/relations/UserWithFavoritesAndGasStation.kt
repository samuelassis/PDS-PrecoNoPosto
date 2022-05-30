package com.example.preconoposto.data.relations

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation
import com.example.preconoposto.data.Favorite
import com.example.preconoposto.data.GasStation
import com.example.preconoposto.data.Rating
import com.example.preconoposto.data.User

data class UserWithFavoritesAndGasStation(
    @Embedded
    val user: User,
    @Relation(
        entity = Favorite::class,
        parentColumn = "idUser",
        entityColumn = "idUser"
    )
    val ratings: List<FavoriteAndGasStation>
)

@Entity(primaryKeys = ["idFavorite", "idGasStation"])
data class FavoriteGasStationCrossRef(
    val idFavorite: Long,
    val idGasStation: Long
)

data class FavoriteAndGasStation(
    @Embedded
    val favorite: Favorite,
    @Relation(
        parentColumn = "idFavorite",
        entityColumn = "idGasStation",
        associateBy = Junction(FavoriteGasStationCrossRef::class)
    )
    val gasStation: GasStation
)