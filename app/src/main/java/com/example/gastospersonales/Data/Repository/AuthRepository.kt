package com.example.gastospersonales.Data.Repository

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class AuthRepository {
    suspend fun iniciarSesionConCorreo( correo: String, contrasena: String){

        //get instance de firebase es para obtener la instancia de firebase
        FirebaseAuth.getInstance()
            //aqui busca un usuario registrado con este correo y comprueba esta contraseña dentro de firebase
            .signInWithEmailAndPassword(correo, contrasena)
            //espera a que se complete la tarea
            .await()
    }




}