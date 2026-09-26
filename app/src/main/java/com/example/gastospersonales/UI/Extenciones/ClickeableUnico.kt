package com.example.gastospersonales.UI.Extenciones

import androidx.compose.foundation.clickable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

fun Modifier.clickableUnico(
    tiempoEspera: Long = 0, // Medio segundo de bloqueo (suficiente para que termine la animación)
    onClick: () -> Unit
): Modifier = composed {
    var ultimoClick by remember { mutableLongStateOf(0L) }

    this.clickable {
        val tiempoActual = System.currentTimeMillis()
        if (tiempoActual - ultimoClick > tiempoEspera) {
            ultimoClick = tiempoActual
            onClick()
        }
    }
}