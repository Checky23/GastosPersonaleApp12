package com.example.gastospersonales.Data.Model

data class UI_EstadoLogin(
    val correo: String = "",
    val contraseña: String = "",
    val cargando: Boolean = false,
    val error: String? = null,
    val iniciadoSesion: Boolean = false


)
