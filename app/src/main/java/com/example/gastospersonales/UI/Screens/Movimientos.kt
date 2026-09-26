package com.example.gastospersonales.UI.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gastospersonales.UI.ComponentesVisuales.BarraBusqueda
import com.example.gastospersonales.UI.ComponentesVisuales.MovimientosRecientesScreen
import com.example.gastospersonales.UI.Extenciones.clickableUnico
import com.example.gastospersonales.UI.Navegacion.Screen
import com.example.gastospersonales.UI.Temas.Dimens
import com.example.gastospersonales.UI.Temas.Fondo
import com.example.gastospersonales.UI.Temas.GrisBorde
import com.example.gastospersonales.UI.Temas.NegroTitulo
import com.example.gastospersonales.UI.Temas.SubTituloGris
import com.example.gastospersonales.UI.Temas.TextSizes
import com.example.gastospersonales.UI.Temas.VerdeApp
import com.example.gastospersonales.ViewModel.MovimientoViewModel
//
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

    val cicleLifeOwner = LocalLifecycleOwner.current

    val movimientos by viewModel.movimientos.collectAsState()

    var busquedaDelUsuario by remember {
        mutableStateOf("")
    }

    var filtroSeleccionado by remember {
        mutableStateOf(TipoFiltro.TODOS)
    }

    // Filtrado por búsqueda y por tipo
    val movimientosFiltrados = movimientos
        .filter { movimiento ->

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
            .background(Fondo)
            .padding(horizontal = Dimens.EspacioGrande)
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
            fontSize = TextSizes.Titulo1,
            fontWeight = FontWeight.Bold,
            color = NegroTitulo,
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
            fontSize = TextSizes.Cuerpo,
            color = SubTituloGris,
            modifier = Modifier.constrainAs(subtitle) {
                top.linkTo(
                    title.bottom,
                    margin = Dimens.EspacioPequeno
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
                    margin = Dimens.EspacioGrande
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
                    margin = Dimens.EspacioMedio
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
                Arrangement.spacedBy(Dimens.EspacioPequeno),

            modifier =
                Modifier.constrainAs(list) {

                    top.linkTo(
                        chips.bottom,
                        margin = Dimens.EspacioGrande
                    )

                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                },

            contentPadding =
                PaddingValues(bottom = 90.dp)
        ) {

            if (movimientosFiltrados.isEmpty()) {

                item {

                    Text(
                        text =
                            "No se encontraron movimientos",
                        color = SubTituloGris,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,


                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                vertical = Dimens.EspacioGrande
                            )
                    )
                }

            } else {

                items(
                    items = movimientosFiltrados,
                    key = { movimiento ->
                        movimiento.Id
                    }
                ) { movimiento ->

                    MovimientosRecientesScreen(
                        Gasto = movimiento.Gasto,
                        CantidadDelMovimiento = movimiento.Monto,
                        TipoDeMovimiento = movimiento.TipoDeMovimiento,
                        Descripcion = movimiento.Descripcion,
                        modifier =
                            Modifier.clickableUnico(500L,{

                                if (cicleLifeOwner.lifecycle.currentState
                                    .isAtLeast(Lifecycle.State.STARTED)){

                                    navController.navigate(
                                        "${Screen.DetalleMovimientoScreen.ruta}/${movimiento.Id}"
                                    ){
                                        launchSingleTop = true }

                                }
                            }
                            )
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
            VerdeApp
        else
            Color.White

    val textColor =
        if (isSelected)
            Color.White
        else
            SubTituloGris

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
                    GrisBorde,
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
                vertical = Dimens.EspacioPequeno
            ),

        contentAlignment =
            Alignment.Center
    ) {

        Text(
            text = text,
            color = textColor,
            fontSize = TextSizes.Cuerpo,
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