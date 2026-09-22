package com.example.gastospersonales.UI.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gastospersonales.UI.Temas.Fondo
import com.example.gastospersonales.UI.Temas.GrisBorde
import com.example.gastospersonales.UI.Temas.NegroTitulo
import com.example.gastospersonales.UI.Temas.SubTituloGris
import com.example.gastospersonales.UI.Temas.VerdeApp
import com.example.gastospersonales.UI.Temas.VerdeClaro
import com.example.gastospersonales.ViewModel.MovimientoViewModel

@Composable
fun AgregarGastosScreen(navController: NavHostController ,viewModel : MovimientoViewModel = viewModel() ) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Fondo)
            .padding(24.dp)
    ) {
        // Creación de las referencias
        val (TextsPresentacion, amountLabel, topBox, categoryLabel, gridBox, dateLabel, input1, descLabel, input2, bottomButton, cancelText) = createRefs()

        // ---------- ENCABEZADO ---------------
        Column(
            modifier = Modifier.constrainAs(TextsPresentacion) {
                top.linkTo(parent.top, margin = 24.dp)
                start.linkTo(parent.start)
            }
        ) {
            Text(
                text = "Agregar gasto",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = NegroTitulo
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Registra una compra en pocos segundos.",
                fontSize = 14.sp,
                color = SubTituloGris
            )
        }

        // -------------- MONTO ------------
        Text(
            text = "Monto",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = NegroTitulo,
            modifier = Modifier.constrainAs(amountLabel) {
                top.linkTo(TextsPresentacion.bottom, margin = 24.dp)
                start.linkTo(parent.start)
            }
        )

        // 1. Caja superior






        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .background(Color.White, shape = RoundedCornerShape(12.dp))
                .border(2.dp, VerdeApp, shape = RoundedCornerShape(12.dp))
                .padding(horizontal = 16.dp)
                .constrainAs(topBox) {
                    top.linkTo(amountLabel.bottom, margin = 8.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            Text(
                text = "C$ 0.00",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = NegroTitulo
            )
        }

        // --- CATEGORÍA ---
        Text(
            text = "Categoría",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = NegroTitulo,
            modifier = Modifier.constrainAs(categoryLabel) {
                top.linkTo(topBox.bottom, margin = 24.dp)
                start.linkTo(parent.start)
            }
        )

        // 2. Cuadrícula central
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(gridBox) {
                    top.linkTo(categoryLabel.bottom, margin = 8.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                GridItem(
                    "🍔\nComida",
                    isSelected = true,
                    Modifier.weight(1f)
                )

                GridItem(
                    "🚂\nTransporte",
                    isSelected = false,
                    Modifier.weight(1f)
                )

                GridItem(
                    "🏠\nHogar",
                    isSelected = false,
                    Modifier.weight(1f)
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                GridItem(
                    "💡\nServicios",
                    isSelected = false,
                    Modifier.weight(1f)
                )

                GridItem(
                    "🎮\nOcio",
                    isSelected = false,
                    Modifier.weight(1f)
                )

                GridItem(
                    "🎛️\nOtros",
                    isSelected = false,
                    Modifier.weight(1f)
                )
            }
        }

        // --- FECHA ---
        Text(
            text = "Fecha",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = NegroTitulo,
            modifier = Modifier.constrainAs(dateLabel) {
                top.linkTo(gridBox.bottom, margin = 24.dp)
                start.linkTo(parent.start)
            }
        )

        // 3. Primer input (Fecha)
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(Color.White, shape = RoundedCornerShape(16.dp))
                .padding(horizontal = 16.dp)
                .constrainAs(input1) {
                    top.linkTo(dateLabel.bottom, margin = 8.dp)
                }
        ) {
            Text(
                text = "26 ago 2026",
                color = NegroTitulo
            )
        }

        // --- DESCRIPCIÓN ---
        Text(
            text = "Descripción (opcional)",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = NegroTitulo,
            modifier = Modifier.constrainAs(descLabel) {
                top.linkTo(input1.bottom, margin = 24.dp)
                start.linkTo(parent.start)
            }
        )

        // 4. Segundo input (Descripción)
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(Color.White, shape = RoundedCornerShape(16.dp))
                .padding(horizontal = 16.dp)
                .constrainAs(input2) {
                    top.linkTo(descLabel.bottom, margin = 8.dp)
                }
        ) {
            Text(
                text = "¿Qué compraste?",
                color = SubTituloGris
            )
        }

        // 5. Botón principal
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(VerdeApp, shape = RoundedCornerShape(12.dp))
                .constrainAs(bottomButton) {
                    top.linkTo(input2.bottom, margin = 32.dp)
                }
        ) {
            Text(
                text = "Guardar gasto",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }

        // --- CANCELAR ---
        Text(
            text = "Cancelar",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = SubTituloGris,
            modifier = Modifier.constrainAs(cancelText) {
                top.linkTo(bottomButton.bottom, margin = 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
    }
}

@Composable
fun GridItem(
    text: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {
    val currentBgColor = if (isSelected) VerdeClaro else Color.White
    val currentBorderColor = if (isSelected) VerdeApp else GrisBorde
    val currentTextColor = if (isSelected) VerdeApp else NegroTitulo

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .height(80.dp)
            .background(
                currentBgColor,
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                1.dp,
                currentBorderColor,
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        Text(
            text = text,
            color = currentTextColor,
            fontSize = 12.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewAgregarGasto() {
    val navControler = rememberNavController()
    AgregarGastosScreen(navControler )
}