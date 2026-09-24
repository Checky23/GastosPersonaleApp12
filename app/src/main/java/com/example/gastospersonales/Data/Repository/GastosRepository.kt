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
}