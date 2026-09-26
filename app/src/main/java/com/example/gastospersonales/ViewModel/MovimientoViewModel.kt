package com.example.gastospersonales.ViewModel

import androidx.lifecycle.ViewModel
import com.example.gastospersonales.Data.Model.RegistroDeMovimientos
import com.example.gastospersonales.Data.Repository.MovimientoRepository
import java.time.LocalDateTime

class MovimientoViewModel : ViewModel() {

    val movimientos = MovimientoRepository.movimientos


    // =========================================================
    // AGREGAR MOVIMIENTO
    // =========================================================

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


    // =========================================================
    // EDITAR MOVIMIENTO
    // =========================================================

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


    // =========================================================
    // ELIMINAR MOVIMIENTO
    // =========================================================

    fun eliminarMovimiento(id: Int) {

        MovimientoRepository.eliminarMovimiento(id)
    }


    // =========================================================
    // OBTENER ICONO
    // =========================================================

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