package com.example.gastospersonales.UI.Navegacion

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.gastospersonales.Data.FireBase.VerificacionDeUsuario
import com.example.gastospersonales.UI.ComponentesVisuales.BottomNavigationBar
import com.example.gastospersonales.UI.Screens.AgregarGastosScreen
import com.example.gastospersonales.UI.Screens.DetalleMovimientoScreen
import com.example.gastospersonales.UI.Screens.InicioDeSesionScreen
import com.example.gastospersonales.UI.Screens.InicioScreen
import com.example.gastospersonales.UI.Screens.MovimientosScreen
import com.example.gastospersonales.UI.Screens.RegistroScreen

@Composable
fun AppNavigation() {

    val navController =
        rememberNavController()

    val usuarioLogeado = VerificacionDeUsuario()

    val rutaInicial = if (usuarioLogeado) { Screen.InicioScreen.ruta } else { Screen.InicioDeSesionScreen.ruta }

    val navBackStackEntry by
    navController
        .currentBackStackEntryAsState()

    val rutaActual =
        navBackStackEntry
            ?.destination
            ?.route

    // La barra inferior solo aparece
    // en Inicio y Movimientos.
    val mostrarBottomBar =
        rutaActual ==
                Screen.InicioScreen.ruta ||
                rutaActual ==
                Screen.MovimientosScreen.ruta

    Box(
        modifier =
            Modifier.fillMaxSize()
    ) {

        NavHost(

            navController =
                navController,

            startDestination =
                rutaInicial
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
            // INICIO
            // ---------------------------

            composable(
                Screen.InicioScreen.ruta
            ) {

                InicioScreen(
                    navController
                )
            }

            // ---------------------------
            // AGREGAR GASTO
            // ---------------------------

            composable(
                route = "${Screen.AgregarGastosScreen.ruta}/{movimientoId}",
                arguments = listOf(
                    navArgument("movimientoId") { type = NavType.IntType
                    }
                )
            ) {
                    backStackEntry ->

                val movimientoId = backStackEntry.arguments?.getInt("movimientoId") ?: 0

                AgregarGastosScreen(
                    navController = navController,
                    movimientoId = movimientoId // Le pasamos el ID a la pantalla
                )
            }

            // ---------------------------
            // MOVIMIENTOS
            // ---------------------------

            composable(
                Screen.MovimientosScreen.ruta
            ) {

                MovimientosScreen(
                    navController
                )
            }

            // ---------------------------
            // DETALLE DEL MOVIMIENTO
            // ---------------------------

            composable(

                route = "${Screen.DetalleMovimientoScreen.ruta}/{movimientoId}",
                arguments = listOf(
                        navArgument("movimientoId") {
                            type = NavType.IntType
                        }
                    )
            ) { backStackEntry ->

                val movimientoId =
                    backStackEntry.arguments?.getInt("movimientoId") ?: 0

                DetalleMovimientoScreen(
                    movimientoId = movimientoId,
                    onBackClick = {

                        //controlo y verifico si hay mas pantallas en la pila para poder retroceder, si hay retrocedo
                        if (navController.previousBackStackEntry != null) {

                            //si hay retrocedo a la pantalla anterior
                            navController.popBackStack()
                        }

                    },
                    // Acción al eliminar (regresa a la pantalla anterior)
                    onEliminarClick = {
                        navController.popBackStack()
                    },

                    // Acción al editar (navega al formulario pasándole el ID)
                    onEditarClick = {
                        navController.navigate("${Screen.AgregarGastosScreen.ruta}/$movimientoId")
                    }
                )
            }
        }

        // ---------------------------
        // BARRA INFERIOR
        // ---------------------------

        if (mostrarBottomBar) {

            BottomNavigationBar(

                modifier =
                    Modifier.align(
                        Alignment.BottomCenter
                    ),

                rutaActual =
                    rutaActual,

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
                            Screen.InicioScreen.ruta
                        ) {

                            saveState =
                                true
                        }

                        launchSingleTop =
                            true

                        restoreState =
                            true
                    }
                },

                ClickDeBotonHistorial = {

                    navController.navigate(
                        Screen.MovimientosScreen.ruta
                    ) {

                        popUpTo(
                            Screen.InicioScreen.ruta
                        ) {

                            saveState =
                                true
                        }

                        launchSingleTop =
                            true

                        restoreState =
                            true
                    }
                }
            )
        }
    }
}