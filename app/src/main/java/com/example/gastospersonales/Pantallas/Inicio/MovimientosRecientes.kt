package com.example.gastospersonales.Pantallas.Inicio

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.gastospersonales.ui.theme.NegroTitulo
import com.example.gastospersonales.ui.theme.RojoGasto
import com.example.gastospersonales.ui.theme.SubTituloGris
import com.example.gastospersonales.ui.theme.VerdeApp
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Composable
fun MovimientosRecientesScreen(
    Gasto: String,
    CantidadDelMovimiento: Int,
    TipoDeMovimiento: Boolean
) {
    val fechaHora = LocalDateTime.now()
    val formato = DateTimeFormatter.ofPattern("h:mm a")
    val hora = fechaHora.format(formato)
    val textoFecha = obtenerFechaMovimiento(fechaHora)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Text(
                text = "🍔  $Gasto",
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = NegroTitulo,
                modifier = Modifier.constrainAs(createRef()) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
            )

            Text(
                text = "$textoFecha · $hora",
                fontSize = 11.sp,
                color = SubTituloGris,
                modifier = Modifier.constrainAs(createRef()) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top, 20.dp)
                }
            )
            if (TipoDeMovimiento) {
                Text(
                    text = " C$ $CantidadDelMovimiento",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = VerdeApp,
                    modifier = Modifier.constrainAs(createRef()) {
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)


                    }
                )
            } else {
                Text(
                    text = "− C$ $CantidadDelMovimiento",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = RojoGasto,
                    modifier = Modifier.constrainAs(createRef()) {
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)


                    }
                )
            }

        }
    }
}

@Preview
@Composable
fun MovimientosRecientesPreview() {
    MovimientosRecientesScreen("Comida", 2500, false)
}

fun obtenerFechaMovimiento(fecha: LocalDateTime): String {

    val hoy = LocalDateTime.now().toLocalDate()
    val fechaMovimiento = fecha.toLocalDate()

    return when {
        fechaMovimiento == hoy -> "Hoy"
        fechaMovimiento == hoy.minusDays(1) -> "Ayer"
        else -> fecha.format(
            DateTimeFormatter.ofPattern("dd MMM")
        )
    }
}