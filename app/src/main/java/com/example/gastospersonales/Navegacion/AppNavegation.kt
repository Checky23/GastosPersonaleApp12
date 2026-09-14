package com.example.gastospersonales.Navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gastospersonales.Pantallas.InicioDeSesionScreen
import com.example.gastospersonales.Pantallas.RegistroScreen


@Composable
fun AppNavigation() {

    // Controla la navegación entre pantallas
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.InicioDeSesionScreen.ruta
    ) {

        // Pantalla 1
        composable(Screen.InicioDeSesionScreen.ruta) {
            InicioDeSesionScreen()
        }

        // Pantalla 2
        composable(Screen.RegistroScreen.ruta) {
            RegistroScreen()
        }
        //veropendeja
    }
}