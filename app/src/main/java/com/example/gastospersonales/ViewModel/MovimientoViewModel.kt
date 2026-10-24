
package com.example.gastospersonales.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.gastospersonales.Data.Model.RegistroDeMovimientos
import com.example.gastospersonales.Data.Repository.MovimientoRepository
import com.example.gastospersonales.Data.Room.RoomProvider
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class MovimientoViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val dao = RoomProvider
        .obtenerBaseDeDatos(application)
        .movimientoDao()

    private val repository = MovimientoRepository(dao)

    // Conservamos el nombre y exponemos los datos como StateFlow.
    val movimientos: StateFlow<List<RegistroDeMovimientos>> =
        repository.movimientos.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val totalIngresos: Int
        get() = movimientos.value
            .filter { it.TipoDeMovimiento }
            .sumOf { it.Monto }

    val totalGastos: Int
        get() = movimientos.value
            .filter { !it.TipoDeMovimiento }
            .sumOf { it.Monto }

    val saldo: Int
        get() = totalIngresos - totalGastos

    fun agregarMovimiento(movimiento: RegistroDeMovimientos) {
        val nuevoMovimiento = movimiento.copy(
            Id = 0,
            Iconos = obtenerIcono(movimiento.Gasto),
            FechaHora = LocalDateTime.now()
        )

        viewModelScope.launch {
            repository.agregarMovimiento(nuevoMovimiento)
        }
    }

    fun editarMovimiento(movimiento: RegistroDeMovimientos) {
        val movimientoEditado = movimiento.copy(
            Iconos = obtenerIcono(movimiento.Gasto)
        )

        viewModelScope.launch {
            repository.editarMovimiento(
                movimientoEditado.Id,
                movimientoEditado
            )
        }
    }

    fun eliminarMovimiento(id: Int) {
        viewModelScope.launch {
            repository.eliminarMovimiento(id)
        }
    }

    private fun obtenerIcono(gasto: String): String = when (gasto) {
        "Comida" -> "🍔"
        "Transporte" -> "🚂"
        "Hogar" -> "🏠"
        "Servicios" -> "💡"
        "Ocio" -> "🎮"
        "Otros" -> "🎛️"
        else -> "🎛️"
    }
}
