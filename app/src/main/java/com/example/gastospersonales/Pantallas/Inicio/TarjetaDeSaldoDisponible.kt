package com.example.gastospersonales.Pantallas.Inicio

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.gastospersonales.ui.theme.VerdeApp
import com.example.gastospersonales.ui.theme.VerdeClaro

@Composable
fun TarjetaDeSaldoScreen (modifier: Modifier = Modifier){


    Surface(
        modifier = modifier
            .width(342.dp)
            .height(170.dp)
           ,
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

            // Cantidad disponible
            Text(
                text = "C$ 15,240.00",
                fontSize = 31.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.constrainAs(createRef()) {
                    start.linkTo(parent.start, 24.dp)
                    top.linkTo(parent.top, 62.dp)
                }
            )

            // Descripción
            Text(
                text = "Disponible para tus próximos gastos",
                fontSize = 12.sp,
                color = VerdeClaro,
                modifier = Modifier.constrainAs(createRef()) {
                    start.linkTo(parent.start, 24.dp)
                    top.linkTo(parent.top, 98.dp)
                }
            )
        }
    }
}

@Composable
@Preview
fun PreviewTarjetaDeSaldoDisponible(){
    TarjetaDeSaldoScreen ()
}