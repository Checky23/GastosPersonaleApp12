package com.example.gastospersonales.ViewModel

import androidx.lifecycle.ViewModel
import com.example.gastospersonales.Data.Model.RegistroDeMovimientos
import com.example.gastospersonales.Data.Repository.MovimientoRepository

class MovimientoViewModel : ViewModel() {

    val movimientos = MovimientoRepository.movimientos

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

        MovimientoRepository.agregarMovimiento(movimiento)
    }

    fun eliminarMovimiento(id: Int) {
        MovimientoRepository.eliminarMovimiento(id)
    }


}