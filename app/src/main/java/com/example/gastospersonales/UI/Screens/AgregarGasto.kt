package com.example.gastospersonales.UI.Screens

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.compose.rememberNavController
import com.example.gastospersonales.Data.Model.RegistroDeMovimientos
import com.example.gastospersonales.UI.ComponentesVisuales.SelectorTipoMovimiento
import com.example.gastospersonales.UI.Temas.Fondo
import com.example.gastospersonales.UI.Temas.GrisBorde
import com.example.gastospersonales.UI.Temas.NegroTitulo
import com.example.gastospersonales.UI.Temas.SubTituloGris
import com.example.gastospersonales.UI.Temas.VerdeApp
import com.example.gastospersonales.UI.Temas.VerdeClaro
import com.example.gastospersonales.ViewModel.MovimientoViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime


// PANTALLA PRINCIPAL (STATEFUL - Habla con el ViewModel)
@Composable
fun AgregarGastosScreen(
    viewModel: MovimientoViewModel,
    movimientoId: Int,
    onTerminar: () -> Unit
) {
    // 1. Recolección de estado de la base de datos
    val movimientos by viewModel.movimientos.collectAsState()
    val movimientoOriginal = movimientos.find { it.Id == movimientoId }

    // 2. Delegación a la UI Stateless
    AgregarGastosContent(
        movimientoId = movimientoId,
        movimientoOriginal = movimientoOriginal,
        onTerminar = onTerminar,
        onAgregarMovimiento = viewModel::agregarMovimiento,
        onEditarMovimiento = viewModel::editarMovimiento
    )
}


// CONTENIDO DE LA UI (STATELESS - Solo dibuja y emite eventos)
@Composable
fun AgregarGastosContent(
    movimientoId: Int,
    movimientoOriginal: RegistroDeMovimientos?,
    onTerminar: () -> Unit,
    onAgregarMovimiento: (RegistroDeMovimientos) -> Unit,
    onEditarMovimiento: (RegistroDeMovimientos) -> Unit
) {
    val context = LocalContext.current
    val esEdicion = movimientoId != 0

    // Estado local del formulario
    var movimiento by remember(movimientoOriginal) {
        mutableStateOf(
            movimientoOriginal ?: RegistroDeMovimientos(
                Gasto = "Comida",
                Descripcion = "",
                Iconos = "",
                Monto = 0,
                TipoDeMovimiento = false,
                FechaHora = LocalDateTime.now()
            )
        )
    }


    // Datos estáticos y controladores de UI
    val categorias = listOf(
        "🍔\nComida", "🚂\nTransporte", "🏠\nHogar",
        "💡\nServicios", "🎮\nOcio", "🎛️\nOtros"
    )
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()

    // Controladores de scroll automático para el teclado
    val montoRequester = remember { BringIntoViewRequester() }
    val fechaRequester = remember { BringIntoViewRequester() }
    val descripcionRequester = remember { BringIntoViewRequester() }


    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Fondo)
            .padding(24.dp)
    ) {
        val (contenido, botonGuardar, textoCancelar) = createRefs()

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
                .imePadding()
                .constrainAs(contenido) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .padding(bottom = 110.dp)
        ) {
            EncabezadoMovimiento(esEdicion = esEdicion)
            Spacer(modifier = Modifier.height(20.dp))

            CampoMonto(
                monto = if (movimiento.Monto == 0) "" else movimiento.Monto.toString(),
                onMontoChange = { newValue ->
                    movimiento = movimiento.copy(Monto = newValue.toIntOrNull() ?: 0)
                },
                montoRequester = montoRequester,
                scope = scope
            )
            Spacer(modifier = Modifier.height(20.dp))

            SelectorCategoria(
                categorias = categorias,
                categoriaSeleccionada = movimiento.Gasto,
                onCategoriaSeleccionada = { categoria ->
                    movimiento = movimiento.copy(Gasto = categoria)
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            CampoFecha(
                fecha = movimiento.FechaHora.toLocalDate().toString(),
                fechaRequester = fechaRequester,
                scope = scope
            )
            Spacer(modifier = Modifier.height(16.dp))

            CampoDescripcion(
                descripcion = movimiento.Descripcion,
                onDescripcionChange = { text ->
                    movimiento = movimiento.copy(Descripcion = text)
                },
                descripcionRequester = descripcionRequester,
                scope = scope
            )
            Spacer(modifier = Modifier.height(20.dp))

            SelectorTipoMovimiento(
                valorSeleccionado = if (movimiento.TipoDeMovimiento) "Ingreso" else "Egreso",
                onValorSeleccionadoChange = { seleccion ->
                    movimiento = movimiento.copy(TipoDeMovimiento = seleccion == "Ingreso")
                }
            )
            Spacer(modifier = Modifier.height(100.dp))
        }


        BotonGuardarMovimiento(
            esEdicion = esEdicion,
            onClick = {
                // Validación local antes de enviar al ViewModel
                when {
                    movimiento.Monto <= 0 -> {
                        Toast.makeText(context, "Ingresa un monto válido", Toast.LENGTH_SHORT).show()
                    }
                    movimiento.Gasto.isBlank() -> {
                        Toast.makeText(context, "Selecciona una categoría", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        if (esEdicion) onEditarMovimiento(movimiento) else onAgregarMovimiento(movimiento)
                        onTerminar()
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .constrainAs(botonGuardar) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom, margin = 30.dp)
                }
        )

        Text(
            text = "Cancelar",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = SubTituloGris,
            modifier = Modifier
                .clickable { onTerminar() }
                .padding(8.dp) // Añadido un pequeño padding para facilitar el clic
                .constrainAs(textoCancelar) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
    }
}


// COMPONENTES VISUALES EXTERNALIZABLES
// (Te sugiero mover todos estos a un archivo en UI/ComponentesVisuales)

@Composable
fun EncabezadoMovimiento(esEdicion: Boolean) {
    Column {
        Text(
            text = if (esEdicion) "Editar Movimiento" else "Agregar Movimiento",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = NegroTitulo
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Registra una transacción en pocos segundos.",
            fontSize = 14.sp,
            color = SubTituloGris
        )
    }
}

@Composable
fun CampoMonto(
    monto: String,
    onMontoChange: (String) -> Unit,
    montoRequester: BringIntoViewRequester,
    scope: kotlinx.coroutines.CoroutineScope
) {
    Column {
        Text(text = "Monto", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = NegroTitulo)
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = monto,
            onValueChange = onMontoChange,
            singleLine = true,
            textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold, color = NegroTitulo),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            placeholder = { Text(text = "C$ 0.00", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = SubTituloGris) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = VerdeApp,
                unfocusedIndicatorColor = Color.Transparent // Ocultamos la línea cuando no hay foco para más limpieza
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .bringIntoViewRequester(montoRequester)
                .onFocusChanged {
                    if (it.isFocused) scope.launch { montoRequester.bringIntoView() }
                }
        )
    }
}

@Composable
fun SelectorCategoria(
    categorias: List<String>,
    categoriaSeleccionada: String,
    onCategoriaSeleccionada: (String) -> Unit
) {
    Column {
        Text(text = "Categoría", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = NegroTitulo)
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(categorias.chunked(2)) { grupo ->
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    grupo.forEach { categoria ->
                        val nombreCategoria = categoria.substringAfter("\n")
                        GridItem(
                            text = categoria,
                            isSelected = categoriaSeleccionada == nombreCategoria,
                            onClick = { onCategoriaSeleccionada(nombreCategoria) },
                            modifier = Modifier.width(100.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CampoFecha(
    fecha: String,
    fechaRequester: BringIntoViewRequester,
    scope: kotlinx.coroutines.CoroutineScope
) {
    Column {
        Text(text = "Fecha", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = NegroTitulo)
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = fecha,
            onValueChange = {}, // Automático por ahora
            readOnly = true, // Evita que el teclado se abra
            singleLine = true,
            textStyle = TextStyle(fontSize = 14.sp, color = NegroTitulo),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = VerdeApp,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .bringIntoViewRequester(fechaRequester)
                .onFocusChanged {
                    if (it.isFocused) scope.launch { fechaRequester.bringIntoView() }
                }
        )
    }
}

@Composable
fun CampoDescripcion(
    descripcion: String,
    onDescripcionChange: (String) -> Unit,
    descripcionRequester: BringIntoViewRequester,
    scope: kotlinx.coroutines.CoroutineScope
) {
    Column {
        Text(text = "Descripción (opcional)", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = NegroTitulo)
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = descripcion,
            onValueChange = onDescripcionChange,
            singleLine = true,
            placeholder = { Text(text = "¿Qué compraste?", color = SubTituloGris) },
            textStyle = TextStyle(fontSize = 14.sp, color = NegroTitulo),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = VerdeApp,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .bringIntoViewRequester(descripcionRequester)
                .onFocusChanged {
                    if (it.isFocused) scope.launch { descripcionRequester.bringIntoView() }
                }
        )
    }
}

@Composable
fun BotonGuardarMovimiento(
    esEdicion: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(containerColor = VerdeApp),
        modifier = modifier
    ) {
        Text(
            text = if (esEdicion) "Actualizar gasto" else "Guardar gasto",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
    }
}

@Composable
fun GridItem(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val currentBgColor = if (isSelected) VerdeClaro else Color.White
    val currentBorderColor = if (isSelected) VerdeApp else GrisBorde
    val currentTextColor = if (isSelected) VerdeApp else NegroTitulo

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .height(80.dp)
            .background(currentBgColor, shape = RoundedCornerShape(12.dp))
            .border(1.dp, currentBorderColor, shape = RoundedCornerShape(12.dp))
            .clickable { onClick() }
    ) {
        Text(
            text = text,
            color = currentTextColor,
            fontSize = 12.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            textAlign = TextAlign.Center
        )
    }
}



// ==========================================================
// PREVIEW PARA VERLO EN ANDROID STUDIO
// ==========================================================
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AgregarGastosScreenPreview() {
    // Creamos un dato falso solo para que el Preview pueda pintar algo
    val movimientoFalso = RegistroDeMovimientos(
        Id = 0,
        Gasto = "Comida",
        Descripcion = "",
        Iconos = "",
        Monto = 0,
        TipoDeMovimiento = false,
        FechaHora = LocalDateTime.now() // Si te marca error por el API nivel, puedes usar una fecha estática
    )

    // Llamamos al Content (Stateless), NO a la Screen principal,
    // porque el Preview no se lleva bien con los ViewModels inyectados.
    AgregarGastosContent(
        movimientoId = 0, // 0 simula que estamos "Agregando", si pones 1 simularía "Editando"
        movimientoOriginal = movimientoFalso,
        onTerminar = { /* No hace nada en el preview */ },
        onAgregarMovimiento = { /* No hace nada en el preview */ },
        onEditarMovimiento = { /* No hace nada en el preview */ }
    )
}