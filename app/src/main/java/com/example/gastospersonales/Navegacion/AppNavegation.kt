package com.example.gastospersonales.Navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gastospersonales.Pantallas.InicioDeSesionScreen
import com.example.gastospersonales.Pantallas.RegistroScreen
import com.example.gastospersonales.Pantallas.SplashScreenConstraint

@Composable
fun AppNavigation() {

    // Control de la navegación entre pantalla
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        // arranca en el splash, no en el login
        startDestination = "splash"
    ) {

        // RUTA DEL SPLASH SCREEN
        composable("splash") {
            SplashScreenConstraint(
                onAppReady = { destino ->
                    // Recibo login
                    if (destino == "login") {
                        navController.navigate(Screen.InicioDeSesionScreen.ruta) {
                            // Destruyo el splash para que el botón de "Atrás" del celular no vuelva a él
                            popUpTo("splash") { inclusive = true }
                        }
                    }
                }
            )
        }

        // Pantalla 1
        composable(route = Screen.InicioDeSesionScreen.ruta) {
            InicioDeSesionScreen()
        }

        // Pantalla 2
        composable(route = Screen.RegistroScreen.ruta) {
            RegistroScreen()
        }
    }
}