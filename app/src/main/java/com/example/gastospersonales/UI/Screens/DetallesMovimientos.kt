package com.example.gastospersonales.UI.Screens

import com.example.gastospersonales.UI.Temas.*
import androidx.compose.foundation.BorderStroke
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

@Composable
fun DetalleMovimientoScreen(
    onBackClick: () -> Unit = {},
    onEliminarClick: () -> Unit = {},
    onEditarClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Fondo)
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {

        // TOP BAR (FLECHA + TÍTULO)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Volver",
                tint = NegroTitulo,
                modifier = Modifier
                    .size(20.dp)
                    .clickable { onBackClick() }
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = "Detalle del movimiento",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = NegroTitulo
            )
        }

        // CARD PRINCIPAL
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(ColorBlanco)
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
                        .background(VerdeClaro),
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
                    color = NegroTitulo
                )

                Spacer(modifier = Modifier.height(4.dp))

                // MONTO
                Text(
                    text = "- C$ 250.00",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = RojoGasto
                )

                Spacer(modifier = Modifier.height(6.dp))

                // FECHA Y HORA DE CABECERA
                Text(
                    text = "26 ago 2026 · 12:30",
                    fontSize = 11.sp,
                    color = SubTituloGris
                )
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        // CAMPOS DE INFORMACIÓN
        DetailItem(label = "Categoría", value = "Comida")
        DetailItem(label = "Fecha", value = "26 ago 2026")
        DetailItem(label = "Descripción", value = "Almuerzo", showDivider = false)

        Spacer(modifier = Modifier.height(32.dp))

        // BOTONES DE ACCIÓN
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
                border = BorderStroke(1.dp, RojoGasto.copy(alpha = 0.5f)),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = RojoGasto
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
                    containerColor = VerdeApp,
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

        // CARD DE CONSEJO
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
                    color = NegroTitulo
                )
            }
        }
    }
}

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
            color = SubTituloGris
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = value,
            fontSize = 13.sp,
            fontWeight = FontWeight.Normal,
            color = NegroTitulo
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DetalleMovimientoScreenPreview() {
    DetalleMovimientoScreen()
}
