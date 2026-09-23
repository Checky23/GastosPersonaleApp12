package com.example.gastospersonales.Data.FireBase

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

//una función suspendida que crea una cuenta de usuario en Firebase Authentication.
//la funcion suspendida es una función que puede ser pausada y reanudada en cualquier momento.
suspend fun CreacionDeCuenta(correo: String, contraseña: String,
                             onSuccess: () -> Unit,
                             onError: (String) -> Unit){

    try {
        //Obtengo la instancia de FirebaseAuth para crear la cuenta
        //FirebaseAuth es una clase que proporciona una interfaz para interactuar con la autenticación de Firebase
        FirebaseAuth.getInstance()
        //Creo la cuenta con el correo y la contraseña proporcionados activado en Firebase
        .createUserWithEmailAndPassword(correo, contraseña)
        .await()
        //Si la cuenta se crea correctamente
        onSuccess()
    }catch (e: Exception) {
        onError(e.localizedMessage ?: "Error desconocido")

    }

}