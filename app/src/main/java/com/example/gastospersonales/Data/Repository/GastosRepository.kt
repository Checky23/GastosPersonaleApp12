
package com.example.gastospersonales.Data.Repository

import com.example.gastospersonales.Data.Model.RegistroDeMovimientos
import com.example.gastospersonales.Data.Room.MovimientoDao
import kotlinx.coroutines.flow.Flow

class MovimientoRepository(
    private val movimientoDao: MovimientoDao
) {

    // Observa los movimientos guardados en Room.
    val movimientos: Flow<List<RegistroDeMovimientos>> =
        movimientoDao.obtenerTodos()

    // Guarda un movimiento nuevo y devuelve su ID generado.
    suspend fun agregarMovimiento(
        movimiento: RegistroDeMovimientos
    ): Long {
        return movimientoDao.insertarYObtenerId(movimiento)
    }

    // Actualiza un movimiento existente.
    suspend fun editarMovimiento(
        id: Int,
        nuevoMovimiento: RegistroDeMovimientos
    ) {
        movimientoDao.actualizar(
            nuevoMovimiento.copy(Id = id)
        )
    }

    // Elimina un movimiento por su ID.
    suspend fun eliminarMovimiento(id: Int) {
        movimientoDao.eliminarPorId(id)
    }
}
