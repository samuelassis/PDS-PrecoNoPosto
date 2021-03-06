package com.example.preconoposto.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.preconoposto.dao.*
import com.example.preconoposto.data.*

@Database(
    entities = [
        Address::class,
        Favorite::class,
        GasStation::class,
        Price::class,
        Rating::class,
        Service::class,
        Session::class,
        User::class
    ],
    version = 11
)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract val userDao: UserDao
    abstract val gasStationDao: GasStationDao
    abstract val priceDao: PriceDao
    abstract val ratingDao: RatingDao
    abstract val addressDao: AddressDao
    abstract val serviceDao: ServiceDao
    abstract val favoriteDao: FavoriteDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context) : AppDatabase {
            synchronized(this){
                var instance = INSTANCE
                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app_db"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}