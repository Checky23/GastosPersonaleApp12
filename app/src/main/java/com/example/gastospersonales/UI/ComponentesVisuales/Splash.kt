package com.example.gastospersonales.UI.ComponentesVisuales

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.gastospersonales.UI.Temas.NegroTitulo
import com.example.gastospersonales.UI.Temas.SubTituloGris
import com.example.gastospersonales.UI.Temas.VerdeApp
import kotlinx.coroutines.delay

//parámetro para avisar que la app está lista
@Composable
fun SplashScreenConstraint(
    usuarioLogeado: Boolean,
    onAppReady: (String) -> Unit = {}
) {
    LaunchedEffect(Unit) {
        delay(1500)

        if (usuarioLogeado) {
            onAppReady("inicio")
        } else {
            onAppReady("login")
        }
    }

    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (logo, titulo, subtitulo, loading) = createRefs()

        createVerticalChain(logo, titulo, subtitulo, chainStyle = ChainStyle.Packed)

        // Logo
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(color = VerdeApp, shape = CircleShape)
                .constrainAs(logo) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "C",
                color = Color.White,
                fontSize = 54.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }

        // Título
        Text(
            text = "ControlDeGastos",
            color = NegroTitulo,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.constrainAs(titulo) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(logo.bottom, margin = 24.dp)
            }
        )

        // Subtítulo
        Text(
            text = "Controla tu dinero. Vive con tranquilidad.",
            color = SubTituloGris,
            fontSize = 14.sp,
            modifier = Modifier.constrainAs(subtitulo) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(titulo.bottom, margin = 8.dp)
            }
        )

        // Indicador de carga
        Text(
            text = "Cargando...",
            color = SubTituloGris,
            fontSize = 12.sp,
            modifier = Modifier.constrainAs(loading) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom, margin = 32.dp)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenConstraintPreview() {
    SplashScreenConstraint(usuarioLogeado = false)
}