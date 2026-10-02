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
import com.example.gastospersonales.UI.Screens.InicioDeSesionScreen
import com.example.gastospersonales.UI.Screens.RegistroScreen


@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    val usuarioLogeado = VerificacionDeUsuario()

    val rutaInicial =
        if (usuarioLogeado) {
            "movimientos_graph"
        } else {
            Screen.InicioDeSesionScreen.ruta
        }

    val navBackStackEntry by
    navController.currentBackStackEntryAsState()

    val rutaActual =
        navBackStackEntry
            ?.destination
            ?.route

    val mostrarBottomBar =
        rutaActual == Screen.InicioScreen.ruta ||
                rutaActual == Screen.MovimientosScreen.ruta

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        NavHost(
            navController = navController,
            startDestination = rutaInicial
        ) {

            // ---------------------------
            // LOGIN
            // ---------------------------

            composable(
                Screen.InicioDeSesionScreen.ruta
            ) {
                InicioDeSesionScreen(
                    navController
                )
            }

            // ---------------------------
            // REGISTRO
            // ---------------------------

            composable(
                Screen.RegistroScreen.ruta
            ) {
                RegistroScreen(
                    navController
                )
            }

            // ---------------------------
            // MOVIMIENTOS
            // ---------------------------

            movimientoNavigation(
                navController = navController
            )
        }


        // ---------------------------
        // BARRA INFERIOR
        // ---------------------------

        if (mostrarBottomBar) {

            BottomNavigationBar(

                modifier = Modifier.align(
                    Alignment.BottomCenter
                ),

                rutaActual = rutaActual,

                ClickDeBotonAgregar = {

                    navController.navigate(
                        "${Screen.AgregarGastosScreen.ruta}/0"
                    ) {
                        launchSingleTop = true
                    }
                },

                ClickDeBotonInicio = {

                    navController.navigate(
                        Screen.InicioScreen.ruta
                    ) {

                        popUpTo(
                            "movimientos_graph"
                        ) {
                            saveState = true
                        }

                        launchSingleTop = true
                        restoreState = true
                    }
                },

                ClickDeBotonHistorial = {

                    navController.navigate(
                        Screen.MovimientosScreen.ruta
                    ) {

                        popUpTo(
                            "movimientos_graph"
                        ) {
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