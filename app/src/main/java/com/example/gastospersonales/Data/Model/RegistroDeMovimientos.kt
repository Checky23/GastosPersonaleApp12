package com.example.gastospersonales.Data.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "movimientos")
data class RegistroDeMovimientos(
    @PrimaryKey(autoGenerate = true)
    val Id: Int = 0,
    val Gasto: String,
    val Descripcion: String,
    val Iconos: String,
    val Monto: Int,
    val TipoDeMovimiento: Boolean,
    val FechaHora: LocalDateTime = LocalDateTime.now()
)