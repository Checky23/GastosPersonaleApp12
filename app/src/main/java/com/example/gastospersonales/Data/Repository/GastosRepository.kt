package com.example.gastospersonales.Data.Repository

import com.example.gastospersonales.Data.Model.RegistroDeMovimientos
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

object MovimientoRepository {

    private val _movimientos =
        MutableStateFlow<List<RegistroDeMovimientos>>(emptyList())

    val movimientos: StateFlow<List<RegistroDeMovimientos>> =
        _movimientos.asStateFlow()

    fun agregarMovimiento(movimiento: RegistroDeMovimientos) {

        _movimientos.update { listaActual ->
            listaActual + movimiento
        }
    }

    // Se elimina el movimiento filtrando por ID
    fun eliminarMovimiento(id: Int) {
        _movimientos.update { listaActual ->
            // Se crea una lista nueva sin el movimiento que tenga este ID
            listaActual.filterNot { it.Id == id }
        }
    }


    // Editamos el movimiento mediante un map por su id y lo reemplazamos por el nuevo
    fun editarMovimiento(id: Int, nuevoMovimiento: RegistroDeMovimientos) {
        _movimientos.update { listaActual ->
            listaActual.map { itemExistente ->
                // Si encontramos el id lo reemplazamos por el nuevo. Si no, lo dejamos igual.
                if (itemExistente.Id == id) nuevoMovimiento.copy(Id = id) else itemExistente
            }
        }
    }
}