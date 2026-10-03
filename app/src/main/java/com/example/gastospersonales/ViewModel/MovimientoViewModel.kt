package com.example.gastospersonales.ViewModel

import androidx.lifecycle.ViewModel
import com.example.gastospersonales.Data.Model.RegistroDeMovimientos
import com.example.gastospersonales.Data.Repository.MovimientoRepository
import java.time.LocalDateTime

class MovimientoViewModel : ViewModel() {

    // Accedemos al Repository para obtener la lista de movimientos.
    val movimientos = MovimientoRepository.movimientos


    // Calculamos el total de ingresos a partir de los movimientos.
    val totalIngresos: Int
        get() = movimientos.value
            .filter { it.TipoDeMovimiento }
            .sumOf { it.Monto }


    // Calculamos el total de gastos a partir de los movimientos.
    val totalGastos: Int
        get() = movimientos.value
            .filter { !it.TipoDeMovimiento }
            .sumOf { it.Monto }


    // Calculamos el saldo restando los gastos a los ingresos.
    val saldo: Int
        get() = totalIngresos - totalGastos


    fun agregarMovimiento(
        movimiento: RegistroDeMovimientos
    ) {

        val nuevoMovimiento = movimiento.copy(
            Iconos = obtenerIcono(movimiento.Gasto),
            FechaHora = LocalDateTime.now()
        )

        MovimientoRepository.agregarMovimiento(
            nuevoMovimiento
        )
    }

    fun editarMovimiento(
        movimiento: RegistroDeMovimientos
    ) {

        val movimientoEditado = movimiento.copy(
            Iconos = obtenerIcono(movimiento.Gasto)
        )

        MovimientoRepository.editarMovimiento(
            movimiento.Id,
            movimientoEditado
        )
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