package com.example.gastospersonales.UI.Navegacion

sealed class Screen(val ruta: String) {

    data object InicioDeSesionScreen :
        Screen("InicioDeSesion")

    data object RegistroScreen :
        Screen("Registro")

    data object InicioScreen :
        Screen("Inicio")

    data object AgregarGastosScreen :
        Screen("AgregarGasto")

    data object MovimientosScreen :
        Screen("Movimientos")

    data object DetalleMovimientoScreen :
        Screen("DetalleMovimiento")
}