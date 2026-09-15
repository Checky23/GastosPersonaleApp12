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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.gastospersonales.ui.theme.SubTituloGris
import com.example.gastospersonales.ui.theme.VerdeApp

@Composable
fun ResumenMensualCard (modifier: Modifier = Modifier){
    Surface(
        modifier = modifier
            .width(163.dp)
            .height(86.dp)
           ,
        shape = RoundedCornerShape(16.dp),
        color = Color.White
    ) {

        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {

            Text(
                text = "Ingresos",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = SubTituloGris,
                modifier = Modifier.constrainAs(createRef()) {
                    start.linkTo(parent.start, 18.dp)
                    top.linkTo(parent.top, 20.dp)
                }
            )

            Text(
                text = "C$ 8,500",
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold,
                color = VerdeApp,
                modifier = Modifier.constrainAs(createRef()) {
                    start.linkTo(parent.start, 18.dp)
                    top.linkTo(parent.top, 48.dp)
                }
            )
        }
    }
}