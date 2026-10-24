package com.example.gastospersonales.Data.Room


import android.content.Context
import androidx.room.Room

object RoomProvider {

    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun obtenerBaseDeDatos(context: Context): AppDatabase {
        return INSTANCE ?: synchronized(this) {
            INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "gastos_personales_db"
            ).build().also { INSTANCE = it }
        }
    }
}
