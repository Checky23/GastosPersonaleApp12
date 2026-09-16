package com.example.gastospersonales.FireBase

import android.content.Context
import android.util.Log
import android.widget.Toast

import com.google.firebase.auth.FirebaseAuth

fun CreacionDeCuenta( correo: String, contraseña: String, context: Context){


    //Obtengo la instancia de FirebaseAuth para crear la cuenta
    val auth = FirebaseAuth.getInstance()

    //Creo la cuenta con el correo y la contraseña proporcionados activado en Firebase
    auth.createUserWithEmailAndPassword(correo, contraseña)

        //Esta funcion se ejecuta cuando la cuenta se crea correctamente
        //es decir cuando la tarea se completa correctamente en Firebase
        .addOnCompleteListener { task ->

            if (task.isSuccessful){

                // Cuenta creada correctamente este sale en consola
                Log.d("FirebaseAuth", "Cuenta creada correctamente")

                //Mensaje Emergente de Confirmacion
                Toast.makeText(
                    context,
                    "Cuenta creada correctamente",
                    Toast.LENGTH_SHORT
                ).show()


            } else {

                // Error al crear la cuenta este sale en consola
                Log.e(
                    "FirebaseAuth",
                    "Error al crear cuenta",
                    task.exception
                )

                //Mensaje Emergente de Error
                Toast.makeText(
                    context,
                    "Error al crear cuenta",
                    Toast.LENGTH_SHORT
                ).show()


            }




        }


}