package com.example.gastospersonales.Data.Model

data class UI_EstadoRegistro(
    val nombre: String = "",
    val correo: String = "",
    val contraseña : String = "",
    val confirmarContraseña : String = "",
    val cargando : Boolean = false,
    val error : String? = null,
    val cuentaCreada : Boolean = false

)
