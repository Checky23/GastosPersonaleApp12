package com.example.gastospersonales.UI.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gastospersonales.UI.ComponentesVisuales.BarraBusqueda
import com.example.gastospersonales.UI.ComponentesVisuales.MovimientosRecientesScreen
import com.example.gastospersonales.UI.Navegacion.Screen
import com.example.gastospersonales.ViewModel.MovimientoViewModel

val BgColor = Color(0xFFF7F9FC)
val TextPrimary = Color(0xFF1A1F36)
val TextSecondary = Color(0xFF8F9BB3)
val GreenActive = Color(0xFF338258)
val RedExpense = Color(0xFFE55353)
val ChipBorder = Color(0xFFE4E9F2)

enum class TipoFiltro {
    TODOS,
    GASTOS,
    INGRESOS
}

@Composable
fun MovimientosScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: MovimientoViewModel = viewModel()
) {

    val movimientos by viewModel.movimientos.collectAsState()

    var busquedaDelUsuario by remember {
        mutableStateOf("")
    }

    var filtroSeleccionado by remember {
        mutableStateOf(TipoFiltro.TODOS)
    }

    // Filtrado por búsqueda y por tipo
    val movimientosFiltrados = movimientos
        .withIndex()
        .filter { (_, movimiento) ->

            val coincideBusqueda =
                movimiento.Gasto.contains(
                    busquedaDelUsuario,
                    ignoreCase = true
                ) ||
                        movimiento.Descripcion.contains(
                            busquedaDelUsuario,
                            ignoreCase = true
                        )

            val coincideFiltro = when (filtroSeleccionado) {

                TipoFiltro.TODOS -> true

                TipoFiltro.GASTOS ->
                    !movimiento.TipoDeMovimiento

                TipoFiltro.INGRESOS ->
                    movimiento.TipoDeMovimiento
            }

            coincideBusqueda && coincideFiltro
        }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(BgColor)
            .padding(horizontal = 24.dp)
    ) {

        val (
            title,
            subtitle,
            searchBar,
            chips,
            list
        ) = createRefs()

        // ---------------------------
        // TÍTULO
        // ---------------------------

        Text(
            text = "Movimientos",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary,
            modifier = Modifier.constrainAs(title) {
                top.linkTo(
                    parent.top,
                    margin = 48.dp
                )

                start.linkTo(parent.start)
            }
        )

        // ---------------------------
        // SUBTÍTULO
        // ---------------------------

        Text(
            text = "Historial financiero",
            fontSize = 14.sp,
            color = TextSecondary,
            modifier = Modifier.constrainAs(subtitle) {
                top.linkTo(
                    title.bottom,
                    margin = 4.dp
                )

                start.linkTo(parent.start)
            }
        )

        // ---------------------------
        // BARRA DE BÚSQUEDA
        // ---------------------------

        BarraBusqueda(
            texto = busquedaDelUsuario,

            onTextoChange = {
                busquedaDelUsuario = it
            },

            placeholder = "Buscar movimiento",

            modifier = Modifier.constrainAs(searchBar) {

                top.linkTo(
                    subtitle.bottom,
                    margin = 24.dp
                )

                start.linkTo(parent.start)

                end.linkTo(parent.end)

                width = Dimension.fillToConstraints
            }
        )

        // ---------------------------
        // FILTROS
        // ---------------------------

        Row(

            modifier = Modifier.constrainAs(chips) {

                top.linkTo(
                    searchBar.bottom,
                    margin = 16.dp
                )

                start.linkTo(parent.start)
            },

            horizontalArrangement =
                Arrangement.spacedBy(12.dp)
        ) {

            FilterChip(
                text = "Todos",

                isSelected =
                    filtroSeleccionado ==
                            TipoFiltro.TODOS,

                onClick = {
                    filtroSeleccionado =
                        TipoFiltro.TODOS
                }
            )

            FilterChip(
                text = "Gastos",

                isSelected =
                    filtroSeleccionado ==
                            TipoFiltro.GASTOS,

                onClick = {
                    filtroSeleccionado =
                        TipoFiltro.GASTOS
                }
            )

            FilterChip(
                text = "Ingresos",

                isSelected =
                    filtroSeleccionado ==
                            TipoFiltro.INGRESOS,

                onClick = {
                    filtroSeleccionado =
                        TipoFiltro.INGRESOS
                }
            )
        }

        // ---------------------------
        // LISTA
        // ---------------------------

        LazyColumn(

            verticalArrangement =
                Arrangement.spacedBy(8.dp),

            modifier =
                Modifier.constrainAs(list) {

                    top.linkTo(
                        chips.bottom,
                        margin = 24.dp
                    )

                    bottom.linkTo(parent.bottom)

                    start.linkTo(parent.start)

                    end.linkTo(parent.end)

                    width =
                        Dimension.fillToConstraints

                    height =
                        Dimension.fillToConstraints
                },

            contentPadding =
                PaddingValues(
                    bottom = 90.dp
                )
        ) {

            if (movimientosFiltrados.isEmpty()) {

                item {

                    Text(
                        text =
                            "No se encontraron movimientos",

                        color = TextSecondary,

                        fontSize = 14.sp,

                        textAlign =
                            TextAlign.Center,

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                vertical = 24.dp
                            )
                    )
                }

            } else {

                items(
                    items = movimientosFiltrados,

                    key = {
                        it.index
                    }
                ) { item ->

                    val indiceOriginal =
                        item.index

                    val movimiento =
                        item.value

                    MovimientosRecientesScreen(

                        Gasto =
                            movimiento.Gasto,

                        CantidadDelMovimiento =
                            movimiento.Monto,

                        TipoDeMovimiento =
                            movimiento.TipoDeMovimiento,

                        Descripcion =
                            movimiento.Descripcion,

                        modifier =
                            Modifier.clickable {

                                navController.navigate(
                                    "${Screen.DetalleMovimientoScreen.ruta}/$indiceOriginal"
                                )
                            }
                    )
                }
            }
        }
    }
}

@Composable
fun FilterChip(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {

    val bgColor =
        if (isSelected)
            GreenActive
        else
            Color.White

    val textColor =
        if (isSelected)
            Color.White
        else
            TextSecondary

    val modifier =
        if (isSelected) {

            Modifier.background(
                bgColor,
                RoundedCornerShape(20.dp)
            )

        } else {

            Modifier
                .background(
                    bgColor,
                    RoundedCornerShape(20.dp)
                )
                .border(
                    1.dp,
                    ChipBorder,
                    RoundedCornerShape(20.dp)
                )
        }

    Box(

        modifier = modifier
            .clickable(
                onClick = onClick
            )
            .padding(
                horizontal = 20.dp,
                vertical = 8.dp
            ),

        contentAlignment =
            Alignment.Center
    ) {

        Text(
            text = text,
            color = textColor,
            fontSize = 13.sp,
            fontWeight =
                FontWeight.SemiBold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MovimientosScreenPreview() {

    MovimientosScreen()
}