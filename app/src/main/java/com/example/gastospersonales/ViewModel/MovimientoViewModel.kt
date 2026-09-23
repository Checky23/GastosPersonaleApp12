package com.example.gastospersonales.ViewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.gastospersonales.Data.Model.RegistroDeMovimientos

class MovimientoViewModel : ViewModel (){
    val movimientos = mutableStateListOf<RegistroDeMovimientos>()

    fun agregarMovimiento(
        gasto: String,
        descripcion: String,
        monto: String
    ) {

        val icono = when (gasto) {
            "Comida" -> "🍔"
            "Transporte" -> "🚂"
            "Hogar" -> "🏠"
            "Servicios" -> "💡"
            "Ocio" -> "🎮"
            "Otros" -> "🎛️"
            else -> "🎛️"
        }

        val movimiento = RegistroDeMovimientos(
            Gasto = gasto,
            Descripcion = descripcion,
            Iconos = icono,
            Monto = monto.toIntOrNull() ?: 0,
            TipoDeMovimiento = false
        )

        movimientos.add(movimiento)
    }


}