package com.example.gastospersonales.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.gastospersonales.Data.Model.UI_EstadoLogin

class InicioDeSesionViewModel: ViewModel()  {

    var uiState by mutableStateOf(UI_EstadoLogin())
        // Estado de inicio de sesión
        private set

    fun cambiarCorreo ( correo:String){
        uiState = uiState.copy(correo = correo)
    }

    fun cambiarContreña ( contraseña:String){
        uiState = uiState.copy(contraseña = contraseña)
    }
}

