package com.example.gastospersonales.UI.Screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.gastospersonales.Data.Model.RegistroDeMovimientos
import com.example.gastospersonales.UI.Navegacion.Screen
import com.example.gastospersonales.UI.ComponentesVisuales.BottomNavigationBar
import com.example.gastospersonales.UI.ComponentesVisuales.MovimientosRecientesScreen
import com.example.gastospersonales.UI.ComponentesVisuales.ResumenMensualCard
import com.example.gastospersonales.UI.ComponentesVisuales.TarjetaDeSaldoScreen
import com.example.gastospersonales.ViewModel.MovimientoViewModel
import com.example.gastospersonales.UI.Temas.ColorBlanco
import com.example.gastospersonales.UI.Temas.NegroTitulo
import com.example.gastospersonales.UI.Temas.RojoGasto
import com.example.gastospersonales.UI.Temas.VerdeApp

@Composable
fun InicioScreen(navController: NavController = rememberNavController()) {

    val viewModel: MovimientoViewModel = viewModel()


    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorBlanco)
    ) {
        // ++++++++++++++   TITULO PRINCIPAL  ++++++++++++++

        Text(
            text = "Resumen financiero",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = NegroTitulo,
            modifier = Modifier.constrainAs(createRef()) {
                start.linkTo(parent.start, 24.dp)
                top.linkTo(parent.top, 35.dp)

            }
        )

        // ----------- TARJETA DEL SALDO DISPONIBLE  ----------------

        TarjetaDeSaldoScreen(
            250000040,
            modifier = Modifier.constrainAs(createRef()) {
                start.linkTo(parent.start, 24.dp)
                end.linkTo(parent.end, 24.dp)
                top.linkTo(parent.top, 95.dp)
            },
        )

        // ++++++++++++++  Texto De Seccion "ESTE MES" ++++++++++++++

        Text(
            text = "Este mes",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = NegroTitulo,
            modifier = Modifier.constrainAs(createRef()) {
                start.linkTo(parent.start, 24.dp)
                top.linkTo(parent.top, 300.dp)
            }
        )


        // ------------------ Tarjeta de Ingresos --------------------------

        ResumenMensualCard(

            modifier = Modifier.constrainAs(createRef()) {
                start.linkTo(parent.start, 24.dp)
                top.linkTo(parent.top, 340.dp)
            }, colorDeLetra = VerdeApp, Cantidad = 2500, "Ingresos"
        )

        // ------------------- Tarjeta De Gastos -------------------
        ResumenMensualCard(
            modifier = Modifier.constrainAs(createRef()) {
                end.linkTo(parent.end, 24.dp)
                top.linkTo(parent.top, 340.dp)
            }, colorDeLetra = RojoGasto, Cantidad = 5600, "Gastos"
        )


        // ++++++++++++++ Apartado De Movimientos Recientes ++++++++++++++

        Text(
            text = "Movimientos recientes",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = NegroTitulo,
            modifier = Modifier.constrainAs(createRef()) {
                start.linkTo(parent.start, 24.dp)
                top.linkTo(parent.top, 444.dp)
            }
        )
        // -------------- Lista De Vistas De Gastos ------------------
        LazyColumn(
            modifier = Modifier
                .width(340.dp)
                .height(180.dp)
                // .background(Color.Red)

                .constrainAs(createRef()) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top, margin = 285.dp)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }
        ) {

            items(viewModel.movimientos) { movimiento ->
                MovimientosRecientesScreen(
                    Gasto = movimiento.Gasto,
                    CantidadDelMovimiento = movimiento.Monto,
                    TipoDeMovimiento = movimiento.TipoDeMovimiento,
                    Descripcion = movimiento.Descripcion
                )
            }


        }

        //Barra De Navegacion
        BottomNavigationBar(
            modifier = Modifier.constrainAs(createRef()) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }, onClickAction = {
                navController.navigate(Screen.AgregarGastosScreen.ruta)
            }
        )
    }

}

@Preview
@Composable
fun InicioPreviow() {
    val navController = rememberNavController()
    InicioScreen(navController)
}
