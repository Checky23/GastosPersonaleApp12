package com.example.gastospersonales.Data.Repository

import com.example.gastospersonales.Data.Model.RegistroDeMovimientos
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


object MovimientoRepository {

    // Estado privado que contiene la lista de movimientos y solo puede modificarse desde el Repository.
    private val _movimientos = MutableStateFlow<List<RegistroDeMovimientos>>(emptyList())

    // Estado de solo lectura que permite observar los movimientos desde fuera del Repository.
    val movimientos: StateFlow<List<RegistroDeMovimientos>> = _movimientos.asStateFlow()


    fun agregarMovimiento(movimiento: RegistroDeMovimientos) {

        val nuevoId = if (_movimientos.value.isEmpty()) {
            1
        } else {
            _movimientos.value.maxOf { it.Id } + 1
        }

        val movimientoConId = movimiento.copy(
            Id = nuevoId
        )

        // Agregamos el objeto a la lista.
        _movimientos.update { listaActual ->
            listaActual + movimientoConId
        }
    }


    // Eliminamos el movimiento cual  ID coincida con el recibido.
    fun eliminarMovimiento(id: Int) {

        _movimientos.update { listaActual ->
            listaActual.filterNot {
                it.Id == id
            }
        }
    }


    fun editarMovimiento(
        id: Int,
        nuevoMovimiento: RegistroDeMovimientos
    ) {

        _movimientos.update { listaActual ->

            // Recorremos la lista y reemplazamos el movimiento que coincida con el ID.
            listaActual.map { itemExistente ->

                if (itemExistente.Id == id) {
                    nuevoMovimiento.copy(Id = id)
                } else {
                    itemExistente
                }
            }
        }
    }
}