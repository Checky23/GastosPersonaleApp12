package com.example.gastospersonales.UI.Navegacion

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
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
        route = Screen.GrupoMovimientos.ruta
    ) {


        // INICIO


        composable(
            Screen.InicioScreen.ruta
        ) { backStackEntry ->

            val viewModel =
                obtenerMovimientoViewModel(
                    navController,
                    backStackEntry
                )

            InicioScreen(
                viewModel = viewModel
            )
        }


        // AGREGAR / EDITAR


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

            val viewModel =
                obtenerMovimientoViewModel(
                    navController,
                    backStackEntry
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

            val viewModel =
                obtenerMovimientoViewModel(
                    navController,
                    backStackEntry
                )

            MovimientosScreen(
                viewModel = viewModel,
                onMovimientoClick = { movimientoId ->

                    navController.navigate(
                        "${Screen.DetalleMovimientoScreen.ruta}/$movimientoId"
                    ) {
                        launchSingleTop = true
                    }
                }
            )
        }


        // DETALLE


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

            val viewModel =
                obtenerMovimientoViewModel(
                    navController,
                    backStackEntry
                )

            DetalleMovimientoScreen(
                viewModel = viewModel,
                movimientoId = movimientoId,

                onBackClick = {
                    navController.popBackStack()
                },

                onEliminarClick = {
                    viewModel.eliminarMovimiento(movimientoId)
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


@Composable
private fun obtenerMovimientoViewModel(
    navController: NavHostController,
    backStackEntry: NavBackStackEntry
): MovimientoViewModel {

    val parentEntry = remember(backStackEntry) {
        navController.getBackStackEntry(
            Screen.GrupoMovimientos.ruta
        )
    }

    return viewModel(
        viewModelStoreOwner = parentEntry
    )
}