package com.example.gastospersonales.Data.Repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

class AuthRepository {
    suspend fun iniciarSesionConCorreo( correo: String, contrasena: String): FirebaseUser {

        //get instance de firebase es para obtener la instancia de firebase
        val resultado = FirebaseAuth.getInstance()
            //aqui busca un usuario registrado con este correo y comprueba esta contraseña dentro de firebase
            .signInWithEmailAndPassword(correo, contrasena)
            //espera a que se complete la tarea
            .await()

        //si esta el usua
        // rio lo devuelve si no lanza una excepcion que se maneja en el viewmodel
        return resultado.user  ?: throw IllegalStateException("No se pudo obtener el usuario")
    }


    suspend fun crearCuentaAuth(
        correo: String,
        contraseña: String
    ) {

        val resultado = FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(
                correo,
                contraseña
            )
            .await()

        val usuario = resultado.user
            ?: throw IllegalStateException(
                "No se pudo obtener el usuario"
            )

        // Enviar correo de verificación para la cuenta recién creada
        usuario.sendEmailVerification().await()
    }




}