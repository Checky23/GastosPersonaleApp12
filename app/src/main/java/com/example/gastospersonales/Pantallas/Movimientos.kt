package com.example.gastospersonales.Pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gastospersonales.Data.RegistroDeMovimientos
import com.example.gastospersonales.Pantallas.VistaGenerales.BarraBusqueda
import com.example.gastospersonales.ViewModel.MovimientoViewModel
import androidx.compose.foundation.lazy.items
import com.example.gastospersonales.Pantallas.Inicio.MovimientosRecientesScreen

// Definición de colores basados en tu diseño
val BgColor = Color(0xFFF7F9FC)
val TextPrimary = Color(0xFF1A1F36)
val TextSecondary = Color(0xFF8F9BB3)
val GreenActive = Color(0xFF338258)
val RedExpense = Color(0xFFE55353)
val ChipBorder = Color(0xFFE4E9F2)


@Composable
fun MovimientosScreen() {
    // Datos de ejemplo simulando el historial financiero


    var BusquedaDelUsuario by remember { mutableStateOf("") }
        val viewModel: MovimientoViewModel = viewModel()
    viewModel.movimientos.add(RegistroDeMovimientos("comida","pollo asado",":D",5052,true))
    viewModel.movimientos.add(RegistroDeMovimientos("comida","pollo asado",":D",5052,true))
    viewModel.movimientos.add(RegistroDeMovimientos("comida","pollo asado",":D",5052,true))
    viewModel.movimientos.add(RegistroDeMovimientos("comida","pollo asado",":D",5052,true))
    viewModel.movimientos.add(RegistroDeMovimientos("comida","pollo asado",":D",5052,true))
    viewModel.movimientos.add(RegistroDeMovimientos("comida","pollo asado",":D",5052,true))
    viewModel.movimientos.add(RegistroDeMovimientos("comida","pollo asado",":D",5052,true))


    val movimientosFiltrados = viewModel.movimientos.filter { movimiento ->
        movimiento.Gasto.contains(BusquedaDelUsuario, ignoreCase = true) ||
                movimiento.Descripcion.contains(BusquedaDelUsuario, ignoreCase = true)
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(BgColor)
            .padding(horizontal = 24.dp)
    ) {
        // Referencias para las constraints del layout principal
        val (title, subtitle, searchBar, chips, list) = createRefs()

        Text(
            text = "Movimientos",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary,
            modifier = Modifier.constrainAs(title) {
                top.linkTo(parent.top, margin = 48.dp)
                start.linkTo(parent.start)
            }
        )

        Text(
            text = "Historial financiero",
            fontSize = 14.sp,
            color = TextSecondary,
            modifier = Modifier.constrainAs(subtitle) {
                top.linkTo(title.bottom, margin = 4.dp)
                start.linkTo(parent.start)
            }
        )

        BarraBusqueda(
            texto = BusquedaDelUsuario,
            onTextoChange = {
                BusquedaDelUsuario = it
            },
            placeholder = "Buscar movimiento",
            modifier = Modifier.constrainAs(searchBar) {
                top.linkTo(subtitle.bottom, margin = 24.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }
        )


        // Fila de Filtros (Todos, Gastos, Ingresos)
        Row(
            modifier = Modifier.constrainAs(chips) {
                top.linkTo(searchBar.bottom, margin = 16.dp)
                start.linkTo(parent.start)
            },
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            FilterChip("Todos", isSelected = true)
            FilterChip("Gastos", isSelected = false)
            FilterChip("Ingresos", isSelected = false)
        }

        // Lista de Movimientos (Scrollable)
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.constrainAs(list) {
                top.linkTo(chips.bottom, margin = 24.dp)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            },
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            items(viewModel.movimientos) { mov ->
                MovimientosRecientesScreen(
                    Gasto = mov.Gasto,
                    CantidadDelMovimiento = mov.Monto,
                    TipoDeMovimiento = mov.TipoDeMovimiento,
                    Descripcion = mov.Descripcion
                )
            }
        }
    }
}

@Composable
fun FilterChip(text: String, isSelected: Boolean) {
    val bgColor = if (isSelected) GreenActive else Color.White
    val textColor = if (isSelected) Color.White else TextSecondary
    val modifier = if (isSelected) {
        Modifier.background(bgColor, RoundedCornerShape(20.dp))
    } else {
        Modifier
            .background(bgColor, RoundedCornerShape(20.dp))
            .border(1.dp, ChipBorder, RoundedCornerShape(20.dp))
    }

    Box(
        modifier = modifier.padding(horizontal = 20.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}
@Preview
@Composable
fun MovimientosScreenPreview() {
    MovimientosScreen()
}