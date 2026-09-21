package com.example.gastospersonales.Pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ============================================================
// COLORES //
// ============================================================

private val BackgroundColor = Color(0xFFF8FAF9)
private val DarkText = Color(0xFF1E232A)
private val GrayLabel = Color(0xFF7A828A)
private val RedAmount = Color(0xFFE55252)
private val GreenButton = Color(0xFF2E7D5B)
private val CircleBg = Color(0xFFE5F5EC)
private val TipBg = Color(0xFFFFF7DB)
private val TipHeader = Color(0xFFE5A93C)
private val DividerColor = Color(0xFFEEF0F2)

// ============================================================
// PANTALLA DETALLE DEL MOVIMIENTO
// ============================================================

@Composable
fun DetalleMovimientoScreen(
    onBackClick: () -> Unit = {},
    onEliminarClick: () -> Unit = {},
    onEditarClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {

        // ====================================================
        // TOP BAR (FLECHA + TÍTULO)
        // ====================================================

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Volver",
                tint = DarkText,
                modifier = Modifier
                    .size(20.dp)
                    .clickable { onBackClick() }
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = "Detalle del movimiento",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = DarkText
            )
        }

        // ====================================================
        // CARD PRINCIPAL
        // ====================================================

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White)
                .padding(vertical = 24.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // CÍRCULO CON EMOJI
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(CircleBg),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "🍔",
                        fontSize = 20.sp
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                // NOMBRE
                Text(
                    text = "Comida",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkText
                )

                Spacer(modifier = Modifier.height(4.dp))

                // MONTO
                Text(
                    text = "- C$ 250.00",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = RedAmount
                )

                Spacer(modifier = Modifier.height(6.dp))

                // FECHA Y HORA DE CABECERA
                Text(
                    text = "26 ago 2026 · 12:30",
                    fontSize = 11.sp,
                    color = GrayLabel
                )
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        // ====================================================
        // CAMPOS DE INFORMACIÓN
        // ====================================================

        DetailItem(label = "Categoría", value = "Comida")
        DetailItem(label = "Fecha", value = "26 ago 2026")
        DetailItem(label = "Descripción", value = "Almuerzo", showDivider = false)

        Spacer(modifier = Modifier.height(32.dp))

        // ====================================================
        // BOTONES DE ACCIÓN
        // ====================================================

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // BOTÓN ELIMINAR
            OutlinedButton(
                onClick = onEliminarClick,
                modifier = Modifier
                    .weight(1f)
                    .height(46.dp),
                shape = RoundedCornerShape(12.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, RedAmount.copy(alpha = 0.5f)),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = RedAmount
                )
            ) {
                Text(
                    text = "Eliminar",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            // BOTÓN EDITAR
            Button(
                onClick = onEditarClick,
                modifier = Modifier
                    .weight(1f)
                    .height(46.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = GreenButton,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Editar",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        // ====================================================
        // CARD DE CONSEJO
        // ====================================================

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(TipBg)
                .padding(14.dp)
        ) {
            Column {
                Text(
                    text = "Consejo",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = TipHeader
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Revisa este gasto antes de cerrar el mes.",
                    fontSize = 11.sp,
                    color = DarkText
                )
            }
        }
    }
}

// ============================================================
// COMPONENTE AUXILIAR PARA LOS FILAS DE INFORMACIÓN
// ============================================================

@Composable
private fun DetailItem(
    label: String,
    value: String,
    showDivider: Boolean = true
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = GrayLabel
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = value,
            fontSize = 13.sp,
            fontWeight = FontWeight.Normal,
            color = DarkText
        )

        if (showDivider) {
            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider(
                color = DividerColor,
                thickness = 1.dp
            )
        }
    }
}

// ============================================================
// VISTA PREVIA (PREVIEW)
// ============================================================

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DetalleMovimientoScreenPreview() {
    DetalleMovimientoScreen()
}

