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
import com.example.gastospersonales.ui.theme.SubTituloGris
import com.example.gastospersonales.ui.theme.VerdeApp
import java.text.NumberFormat
import java.util.Locale

@Composable
fun ResumenMensualCard(
    modifier: Modifier = Modifier,
    colorDeLetra: Color,
    Cantidad: Int,
    Texto: String
) {
    Surface(
        modifier = modifier
            .width(163.dp)
            .height(86.dp),
        shape = RoundedCornerShape(16.dp),
        color = Color.White
    ) {

        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {

            Text(
                text = Texto,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = SubTituloGris,
                modifier = Modifier.constrainAs(createRef()) {
                    start.linkTo(parent.start, 18.dp)
                    top.linkTo(parent.top, 20.dp)
                }
            )
            val dinero = NumberFormat.getNumberInstance(Locale.US).format(Cantidad)
            Text(
                text = "C$ $dinero",
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold,
                color = colorDeLetra,
                modifier = Modifier.constrainAs(createRef()) {
                    start.linkTo(parent.start, 18.dp)
                    top.linkTo(parent.top, 48.dp)
                }
            )
        }
    }
}

@Preview
@Composable
fun ResumenMensualCardPreview() {
    ResumenMensualCard(colorDeLetra = VerdeApp , Cantidad = 5000 , Texto = "Gastos")
}