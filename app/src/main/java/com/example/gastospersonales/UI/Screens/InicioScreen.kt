package com.example.gastospersonales.UI.Screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.gastospersonales.UI.ComponentesVisuales.MovimientosRecientesScreen
import com.example.gastospersonales.UI.ComponentesVisuales.ResumenMensualCard
import com.example.gastospersonales.UI.ComponentesVisuales.TarjetaDeSaldoScreen
import com.example.gastospersonales.UI.Temas.NegroTitulo
import com.example.gastospersonales.UI.Temas.RojoGasto
import com.example.gastospersonales.UI.Temas.VerdeApp
import com.example.gastospersonales.ViewModel.MovimientoViewModel

@Composable
fun InicioScreen(
    navController: NavController = rememberNavController(),
    viewModel: MovimientoViewModel = viewModel()
) {

    val movimientos by viewModel.movimientos.collectAsState()

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {

        // Referencias
        val (
            tituloResumen,
            tarjetaSaldo,
            tituloEsteMes,
            tarjetaIngresos,
            tarjetaGastos,
            tituloMovimientos,
            listaMovimientos
        ) = createRefs()

        Text(
            text = "Resumen financiero",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = NegroTitulo,
            modifier = Modifier.constrainAs(tituloResumen) {
                start.linkTo(parent.start, 24.dp)
                top.linkTo(parent.top, 35.dp)
            }
        )

        TarjetaDeSaldoScreen(
            250000040,
            modifier = Modifier.constrainAs(tarjetaSaldo) {
                start.linkTo(parent.start, 24.dp)
                end.linkTo(parent.end, 24.dp)
                top.linkTo(parent.top, 95.dp)
            }
        )

        Text(
            text = "Este mes",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = NegroTitulo,
            modifier = Modifier.constrainAs(tituloEsteMes) {
                start.linkTo(parent.start, 24.dp)
                top.linkTo(parent.top, 300.dp)
            }
        )

        ResumenMensualCard(
            modifier = Modifier.constrainAs(tarjetaIngresos) {
                start.linkTo(parent.start, 24.dp)
                top.linkTo(parent.top, 340.dp)
            },
            colorDeLetra = VerdeApp,
            Cantidad = 2500,
            "Ingresos"
        )

        ResumenMensualCard(
            modifier = Modifier.constrainAs(tarjetaGastos) {
                end.linkTo(parent.end, 24.dp)
                top.linkTo(parent.top, 340.dp)
            },
            colorDeLetra = RojoGasto,
            Cantidad = 5600,
            "Gastos"
        )

        Text(
            text = "Movimientos recientes",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = NegroTitulo,
            modifier = Modifier.constrainAs(tituloMovimientos) {
                start.linkTo(parent.start, 24.dp)
                top.linkTo(parent.top, 444.dp)
            }
        )

        LazyColumn(
            modifier = Modifier
                .width(340.dp)
                .height(180.dp)
                .constrainAs(listaMovimientos) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)

                    // Ahora queda debajo del título
                    top.linkTo(
                        tituloMovimientos.bottom,
                        margin = 8.dp
                    )
                }
        ) {

            items(movimientos) { movimiento ->

                MovimientosRecientesScreen(
                    Gasto = movimiento.Gasto,
                    CantidadDelMovimiento = movimiento.Monto,
                    TipoDeMovimiento = movimiento.TipoDeMovimiento,
                    Descripcion = movimiento.Descripcion
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InicioPreviow() {

    val navController = rememberNavController()

    InicioScreen(navController)
}