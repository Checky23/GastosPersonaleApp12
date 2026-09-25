package com.example.gastospersonales.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gastospersonales.Data.Model.UI_EstadoRegistro
import com.example.gastospersonales.Data.Repository.AuthRepository
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import kotlinx.coroutines.launch

class RegistroViewModel : ViewModel() {


    private val repository = AuthRepository()
    var uiState by mutableStateOf(UI_EstadoRegistro())
    private set

    fun cambiarNombre (nuevoNombre:String){
        uiState = uiState.copy(nombre = nuevoNombre)
    }

    fun cambiarCorreo (nuevoCorreo:String){
        uiState = uiState.copy(correo = nuevoCorreo)
    }

    fun cambiarContraseña (nuevaContraseña:String){
        uiState = uiState.copy(contraseña = nuevaContraseña)
    }

    fun cambiarConfirmarContraseña (nuevaConfirmarContraseña:String){
        uiState = uiState.copy(confirmarContraseña = nuevaConfirmarContraseña)
    }

    fun crearCuenta(){

        val nombre = uiState.nombre.trim()
        val correo = uiState.correo.trim()
        val contraseña = uiState.contraseña
        val confirmarContraseña = uiState.confirmarContraseña



        val mensajeDeError = when {
            nombre.isEmpty() -> "Error - El nombre no puede estar vacío"
            correo.isEmpty() -> "Error - El correo no puede estar vacío"
            contraseña.isEmpty() -> "Error - La contraseña no puede estar vacía"
            confirmarContraseña.isEmpty() -> "Error - La confirmacion de la contraseña no puede estar vacía"
            contraseña != confirmarContraseña -> "Error - Las contraseñas no coinciden"
            else -> null
        // Si todo está bien, no hay error
        }

        //  Actualizamos el estado de la UI una sola vez
        uiState = uiState.copy(error = mensajeDeError)

        // Si hubo un error, detenemos la ejecución de la función aquí
        if (mensajeDeError != null) return



        viewModelScope.launch {

            uiState = uiState.copy(
                cargando = true,
                error = null
            )

            try {

                repository.crearCuentaAuth(
                    correo = correo,
                    contraseña = contraseña
                )

                uiState = uiState.copy(
                    cargando = false,
                    cuentaCreada = true
                )

            } catch (e: Exception) {

                uiState = uiState.copy(
                    cargando = false,
                    error = e.localizedMessage
                        ?: "Error al crear cuenta"
                )


            }
        }

    }


}