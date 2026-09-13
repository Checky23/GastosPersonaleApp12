package com.example.gastospersonales.Navegacion

sealed class Screen(val ruta: String) {

    data object InicioDeSesionScreen : Screen("InicioDeSesion")

    data object RegistroScreen : Screen("Registro")

}