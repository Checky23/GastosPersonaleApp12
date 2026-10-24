package com.example.gastospersonales.Data.Room.Convers

import androidx.room.TypeConverter
import java.time.LocalDateTime

class DateConverters {

    @TypeConverter
    fun fromLocalDateTime(fecha: LocalDateTime?): String? {
        return fecha?.toString()
    }

    @TypeConverter
    fun toLocalDateTime(fecha: String?): LocalDateTime? {
        return fecha?.let { LocalDateTime.parse(it) }
    }
}