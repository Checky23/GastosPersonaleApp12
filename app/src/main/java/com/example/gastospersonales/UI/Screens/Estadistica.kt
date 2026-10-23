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
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.gastospersonales.UI.Temas.VerdeClaro
import com.example.gastospersonales.ViewModel.MovimientoViewModel

@Composable
fun EstadisticaScreen(
    viewModel: MovimientoViewModel,
    onVolver: () -> Unit
) {
    val totalGastos = viewModel.totalGastos
    val gastosPorCategoria = viewModel.gastosPorCategoria
    val totalIngresos = viewModel.totalIngresos
    val ahorroEstimado = (totalIngresos - totalGastos).coerceAtLeast(0)

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
                    text = "Estadísticas",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = NegroTitulo
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Entiende en qué se va tu dinero.",
                    fontSize = 14.sp,
                    color = SubTituloGris
                )
            }
        }

        // SELECTOR DE MES
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(ColorBlanco)
                    .border(1.dp, GrisBorde, RoundedCornerShape(16.dp))
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Agosto 2026",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = NegroTitulo
                    )
                    Text(
                        text = "▼",
                        fontSize = 12.sp,
                        color = SubTituloGris
                    )
                }
            }
        }

        // TARJETA PRINCIPAL: GASTOS DEL MES
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(ColorBlanco)
                    .border(1.dp, GrisBorde, RoundedCornerShape(20.dp))
                    .padding(20.dp)
            ) {
                Column {
                    Text(
                        text = "Gastos del mes",
                        fontSize = 13.sp,
                        color = SubTituloGris,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "C$ $totalGastos",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = NegroTitulo
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    if (gastosPorCategoria.isEmpty()) {
                        Text(
                            text = "Sin movimientos registrados",
                            fontSize = 13.sp,
                            color = SubTituloGris
                        )
                    } else {
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            gastosPorCategoria.entries.take(3).forEach { entry ->
                                val categoria = entry.key
                                val monto = entry.value
                                val porcentaje = if (totalGastos > 0) (monto * 100) / totalGastos else 0
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(text = categoria, fontSize = 13.sp, color = SubTituloGris)
                                    Text(text = "$porcentaje%", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = NegroTitulo)
                                }
                            }
                        }
                    }
                }
            }
        }

        // TÍTULO DE SECCIÓN: GASTO POR CATEGORÍA
        item {
            Text(
                text = "Gasto por categoría",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = NegroTitulo,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        // LISTA DE BARRAS DE PROGRESO POR CATEGORÍA
        if (gastosPorCategoria.isEmpty()) {
            item {
                Text(
                    text = "No hay datos suficientes para mostrar barras.",
                    fontSize = 14.sp,
                    color = SubTituloGris,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        } else {
            items(gastosPorCategoria.toList()) { item ->
                val categoria = item.first
                val monto = item.second
                val porcentajeInt = if (totalGastos > 0) (monto * 100) / totalGastos else 0
                val progresoFloat = if (totalGastos > 0) (monto.toFloat() / totalGastos.toFloat()).coerceIn(0f, 1f) else 0f

                // Asignamos el color correspondiente basado en el nombre de la categoría
                val colorBarra = when (categoria.lowercase()) {
                    "comida" -> ColorComida
                    "transporte" -> ColorTransporte
                    "servicios" -> ColorServicios
                    else -> VerdeApp // Color por defecto si es otra categoría
                }

                ItemBarraCategoriaEstadistica(
                    categoria = categoria,
                    porcentaje = porcentajeInt,
                    progreso = progresoFloat,
                    colorBarra = colorBarra
                )
            }
        }

        // TARJETA INFERIOR: AHORRO ESTIMADO
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(VerdeClaro)
                    .padding(20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Ahorro estimado",
                            fontSize = 13.sp,
                            color = SubTituloGris,
                            fontWeight = FontWeight.Medium
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "C$ $ahorroEstimado",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = VerdeApp
                        )
                    }
                    Text(
                        text = "↑ 12%",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = VerdeApp
                    )
                }
            }
        }
    }
}

@Composable
fun ItemBarraCategoriaEstadistica(
    categoria: String,
    porcentaje: Int,
    progreso: Float,
    colorBarra: androidx.compose.ui.graphics.Color
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
                    text = "$porcentaje%",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = SubTituloGris
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
fun EstadisticaScreenPreview() {
    val viewModel: MovimientoViewModel = viewModel()
    EstadisticaScreen(
        viewModel = viewModel,
        onVolver = {}
    )
}