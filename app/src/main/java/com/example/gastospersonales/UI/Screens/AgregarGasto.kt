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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.gastospersonales.Data.Model.RegistroDeMovimientos
import com.example.gastospersonales.UI.Temas.Fondo
import com.example.gastospersonales.UI.Temas.GrisBorde
import com.example.gastospersonales.UI.Temas.NegroTitulo
import com.example.gastospersonales.UI.Temas.SubTituloGris
import com.example.gastospersonales.UI.Temas.VerdeApp
import com.example.gastospersonales.UI.Temas.VerdeClaro
import com.example.gastospersonales.ViewModel.MovimientoViewModel


@Composable
fun AgregarGastosScreen(
    navController: NavHostController,
    viewModel: MovimientoViewModel = viewModel()
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


    // ---------------- LISTA DE CATEGORÍAS ----------------

    val categorias = listOf(
        "🍔\nComida",
        "🚂\nTransporte",
        "🏠\nHogar",
        "💡\nServicios",
        "🎮\nOcio",
        "🎛️\nOtros"
    )


    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Fondo)
            .padding(24.dp)
    ) {

        val (
            TextsPresentacion,
            amountLabel,
            topBox,
            categoryLabel,
            gridBox,
            dateLabel,
            input1,
            descLabel,
            input2,
            bottomButton,
            cancelText
        ) = createRefs()


        // ---------- ENCABEZADO ----------

        Column(
            modifier = Modifier.constrainAs(TextsPresentacion) {
                top.linkTo(parent.top, margin = 24.dp)
                start.linkTo(parent.start)
            }
        ) {

            Text(
                text = "Agregar gasto",
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
        }


        // ---------- MONTO ----------

        Text(
            text = "Monto",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = NegroTitulo,

            modifier = Modifier.constrainAs(amountLabel) {

                top.linkTo(
                    TextsPresentacion.bottom,
                    margin = 24.dp
                )

                start.linkTo(parent.start)
            }
        )


        // ---------- TEXT FIELD MONTO ----------

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

                .constrainAs(topBox) {

                    top.linkTo(
                        amountLabel.bottom,
                        margin = 8.dp
                    )

                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )


        // ---------- CATEGORÍA ----------

        Text(
            text = "Categoría",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = NegroTitulo,

            modifier = Modifier.constrainAs(categoryLabel) {

                top.linkTo(
                    topBox.bottom,
                    margin = 24.dp
                )

                start.linkTo(parent.start)
            }
        )


        // ---------- GRID DE CATEGORÍAS ----------

        LazyVerticalGrid(

            columns = GridCells.Fixed(3),

            horizontalArrangement = Arrangement.spacedBy(12.dp),

            verticalArrangement = Arrangement.spacedBy(12.dp),

            userScrollEnabled = false,

            modifier = Modifier
                .fillMaxWidth()

                .constrainAs(gridBox) {

                    top.linkTo(
                        categoryLabel.bottom,
                        margin = 8.dp
                    )

                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {

            items(categorias) { categoria ->

                GridItem(

                    text = categoria,

                    isSelected =
                        categoriaSeleccionada ==
                                categoria.substringAfter("\n"),

                    onClick = {

                        categoriaSeleccionada =
                            categoria.substringAfter("\n")
                    }
                )
            }
        }


        // ---------- FECHA ----------

        Text(
            text = "Fecha",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = NegroTitulo,

            modifier = Modifier.constrainAs(dateLabel) {

                top.linkTo(
                    gridBox.bottom,
                    margin = 24.dp
                )

                start.linkTo(parent.start)
            }
        )


        // ---------- TEXT FIELD FECHA ----------

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

                .constrainAs(input1) {

                    top.linkTo(
                        dateLabel.bottom,
                        margin = 8.dp
                    )

                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )


        // ---------- DESCRIPCIÓN ----------

        Text(
            text = "Descripción (opcional)",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = NegroTitulo,

            modifier = Modifier.constrainAs(descLabel) {

                top.linkTo(
                    input1.bottom,
                    margin = 24.dp
                )

                start.linkTo(parent.start)
            }
        )


        // ---------- TEXT FIELD DESCRIPCIÓN ----------

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

                .constrainAs(input2) {

                    top.linkTo(
                        descLabel.bottom,
                        margin = 8.dp
                    )

                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )


        // ---------- BOTÓN GUARDAR ----------

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

                    top.linkTo(
                        input2.bottom,
                        margin = 32.dp
                    )

                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            Text(
                text = "Guardar gasto",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
        // ---------- CANCELAR ----------

        Text(

            text = "Cancelar",

            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = SubTituloGris,

            modifier = Modifier

                .clickable {
                    navController.popBackStack()
                    // Aquí posteriormente
                    // colocaremos la acción de cancelar.
                }

                .constrainAs(cancelText) {

                    top.linkTo(
                        bottomButton.bottom,
                        margin = 16.dp
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

    val navControler = rememberNavController()

    AgregarGastosScreen(navControler)
}