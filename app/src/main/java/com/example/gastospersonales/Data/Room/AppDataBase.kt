package com.example.gastospersonales.Data.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.gastospersonales.Data.Model.RegistroDeMovimientos
import com.example.gastospersonales.Data.Room.Convers.DateConverters


@Database(
    entities = [RegistroDeMovimientos::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movimientoDao(): MovimientoDao
}