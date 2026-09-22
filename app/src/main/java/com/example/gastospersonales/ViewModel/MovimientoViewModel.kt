package com.example.gastospersonales.ViewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.gastospersonales.Data.Model.RegistroDeMovimientos

class MovimientoViewModel : ViewModel (){
    val movimientos = mutableStateListOf<RegistroDeMovimientos>()

    fun agregarMovimiento(movimiento: RegistroDeMovimientos) {
        movimientos.add(movimiento)
    }


}