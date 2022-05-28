package com.example.preconoposto.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.preconoposto.dao.UserDao
import com.example.preconoposto.data.User

@Database(
    entities = [
        User::class
    ], version = 1
)
abstract class UserDatabase : RoomDatabase() {
    abstract val userDao: UserDao

    companion object {
        // para manter nossa instancia
        // o UserDatabase vai funcionar como uma especie de singleton. Todas as opreações devem ser direcionadas para a mesma instancia de database

        @Volatile // para acessar somente pela memória principal, e nunca pelo cache
        private var INSTANCE: UserDatabase? = null

        fun getInstance(context: Context): UserDatabase { // função para pegar uma instancia caso exista uma. Caso não exista, criará uma nova instancia
            synchronized(this) { // para que todos os chamados do database entrem em uma fila única, ou seja, mesmo que tenhamos varios pontos querendo
                // acessar a database, esses pontos serão colocados numa fila e tratados em sequencia, evitando problemas de inconsistência de dados
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        UserDatabase::class.java,
                        "user_db" //nome arbitrário
                    )
                        .fallbackToDestructiveMigration() //caso mude a versão do database, fará uma migração destrutiva, isto é, apagará os dados e criará um database novo
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }

    }
}