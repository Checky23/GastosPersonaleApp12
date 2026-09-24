package com.example.gastospersonales.UI.Navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gastospersonales.Data.FireBase.VerificacionDeUsuario
import com.example.gastospersonales.UI.Screens.InicioScreen
import com.example.gastospersonales.UI.Screens.InicioDeSesionScreen
import com.example.gastospersonales.UI.Screens.RegistroScreen

@Composable
fun AppNavigation() {
    // 1. Instancias el controlador
    val navController = rememberNavController()

    // 2. Verificas si hay sesión
    val usuarioLogeado = VerificacionDeUsuario()

    // 3. Decides CÚAL va a ser la ruta inicial basándote en la sesión
    val rutaInicial = if (usuarioLogeado) {
        Screen.InicioScreen.ruta
    } else {
        Screen.InicioDeSesionScreen.ruta
    }

    // 4. UN SOLO NAVHOST CON TODAS TUS PANTALLAS
    NavHost(
        navController = navController,
        startDestination = rutaInicial
    ) {

        // Pantalla de Inicio de Sesión
        composable(Screen.InicioDeSesionScreen.ruta) {
            InicioDeSesionScreen(navController)
        }

        // Pantalla de Registro
        composable(Screen.RegistroScreen.ruta) {
            RegistroScreen(navController)
        }

        // Pantalla Principal (Inicio)
        composable(Screen.InicioScreen.ruta) {
            InicioScreen(navController)
        }

        // Pantalla Principal (Inicio)
        composable(Screen.AgregarGastosScreen.ruta) {
            InicioScreen(navController)
        }
    }
}