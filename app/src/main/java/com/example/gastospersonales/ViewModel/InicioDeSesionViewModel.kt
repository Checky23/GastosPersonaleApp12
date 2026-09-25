package com.example.gastospersonales.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gastospersonales.Data.Model.UI_EstadoLogin
import com.example.gastospersonales.Data.Repository.AuthRepository
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import kotlinx.coroutines.launch

class InicioDeSesionViewModel: ViewModel()  {

    private val repository = AuthRepository()
    var uiState by mutableStateOf(UI_EstadoLogin())
        // Estado de inicio de sesión
        private set
    fun cambiarCorreo (nuevoCorreo:String){
        uiState = uiState.copy(correo = nuevoCorreo)
    }
    fun cambiarContraseña (nuevaContraseña:String){
        uiState = uiState.copy(contraseña = nuevaContraseña)
    }

    fun iniciarSesion(){

        //el .trim() para elimina espacios en blanco que estén al principio o al final
        val correo = uiState.correo.trim()
        val contraseña = uiState.contraseña

        if (correo.isEmpty()){
            uiState = uiState.copy(error = "Error - El correo no puede estar vacío")
            return
        }
        if (contraseña.isEmpty()){
            uiState = uiState.copy(error = "Error - La contraseña no puede estar vacía")
            return
        }

        viewModelScope.launch {
            uiState = uiState.copy(cargando = true, error = null)

            try {
                repository.iniciarSesionConCorreo(correo = uiState.correo, contrasena = uiState.contraseña)

                uiState = uiState.copy(cargando = false, sesionIniciada = true)

            }catch (e: Exception){


                val mensaje = when (e) {

                    is FirebaseAuthInvalidCredentialsException ->
                        "El correo o la contraseña no son correctos."

                    else ->
                        e.localizedMessage ?: "Error al iniciar sesión"
                }



                uiState = uiState.copy(cargando = false, error = mensaje)
            }

        }
    }



}

