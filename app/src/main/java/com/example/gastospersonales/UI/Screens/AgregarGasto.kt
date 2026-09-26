package com.example.gastospersonales.UI.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gastospersonales.UI.ComponentesVisuales.SelectorTipoMovimiento
import com.example.gastospersonales.UI.Temas.Fondo
import com.example.gastospersonales.UI.Temas.GrisBorde
import com.example.gastospersonales.UI.Temas.NegroTitulo
import com.example.gastospersonales.UI.Temas.SubTituloGris
import com.example.gastospersonales.UI.Temas.VerdeApp
import com.example.gastospersonales.UI.Temas.VerdeClaro
import com.example.gastospersonales.ViewModel.MovimientoViewModel
import kotlinx.coroutines.launch
//
@Composable
fun AgregarGastosScreen(
    navController: NavHostController,
    viewModel: MovimientoViewModel = viewModel(),
    movimientoId: Int
) {

    // ---------------- ESTADOS ----------------

    var categoriaSeleccionada by remember {
        mutableStateOf("Comida")
    }

    var monto by remember {
        mutableStateOf("")
    }

    var fecha by remember {
        mutableStateOf("26 ago 2026")
    }

    var descripcion by remember {
        mutableStateOf("")
    }

    val esEdicion = movimientoId != 0

    // Observamos la lista para buscar el movimiento si es edición
    val movimientos by viewModel.movimientos.collectAsState()
    
    // Cuando la pantalla se abre, revisa si hay un ID.
    // Si lo hay, busca el movimiento y "autocompleta" los campos.
    LaunchedEffect(movimientoId) {
        if (esEdicion) {
            val movimientoAEditar = movimientos.find { it.Id == movimientoId }
            if (movimientoAEditar != null) {
                categoriaSeleccionada = movimientoAEditar.Gasto
                monto = movimientoAEditar.Monto.toString()
                descripcion = movimientoAEditar.Descripcion
                // Si en el futuro tienes fecha en el modelo, la asignas aquí también
            }
        }
    }


    // ---------------- LISTA DE CATEGORÍAS ----------------

    val categorias = listOf(
        "🍔\nComida",
        "🚂\nTransporte",
        "🏠\nHogar",
        "💡\nServicios",
        "🎮\nOcioa",
        "🎛️\nOtrosa",
        "🍔\nComidaa",
        "🚂\nTransportea",
        "🏠\nHogara",
        "💡\nServicioss",
        "🎮\nOciod",
        "🎛️\nOtrosv"
    )


    // ---------------- SCROLL ----------------

    val scrollState = rememberScrollState()

    val scope = rememberCoroutineScope()

    // Para llevar cada TextField visible cuando recibe el foco
    val montoRequester = remember {
        BringIntoViewRequester()
    }

    val fechaRequester = remember {
        BringIntoViewRequester()
    }

    val descripcionRequester = remember {
        BringIntoViewRequester()
    }


    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Fondo)
            .padding(24.dp)
    ) {

        val (
            TextsPresentacion,
            bottomButton,
            cancelText
        ) = createRefs()


        // ======================================================
        // FORMULARIO SCROLLEABLE
        // ======================================================

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
                .imePadding()
                .constrainAs(TextsPresentacion) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .padding(bottom = 110.dp)
        ) {

            // ---------- ENCABEZADO ----------

            Text(
                text = "Agregar Movimiento",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = NegroTitulo
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "Registra una compra en pocos segundos.",
                fontSize = 14.sp,
                color = SubTituloGris
            )


            // ======================================================
            // MONTO
            // ======================================================

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            Text(
                text = "Monto",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = NegroTitulo
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            TextField(
                value = monto,

                onValueChange = {
                    monto = it
                },

                singleLine = true,

                textStyle = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = NegroTitulo
                ),

                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal
                ),

                placeholder = {
                    Text(
                        text = "C$ 0.00",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = NegroTitulo
                    )
                },

                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = VerdeApp,
                    unfocusedIndicatorColor = VerdeApp
                ),

                shape = RoundedCornerShape(12.dp),

                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .bringIntoViewRequester(montoRequester)
                    .onFocusChanged { focusState ->

                        if (focusState.isFocused) {

                            scope.launch {
                                montoRequester.bringIntoView()
                            }
                        }
                    }
            )


            // ======================================================
            // CATEGORÍA
            // ======================================================

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            Text(
                text = "Categoría",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = NegroTitulo
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )


            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(categorias.chunked(2)) { grupo ->

                    Column(
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        grupo.forEach { categoria ->

                            GridItem(
                                text = categoria,

                                isSelected =
                                    categoriaSeleccionada ==
                                            categoria.substringAfter("\n"),

                                onClick = {
                                    categoriaSeleccionada =
                                        categoria.substringAfter("\n")
                                },

                                modifier = Modifier.width(100.dp)
                            )
                        }
                    }
                }
            }


            // ======================================================
            // FECHA
            // ======================================================

            Text(
                text = "Fecha",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = NegroTitulo
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )


            TextField(

                value = fecha,

                onValueChange = {
                    fecha = it
                },

                singleLine = true,

                textStyle = TextStyle(
                    fontSize = 14.sp,
                    color = NegroTitulo
                ),

                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = VerdeApp,
                    unfocusedIndicatorColor = VerdeApp
                ),

                shape = RoundedCornerShape(16.dp),

                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .bringIntoViewRequester(fechaRequester)
                    .onFocusChanged { focusState ->

                        if (focusState.isFocused) {

                            scope.launch {
                                fechaRequester.bringIntoView()
                            }
                        }
                    }
            )


            Spacer(
                modifier = Modifier.height(16.dp)
            )


            // ======================================================
            // DESCRIPCIÓN
            // ======================================================

            Text(
                text = "Descripción (opcional)",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = NegroTitulo
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )


            TextField(

                value = descripcion,

                onValueChange = {
                    descripcion = it
                },

                singleLine = true,

                placeholder = {
                    Text(
                        text = "¿Qué compraste?",
                        color = SubTituloGris
                    )
                },

                textStyle = TextStyle(
                    fontSize = 14.sp,
                    color = NegroTitulo
                ),

                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = VerdeApp,
                    unfocusedIndicatorColor = VerdeApp
                ),

                shape = RoundedCornerShape(16.dp),

                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .bringIntoViewRequester(descripcionRequester)
                    .onFocusChanged { focusState ->

                        if (focusState.isFocused) {

                            scope.launch {
                                descripcionRequester.bringIntoView()
                            }
                        }
                    }
            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            SelectorTipoMovimiento()


            // Espacio final para poder hacer scroll
            // y que el último campo no quede pegado al teclado.
            Spacer(
                modifier = Modifier.height(100.dp)
            )


        }


        // ======================================================
        // BOTÓN GUARDAR
        // ======================================================

        Button(

            onClick = {

                if (
                    monto.isNotBlank() &&
                    categoriaSeleccionada.isNotBlank() &&
                    fecha.isNotBlank()
                ) {

                    viewModel.agregarMovimiento(
                        gasto = categoriaSeleccionada,
                        descripcion = descripcion,
                        monto = monto
                    )

                    navController.popBackStack()
                }
            },

            shape = RoundedCornerShape(12.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = VerdeApp
            ),

            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .constrainAs(bottomButton) {

                    start.linkTo(parent.start)
                    end.linkTo(parent.end)

                    bottom.linkTo(
                        parent.bottom,
                        margin = 30.dp
                    )
                }

        ) {

            Text(
                text = "Guardar gasto",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }


        // ======================================================
        // CANCELAR
        // ======================================================

        Text(

            text = "Cancelar",

            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = SubTituloGris,

            modifier = Modifier
                .clickable {
                    navController.popBackStack()
                }
                .constrainAs(cancelText) {

                    bottom.linkTo(
                        parent.bottom
                    )

                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
    }
}


// ======================================================
// TARJETA DE CATEGORÍA
// ======================================================

@Composable
fun GridItem(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val currentBgColor =
        if (isSelected) VerdeClaro
        else Color.White

    val currentBorderColor =
        if (isSelected) VerdeApp
        else GrisBorde

    val currentTextColor =
        if (isSelected) VerdeApp
        else NegroTitulo


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

            .clickable {
                onClick()
            }
    ) {

        Text(
            text = text,
            color = currentTextColor,
            fontSize = 12.sp,

            fontWeight =
                if (isSelected)
                    FontWeight.Bold
                else
                    FontWeight.Normal,

            textAlign = TextAlign.Center
        )
    }
}


// ======================================================
// PREVIEW
// ======================================================
@Preview(showBackground = true)
@Composable
fun PreviewAgregarGasto() {

    val navController = rememberNavController()

    AgregarGastosScreen(
        navController = navController,
        movimientoId = 0
    )
}

