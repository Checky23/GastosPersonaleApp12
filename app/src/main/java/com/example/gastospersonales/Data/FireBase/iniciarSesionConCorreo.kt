package com.example.gastospersonales.Data.FireBase

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

suspend fun iniciarSesionConCorreo(
    correo: String,
    contrasena: String,
    onSuccess: () -> Unit,
    onError: (String) -> Unit
){
    try {
        FirebaseAuth.getInstance()

            //Busca un usuario registrado con este correo y comprueba esta contraseña
            .signInWithEmailAndPassword(correo, contrasena)


            //Espera a que se complete la tarea
            .await()
        onSuccess()

    } catch (e: Exception) {

        onError(e.localizedMessage ?: "Error desconocido")
    }



}