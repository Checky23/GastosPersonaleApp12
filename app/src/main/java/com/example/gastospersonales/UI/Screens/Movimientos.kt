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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.gastospersonales.Data.Model.RegistroDeMovimientos
import com.example.gastospersonales.UI.ComponentesVisuales.MovimientosRecientesScreen
import com.example.gastospersonales.UI.ComponentesVisuales.TextosTitulo
import com.example.gastospersonales.UI.Extenciones.clickableUnico
import com.example.gastospersonales.UI.Temas.Dimens
import com.example.gastospersonales.UI.Temas.Fondo
import com.example.gastospersonales.UI.Temas.GrisBorde
import com.example.gastospersonales.UI.Temas.SubTituloGris
import com.example.gastospersonales.UI.Temas.TextSizes
import com.example.gastospersonales.UI.Temas.VerdeApp
import com.example.gastospersonales.ViewModel.MovimientoViewModel


@Composable
fun MovimientosScreen(
    viewModel: MovimientoViewModel,
    onMovimientoClick: (Int) -> Unit
) {

    val movimientos by viewModel.movimientos.collectAsStateWithLifecycle()

    MovimientosScreenContent(
        movimientos = movimientos,
        onMovimientoClick = onMovimientoClick
    )
}


@Composable
private fun MovimientosScreenContent(
    movimientos: List<RegistroDeMovimientos>,
    onMovimientoClick: (Int) -> Unit
) {

    val lifecycleOwner = LocalLifecycleOwner.current

    var busquedaDelUsuario by remember {
        mutableStateOf("")
    }

    var filtroSeleccionado by remember {
        mutableStateOf(TipoFiltro.TODOS)
    }


    val movimientosFiltrados = movimientos.filter { movimiento ->

        val coincideBusqueda =
            movimiento.Gasto.contains(
                busquedaDelUsuario,
                ignoreCase = true
            ) ||
                    movimiento.Descripcion.contains(
                        busquedaDelUsuario,
                        ignoreCase = true
                    )

        val coincideFiltro =
            when (filtroSeleccionado) {

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

        TextosTitulo(
            texto = "Movimientos",
            fontSize = TextSizes.Titulo1,
            modifier = Modifier.constrainAs(title) {
                top.linkTo(
                    parent.top,
                    margin = 48.dp
                )

                start.linkTo(parent.start)
            }

        )


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
                    filtroSeleccionado == TipoFiltro.TODOS,
                onClick = {
                    filtroSeleccionado = TipoFiltro.TODOS
                }
            )

            FilterChip(
                text = "Gastos",
                isSelected =
                    filtroSeleccionado == TipoFiltro.GASTOS,
                onClick = {
                    filtroSeleccionado = TipoFiltro.GASTOS
                }
            )

            FilterChip(
                text = "Ingresos",
                isSelected =
                    filtroSeleccionado == TipoFiltro.INGRESOS,
                onClick = {
                    filtroSeleccionado = TipoFiltro.INGRESOS
                }
            )
        }


        LazyColumn(
            verticalArrangement =
                Arrangement.spacedBy(Dimens.EspacioPequeno),

            modifier = Modifier.constrainAs(list) {

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
                        text = "No se encontraron movimientos",
                        color = SubTituloGris,
                        fontSize = TextSizes.Cuerpo,
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
                        FechaHora = movimiento.FechaHora,

                        modifier = Modifier.clickableUnico(
                            500L
                        ) {

                            if (
                                lifecycleOwner.lifecycle.currentState
                                    .isAtLeast(Lifecycle.State.STARTED)
                            ) {
                                onMovimientoClick(movimiento.Id)
                            }
                        }
                    )
                }
            }
        }
    }
}


@Composable
private fun FilterChip(
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

        contentAlignment = Alignment.Center
    ) {

        Text(
            text = text,
            color = textColor,
            fontSize = TextSizes.Cuerpo,
            fontWeight = FontWeight.SemiBold
        )
    }
}


@Preview(showBackground = true)
@Composable
fun MovimientosScreenPreview() {

    MovimientosScreenContent(
        movimientos = emptyList(),
        onMovimientoClick = {}
    )
}

private enum class TipoFiltro {
    TODOS,
    GASTOS,
    INGRESOS
}


@Composable
private fun BarraBusqueda(
    texto: String,
    onTextoChange: (String) -> Unit,
    placeholder: String = "Buscar...",
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = texto,
        onValueChange = onTextoChange,
        modifier = modifier.fillMaxWidth(),
        placeholder = {
            Text(text = placeholder)
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Buscar"
            )
        },
        trailingIcon = {
            if (texto.isNotEmpty()) {
                IconButton(
                    onClick = {
                        onTextoChange("")
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Limpiar búsqueda"
                    )
                }
            }
        },
        singleLine = true
    )
}