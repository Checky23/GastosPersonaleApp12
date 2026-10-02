package com.example.gastospersonales.ViewModel

import androidx.lifecycle.ViewModel
import com.example.gastospersonales.Data.Model.RegistroDeMovimientos
import com.example.gastospersonales.Data.Repository.MovimientoRepository
import java.time.LocalDateTime

class MovimientoViewModel : ViewModel() {


    // Accedemos al Repositoy para obtener la lista de movimientos desde el ViewModel.
    val movimientos = MovimientoRepository.movimientos
    fun agregarMovimiento(
        gasto: String,
        descripcion: String,
        monto: String,
        tipoDeMovimiento: Boolean
    ) {

        val movimiento = RegistroDeMovimientos(
            Gasto = gasto,
            Descripcion = descripcion,
            Iconos = obtenerIcono(gasto),
            Monto = monto.toIntOrNull() ?: 0,
            TipoDeMovimiento = tipoDeMovimiento,
            FechaHora = LocalDateTime.now()
        )

        MovimientoRepository.agregarMovimiento(movimiento)
    }


    fun editarMovimiento(
        id: Int,
        gasto: String,
        descripcion: String,
        monto: String,
        tipoDeMovimiento: Boolean,
        fechaHoraOriginal: LocalDateTime
    ) {

        val movimientoEditado = RegistroDeMovimientos(
            Id = id,
            Gasto = gasto,
            Descripcion = descripcion,
            Iconos = obtenerIcono(gasto),
            Monto = monto.toIntOrNull() ?: 0,
            TipoDeMovimiento = tipoDeMovimiento,
            FechaHora = fechaHoraOriginal
        )

        MovimientoRepository.editarMovimiento(
            id,
            movimientoEditado
        )
    }

    fun obtenerMovimiento(id: Int): RegistroDeMovimientos? {
        return movimientos.value.find { it.Id == id }
    }

    fun eliminarMovimiento(id: Int) {

        MovimientoRepository.eliminarMovimiento(id)
    }


    private fun obtenerIcono(gasto: String): String {

        return when (gasto) {

            "Comida" -> "🍔"
            "Transporte" -> "🚂"
            "Hogar" -> "🏠"
            "Servicios" -> "💡"
            "Ocio" -> "🎮"
            "Otros" -> "🎛️"

            else -> "🎛️"
        }
    }
}