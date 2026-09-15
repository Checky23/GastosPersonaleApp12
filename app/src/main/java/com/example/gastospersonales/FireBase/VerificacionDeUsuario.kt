package com.example.gastospersonales.FireBase

import com.google.firebase.auth.FirebaseAuth

//AuthGate
fun VerificacionDeUsuario(): Boolean {

    val auth = FirebaseAuth.getInstance()

    return auth.currentUser != null


}