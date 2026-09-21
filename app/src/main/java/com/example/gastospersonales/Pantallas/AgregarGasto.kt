package com.example.gastospersonales.Pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun AgregarGastosScreen(navController: NavHostController) {
    // Paleta de colores extraída de la imagen
    val darkGreen = Color(0xFF347D5A)
    val lightGreenBg = Color(0xFFE4F0E6)
    val lightGrayBorder = Color(0xFFEAEDED)
    val bgColor = Color(0xFFF8F9FA)
    val textColor = Color(0xFF1B2A3B) // Color oscuro para los textos principales
    val labelColor = Color(0xFF5B6A7A) // Color gris para los labels pequeños

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor)
            .padding(24.dp)
    ) {
        // Creación de las referencias
        val (headerGroup, amountLabel, topBox, categoryLabel, gridBox, dateLabel, input1, descLabel, input2, bottomButton, cancelText) = createRefs()

        // --- ENCABEZADO (Simulado) ---
        Column(
            modifier = Modifier.constrainAs(headerGroup) {
                top.linkTo(parent.top, margin = 24.dp)
                start.linkTo(parent.start)
            }
        ) {
            Text(text = "<   Agregar gasto", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = textColor)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Registra una compra en pocos segundos.", fontSize = 14.sp, color = labelColor)
        }

        // --- MONTO ---
        Text(
            text = "Monto",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = textColor,
            modifier = Modifier.constrainAs(amountLabel) {
                top.linkTo(headerGroup.bottom, margin = 24.dp)
                start.linkTo(parent.start)
            }
        )

        // 1. Caja superior (Monto)
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .background(Color.White, shape = RoundedCornerShape(12.dp))
                .border(2.dp, darkGreen, shape = RoundedCornerShape(12.dp))
                .padding(horizontal = 16.dp)
                .constrainAs(topBox) {
                    top.linkTo(amountLabel.bottom, margin = 8.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            Text(text = "C$ 0.00", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = textColor)
        }

        // --- CATEGORÍA ---
        Text(
            text = "Categoría",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = textColor,
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
                GridItem("🍔\nComida", isSelected = true, darkGreen, lightGreenBg, lightGrayBorder, Modifier.weight(1f))
                GridItem("🚂\nTransporte", isSelected = false, darkGreen, lightGreenBg, lightGrayBorder, Modifier.weight(1f))
                GridItem("🏠\nHogar", isSelected = false, darkGreen, lightGreenBg, lightGrayBorder, Modifier.weight(1f))
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                GridItem("💡\nServicios", isSelected = false, darkGreen, lightGreenBg, lightGrayBorder, Modifier.weight(1f))
                GridItem("🎮\nOcio", isSelected = false, darkGreen, lightGreenBg, lightGrayBorder, Modifier.weight(1f))
                GridItem("🎛️\nOtros", isSelected = false, darkGreen, lightGreenBg, lightGrayBorder, Modifier.weight(1f))
            }
        }

        // --- FECHA ---
        Text(
            text = "Fecha",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = textColor,
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
            Text(text = "26 ago 2026", color = textColor)
        }

        // --- DESCRIPCIÓN ---
        Text(
            text = "Descripción (opcional)",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = textColor,
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
            Text(text = "¿Qué compraste?", color = labelColor)
        }

        // 5. Botón principal
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp) // Ajustado a un tamaño más estándar para botones
                .background(darkGreen, shape = RoundedCornerShape(12.dp))
                .constrainAs(bottomButton) {
                    top.linkTo(input2.bottom, margin = 32.dp)
                }
        ) {
            Text(text = "Guardar gasto", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }

        // --- CANCELAR ---
        Text(
            text = "Cancelar",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = labelColor,
            modifier = Modifier.constrainAs(cancelText) {
                top.linkTo(bottomButton.bottom, margin = 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
    }
}

// Composable reutilizable para los elementos de la cuadrícula
@Composable
fun GridItem(

    text: String,
    isSelected: Boolean,
    darkGreen: Color,
    lightGreenBg: Color,
    lightGrayBorder: Color,
    modifier: Modifier = Modifier
) {
    val currentBgColor = if (isSelected) lightGreenBg else Color.White
    val currentBorderColor = if (isSelected) darkGreen else lightGrayBorder
    val currentTextColor = if (isSelected) darkGreen else Color(0xFF1B2A3B)

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .height(80.dp) // Aumenté un poco la altura para acomodar el emoji y el texto
            .background(currentBgColor, shape = RoundedCornerShape(12.dp))
            .border(1.dp, currentBorderColor, shape = RoundedCornerShape(12.dp))
    ) {
        Text(
            text = text,
            color = currentTextColor,
            fontSize = 12.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewAgregarGasto() {
  //  AgregarGastosScreen()
      val navControler = rememberNavController()
    AgregarGastosScreen(navControler)
}
