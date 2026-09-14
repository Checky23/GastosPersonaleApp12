package com.example.gastospersonales.Navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gastospersonales.FireBase.VerificacionDeUsuario
import com.example.gastospersonales.Pantallas.InicioDeSesionScreen
import com.example.gastospersonales.Pantallas.InicioScreen
import com.example.gastospersonales.Pantallas.RegistroScreen
import com.example.gastospersonales.Pantallas.SplashScreenConstraint

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    val usuarioLogeado = VerificacionDeUsuario()

    NavHost(
        navController = navController,
        startDestination = if (usuarioLogeado) {
            Screen.InicioScreen.ruta
        } else {
            Screen.InicioDeSesionScreen.ruta
        }
    ) {

        composable(Screen.InicioDeSesionScreen.ruta) {
            InicioDeSesionScreen(navController)
        }

        composable(Screen.InicioScreen.ruta) {
            InicioScreen(navController)
        }

        composable(Screen.RegistroScreen.ruta) {
            RegistroScreen()
        }

        composable("splash") {
            SplashScreenConstraint(
                onAppReady = { destino ->

                    if (destino == "login") {
                        navController.navigate(
                            Screen.InicioDeSesionScreen.ruta
                        ) {
                            popUpTo("splash") {
                                inclusive = true
                            }
                        }
                    }
                }
            )
        }
    }
}