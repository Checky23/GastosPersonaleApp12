package com.example.gastospersonales.UI.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.gastospersonales.Data.Model.RegistroDeMovimientos
import com.example.gastospersonales.UI.ComponentesVisuales.MovimientosRecientesScreen
import com.example.gastospersonales.UI.ComponentesVisuales.ResumenMensualCard
import com.example.gastospersonales.UI.ComponentesVisuales.TextosTitulo
import com.example.gastospersonales.UI.Temas.Dimens
import com.example.gastospersonales.UI.Temas.Fondo
import com.example.gastospersonales.UI.Temas.RojoGasto
import com.example.gastospersonales.UI.Temas.TextSizes
import com.example.gastospersonales.UI.Temas.VerdeApp
import com.example.gastospersonales.UI.Temas.VerdeClaro
import com.example.gastospersonales.ViewModel.MovimientoViewModel
import java.text.NumberFormat
import java.util.Locale

@Composable
fun InicioScreen(
    viewModel: MovimientoViewModel
) {

    val movimientos by viewModel.movimientos.collectAsStateWithLifecycle()


    InicioContent(
        movimientos = movimientos,
        saldo = viewModel.saldo,
        totalIngresos = viewModel.totalIngresos,
        totalGastos = viewModel.totalGastos
    )
}


@Composable
private fun InicioContent(
    movimientos: List<RegistroDeMovimientos>,
    saldo: Int,
    totalIngresos: Int,
    totalGastos: Int
) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Fondo)
    ) {

        val (
            tituloResumen,
            tarjetaSaldo,
            tituloEsteMes,
            tarjetaIngresos,
            tarjetaGastos,
            tituloMovimientos,
            listaMovimientos
        ) = createRefs()


        TextosTitulo(
            texto = "Resumen financiero",
            fontSize = TextSizes.Titulo1,
            modifier = Modifier.constrainAs(tituloResumen) {
                start.linkTo(parent.start, Dimens.EspacioGrande)
                top.linkTo(parent.top, 35.dp)
            }
        )


        TarjetaDeSaldoScreen(
            saldo = saldo,
            modifier = Modifier.constrainAs(tarjetaSaldo) {
                start.linkTo(parent.start, Dimens.EspacioGrande)
                end.linkTo(parent.end, Dimens.EspacioGrande)
                top.linkTo(parent.top, 95.dp)
            }
        )


        TextosTitulo(
            texto = "Este mes",
            fontSize = TextSizes.Subtitulo1,
            modifier = Modifier.constrainAs(tituloEsteMes) {
                start.linkTo(parent.start, Dimens.EspacioGrande)
                top.linkTo(parent.top, 300.dp)
            }
        )


        ResumenMensualCard(
            modifier = Modifier.constrainAs(tarjetaIngresos) {
                start.linkTo(parent.start, Dimens.EspacioGrande)
                top.linkTo(parent.top, 340.dp)
            },
            colorDeLetra = VerdeApp,
            cantidad = totalIngresos,
            texto = "Ingresos"
        )


        ResumenMensualCard(
            modifier = Modifier.constrainAs(tarjetaGastos) {
                end.linkTo(parent.end, Dimens.EspacioGrande)
                top.linkTo(parent.top, 340.dp)
            },
            colorDeLetra = RojoGasto,
            cantidad = totalGastos,
            texto = "Gastos"
        )


        TextosTitulo(
            texto = "Movimientos recientes",
            fontSize = TextSizes.Subtitulo1,
            modifier = Modifier.constrainAs(tituloMovimientos) {
                start.linkTo(parent.start, Dimens.EspacioGrande)
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
                    top.linkTo(
                        tituloMovimientos.bottom,
                        margin = Dimens.EspacioPequeno
                    )
                }
        ) {

            items(movimientos) { movimiento ->

                MovimientosRecientesScreen(
                    Gasto = movimiento.Gasto,
                    CantidadDelMovimiento = movimiento.Monto,
                    TipoDeMovimiento = movimiento.TipoDeMovimiento,
                    Descripcion = movimiento.Descripcion,
                    FechaHora = movimiento.FechaHora
                )
            }
        }
    }
}


@Composable
private fun TarjetaDeSaldoScreen(saldo: Int, modifier: Modifier = Modifier) {


    Surface(
        modifier = modifier
            .width(342.dp)
            .height(170.dp),
        shape = RoundedCornerShape(24.dp),
        color = VerdeApp
    ) {

        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {

            // Texto pequeño de la tarjeta
            Text(
                text = "SALDO DISPONIBLE",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = VerdeClaro,
                modifier = Modifier.constrainAs(createRef()) {
                    start.linkTo(parent.start, 24.dp)
                    top.linkTo(parent.top, 30.dp)
                }
            )
            val dinero = NumberFormat.getNumberInstance(Locale.US).format(saldo)
            // Cantidad disponible
            if (saldo >= 0) {

                Text(
                    text = "C$ $dinero",
                    fontSize = 31.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.constrainAs(createRef()) {
                        start.linkTo(parent.start, 24.dp)
                        top.linkTo(parent.top, 62.dp)
                    }
                )
            } else {
                Text(
                    text = "C$ $dinero",
                    fontSize = 31.sp,
                    fontWeight = FontWeight.Bold,
                    color = RojoGasto,
                    modifier = Modifier.constrainAs(createRef()) {
                        start.linkTo(parent.start, 24.dp)
                        top.linkTo(parent.top, 62.dp)
                    }
                )
            }

            if (saldo > 0) {
                Text(
                    text = "Disponible para tus próximos gastos",
                    fontSize = 12.sp,
                    color = VerdeClaro,
                    modifier = Modifier.constrainAs(createRef()) {
                        start.linkTo(parent.start, 24.dp)
                        top.linkTo(parent.top, 105.dp)
                    }
                )
            } else {
                Text(
                    text = "Sin presupuesto disponible",
                    fontSize = 12.sp,
                    color = VerdeClaro,
                    modifier = Modifier.constrainAs(createRef()) {
                        start.linkTo(parent.start, 24.dp)
                        top.linkTo(parent.top, 105.dp)
                    }
                )
            }
            // Descripción

        }
    }
}

@Preview(showBackground = true)
@Composable
fun InicioPreview() {

    InicioContent(
        movimientos = emptyList(),
        saldo = 0,
        totalIngresos = 0,
        totalGastos = 0
    )
}