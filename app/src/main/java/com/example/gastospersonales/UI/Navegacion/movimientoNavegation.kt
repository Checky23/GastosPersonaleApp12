package com.example.gastospersonales.UI.Navegacion

import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.gastospersonales.UI.Screens.AgregarGastosScreen
import com.example.gastospersonales.UI.Screens.DetalleMovimientoScreen
import com.example.gastospersonales.UI.Screens.InicioScreen
import com.example.gastospersonales.UI.Screens.MovimientosScreen
import com.example.gastospersonales.ViewModel.MovimientoViewModel

fun NavGraphBuilder.movimientoNavigation(
    navController: NavHostController
) {

    navigation(
        startDestination = Screen.InicioScreen.ruta,
        route = "grupo_movimientos"
    ) {



        composable(
            Screen.InicioScreen.ruta
        ) { backStackEntry ->

            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(
                    "grupo_movimientos"
                )
            }

            val viewModel: MovimientoViewModel = viewModel(
                viewModelStoreOwner = parentEntry
            )

            InicioScreen(
                navController = navController,
                viewModel = viewModel
            )
        }


        // ---------------------------
        // AGREGAR / EDITAR
        // ---------------------------

        composable(
            route = "${Screen.AgregarGastosScreen.ruta}/{movimientoId}",
            arguments = listOf(
                navArgument("movimientoId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val movimientoId =
                backStackEntry.arguments
                    ?.getInt("movimientoId")
                    ?: 0

            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(
                    "grupo_movimientos"
                )
            }

            val viewModel: MovimientoViewModel = viewModel(
                viewModelStoreOwner = parentEntry
            )

            AgregarGastosScreen(
                viewModel = viewModel,
                movimientoId = movimientoId,
                onTerminar = {
                    navController.popBackStack()
                }
            )
        }


        // ---------------------------
        // MOVIMIENTOS
        // ---------------------------

        composable(
            Screen.MovimientosScreen.ruta
        ) { backStackEntry ->

            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(
                    "grupo_movimientos"
                )
            }

            val viewModel: MovimientoViewModel = viewModel(
                viewModelStoreOwner = parentEntry
            )

            MovimientosScreen(
                navController = navController,
                viewModel = viewModel
            )
        }


        // ---------------------------
        // DETALLE
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
                backStackEntry.arguments
                    ?.getInt("movimientoId")
                    ?: 0

            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(
                    "grupo_movimientos"
                )
            }

            val viewModel: MovimientoViewModel = viewModel(
                viewModelStoreOwner = parentEntry
            )

            DetalleMovimientoScreen(
                viewModel = viewModel,
                movimientoId = movimientoId,

                onBackClick = {
                    navController.popBackStack()
                },

                onEliminarClick = {
                    navController.popBackStack()
                },

                onEditarClick = {
                    navController.navigate(
                        "${Screen.AgregarGastosScreen.ruta}/$movimientoId"
                    )
                }
            )
        }
    }
}