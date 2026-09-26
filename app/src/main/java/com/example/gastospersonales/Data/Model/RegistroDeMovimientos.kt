package com.example.gastospersonales.Data.Model

import java.time.LocalDateTime

data class RegistroDeMovimientos(
    val Id: Int = 0,
    val Gasto: String,
    val Descripcion: String,
    val Iconos: String,
    val Monto: Int,
    val TipoDeMovimiento: Boolean,
    val FechaHora: LocalDateTime = LocalDateTime.now()
)