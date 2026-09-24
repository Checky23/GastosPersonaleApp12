package com.example.gastospersonales.UI.Navegacion

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.gastospersonales.Data.FireBase.VerificacionDeUsuario
import com.example.gastospersonales.UI.ComponentesVisuales.BottomNavigationBar
import com.example.gastospersonales.UI.Screens.AgregarGastosScreen
import com.example.gastospersonales.UI.Screens.InicioScreen
import com.example.gastospersonales.UI.Screens.InicioDeSesionScreen
import com.example.gastospersonales.UI.Screens.MovimientosScreen
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

    // Saber qué pantalla está activa
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val rutaActual = navBackStackEntry?.destination?.route

    // La barra solo aparece en Inicio e Historial
    val mostrarBottomBar =
        rutaActual == Screen.InicioScreen.ruta ||
                rutaActual == Screen.MovimientosScreen.ruta

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

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

            // Pantalla Agregar Gastos
            composable(Screen.AgregarGastosScreen.ruta) {
                AgregarGastosScreen(navController)
            }

            // Pantalla Movimientos
            composable(Screen.MovimientosScreen.ruta) {
                MovimientosScreen(navController)
            }
        }

        if (mostrarBottomBar) {

            BottomNavigationBar(
                modifier = Modifier.align(Alignment.BottomCenter),

                rutaActual = rutaActual,

                ClickDeBotonAgregar = {
                    navController.navigate(Screen.AgregarGastosScreen.ruta) {
                        launchSingleTop = true
                    }
                },

                ClickDeBotonInicio = {
                    navController.navigate(Screen.InicioScreen.ruta) {

                        popUpTo(Screen.InicioScreen.ruta) {
                            saveState = true
                        }

                        launchSingleTop = true
                        restoreState = true
                    }
                },

                ClickDeBotonHistorial = {
                    navController.navigate(Screen.MovimientosScreen.ruta) {

                        popUpTo(Screen.InicioScreen.ruta) {
                            saveState = true
                        }

                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}