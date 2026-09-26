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


    // =========================================================
    // AGREGAR MOVIMIENTO
    // =========================================================

    fun agregarMovimiento(movimiento: RegistroDeMovimientos) {

        val nuevoId = if (_movimientos.value.isEmpty()) {
            1
        } else {
            _movimientos.value.maxOf { it.Id } + 1
        }

        val movimientoConId = movimiento.copy(
            Id = nuevoId
        )

        _movimientos.update { listaActual ->
            listaActual + movimientoConId
        }
    }


    // =========================================================
    // ELIMINAR MOVIMIENTO
    // =========================================================

    fun eliminarMovimiento(id: Int) {

        _movimientos.update { listaActual ->
            listaActual.filterNot {
                it.Id == id
            }
        }
    }


    // =========================================================
    // EDITAR MOVIMIENTO
    // =========================================================

    fun editarMovimiento(
        id: Int,
        nuevoMovimiento: RegistroDeMovimientos
    ) {

        _movimientos.update { listaActual ->

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