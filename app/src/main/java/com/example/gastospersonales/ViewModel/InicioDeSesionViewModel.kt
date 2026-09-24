package com.example.gastospersonales.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gastospersonales.Data.Model.UI_EstadoLogin
import com.example.gastospersonales.Data.Repository.AuthRepository
import kotlinx.coroutines.launch

class InicioDeSesionViewModel: ViewModel()  {

    private val repository = AuthRepository()
    var uiState by mutableStateOf(UI_EstadoLogin())
        // Estado de inicio de sesión
        private set
    fun cambiarCorreo ( correo:String){
        uiState = uiState.copy(correo = correo)
    }
    fun cambiarContreña ( nuevacontraseña:String){
        uiState = uiState.copy(contraseña = nuevacontraseña)
    }

    fun iniciarSesion(){
        viewModelScope.launch {

            uiState = uiState.copy(
                cargando = true,
                error = null
            )
            try {
                repository.iniciarSesionConCorreo(correo = uiState.correo, contrasena = uiState.contraseña)

                uiState = uiState.copy(
                    cargando = false,
                    iniciadoSesion = true
                )

            }catch (e: Exception){

                uiState = uiState.copy(
                    cargando = false,
                    error = e.localizedMessage
                        ?: "Error al iniciar sesión"
                )
            }

        }
    }



}

