package com.example.gastospersonales.Navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gastospersonales.FireBase.VerificacionDeUsuario
import com.example.gastospersonales.Pantallas.Inicio.InicioScreen
import com.example.gastospersonales.Pantallas.InicioDeSesionScreen
import com.example.gastospersonales.Pantallas.RegistroScreen
import com.example.gastospersonales.Pantallas.SplashScreenConstraint

@Composable
fun AppNavigation() {

    // Controla la navegación entre pantallas
    val navController = rememberNavController()
    val usuarioLogeado = VerificacionDeUsuario()

    if ( usuarioLogeado == true){
        NavHost(
            navController = navController,
            // arranca en el splash, no en el login

            startDestination = "splash"
        ) {

            // RUTA DEL SPLASH SCREEN
          /*  composable("splash") {
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
            }*/

            //  Inicio - Temporal
            composable(Screen.InicioScreen.ruta) {
               InicioScreen(navController)
            }

            // RegistroDeSesion
            composable(Screen.RegistroScreen.ruta) {
                RegistroScreen(navController)
            }
        }
    } else{
        NavHost(
            navController = navController,
            startDestination = Screen.InicioDeSesionScreen.ruta
        ){
            //  InicioDeSesion
            composable(Screen.InicioDeSesionScreen.ruta){
                InicioDeSesionScreen(navController)
            }
        }
    }






}