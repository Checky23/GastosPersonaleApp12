package com.example.gastospersonales.Navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gastospersonales.FireBase.VerificacionDeUsuario
import com.example.gastospersonales.Pantallas.Inicio
import com.example.gastospersonales.Pantallas.InicioDeSesionScreen
import com.example.gastospersonales.Pantallas.RegistroScreen


@Composable
fun AppNavigation() {

    // Controla la navegación entre pantallas
    val navController = rememberNavController()
    val usuarioLogeado = VerificacionDeUsuario()

    if ( usuarioLogeado == true){
        NavHost(
            navController = navController,
            startDestination = Screen.InicioScreen.ruta
        ) {

            //  Inicio - Temporal
            composable(Screen.InicioScreen.ruta) {
                Inicio(navController)
            }

            // RegistroDeSesion
            composable(Screen.RegistroScreen.ruta) {
                RegistroScreen()
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