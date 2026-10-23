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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gastospersonales.UI.Temas.ColorBlanco
import com.example.gastospersonales.UI.Temas.ColorComida
import com.example.gastospersonales.UI.Temas.ColorServicios
import com.example.gastospersonales.UI.Temas.ColorTransporte
import com.example.gastospersonales.UI.Temas.Fondo
import com.example.gastospersonales.UI.Temas.GrisBorde
import com.example.gastospersonales.UI.Temas.NegroTitulo
import com.example.gastospersonales.UI.Temas.SubTituloGris
import com.example.gastospersonales.UI.Temas.VerdeApp
import com.example.gastospersonales.ViewModel.MovimientoViewModel

@Composable
fun PresupuestoScreen(
    viewModel: MovimientoViewModel,
    onVolver: () -> Unit
) {
    val totalGastos = viewModel.totalGastos
    val gastosPorCategoria = viewModel.gastosPorCategoria

    // Presupuesto límite general de ejemplo
    val presupuestoLimiteTotal = 10000
    val porcentajeTotal = if (presupuestoLimiteTotal > 0) (totalGastos * 100) / presupuestoLimiteTotal else 0
    val progresoTotalFloat = if (presupuestoLimiteTotal > 0) (totalGastos.toFloat() / presupuestoLimiteTotal.toFloat()).coerceIn(0f, 1f) else 0f

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Fondo)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // TÍTULO Y SUBTÍTULO
        item {
            Column {
                Text(
                    text = "Presupuesto",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = NegroTitulo
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Un límite claro para cada mes.",
                    fontSize = 14.sp,
                    color = SubTituloGris
                )
            }
        }

        // TARJETA PRINCIPAL VERDE DE PRESUPUESTO MENSUAL
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(VerdeApp)
                    .padding(20.dp)
            ) {
                Column {
                    Text(
                        text = "PRESUPUESTO MENSUAL",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = ColorBlanco.copy(alpha = 0.8f)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "C$ $presupuestoLimiteTotal",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = ColorBlanco
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Gastado - C$ $totalGastos",
                        fontSize = 13.sp,
                        color = ColorBlanco.copy(alpha = 0.9f)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    LinearProgressIndicator(
                        progress = { progresoTotalFloat },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        color = ColorBlanco,
                        trackColor = ColorBlanco.copy(alpha = 0.3f)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "$porcentajeTotal% utilizado",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = ColorBlanco
                    )
                }
            }
        }

        // TÍTULO DE SECCIÓN: CATEGORÍAS
        item {
            Text(
                text = "Categorías",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = NegroTitulo,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        // LISTA DE CATEGORÍAS Y SUS LÍMITES
        if (gastosPorCategoria.isEmpty()) {
            item {
                Text(
                    text = "No hay categorías con gastos registrados.",
                    fontSize = 14.sp,
                    color = SubTituloGris,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        } else {
            items(gastosPorCategoria.toList()) { item ->
                val categoria = item.first
                val montoGasto = item.second

                // Límite estimado por categoría para fines visuales
                val limiteCategoria = 3000
                val progresoCatFloat = if (limiteCategoria > 0) (montoGasto.toFloat() / limiteCategoria.toFloat()).coerceIn(0f, 1f) else 0f

                // Selección de color según la categoría
                val colorBarra = when (categoria.lowercase()) {
                    "comida" -> ColorComida
                    "transporte" -> ColorTransporte
                    "servicios" -> ColorServicios
                    else -> VerdeApp
                }

                ItemPresupuestoCategoria(
                    categoria = categoria,
                    montoActual = montoGasto,
                    limiteMax = limiteCategoria,
                    progreso = progresoCatFloat,
                    colorBarra = colorBarra
                )
            }
        }

        // BOTÓN INFERIOR: AJUSTAR PRESUPUESTO
        item {
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedButton(
                onClick = { /* Acción para ajustar presupuesto */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(16.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, VerdeApp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = ColorBlanco,
                    contentColor = VerdeApp
                )
            ) {
                Text(
                    text = "+ Ajustar presupuesto",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun ItemPresupuestoCategoria(
    categoria: String,
    montoActual: Int,
    limiteMax: Int,
    progreso: Float,
    colorBarra: Color
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(ColorBlanco)
            .border(1.dp, GrisBorde, RoundedCornerShape(14.dp))
            .padding(16.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = categoria,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = NegroTitulo
                )
                Text(
                    text = "C$ $montoActual / C$ $limiteMax",
                    fontSize = 13.sp,
                    color = SubTituloGris,
                    fontWeight = FontWeight.Medium
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            LinearProgressIndicator(
                progress = { progreso },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(RoundedCornerShape(4.dp)),
                color = colorBarra,
                trackColor = GrisBorde
            )
        }
    }
}

// ---------------------------
// VISTA PREVIA (PREVIEW)
// ---------------------------
@Preview(showBackground = true)
@Composable
fun PresupuestoScreenPreview() {
    val viewModel: MovimientoViewModel = viewModel()
    PresupuestoScreen(
        viewModel = viewModel,
        onVolver = {}
    )
}