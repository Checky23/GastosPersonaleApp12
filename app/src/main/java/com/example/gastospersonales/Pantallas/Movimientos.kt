package com.example.gastospersonales.Pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

// ============================================================
// COLORES
// ============================================================

private val BackgroundColor = Color(0xFFF7F9FC)
private val Green = Color(0xFF2E7D5B)
private val DarkText = Color(0xFF20252B)
private val GrayText = Color(0xFF858A91)
private val LightBorder = Color(0xFFE8EBEF)
private val Red = Color(0xFFE53935)


// ============================================================
// PANTALLA DE MOVIMIENTOS
// ============================================================

@Composable
fun MovimientosScreen() {

     ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {

        val (
            title,
            subtitle,
            search,
            filters,
            todayTitle,
            comida,
            transporte,
            cafe,
            yesterdayTitle,
            salario,
            bottomBar
        ) = createRefs()


        // ====================================================
        // TÍTULO
        // ====================================================

        Text(
            text = "Movimientos",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = DarkText,
            modifier = Modifier.constrainAs(title) {
                top.linkTo(
                    parent.top,
                    margin = 24.dp
                )
                start.linkTo(
                    parent.start,
                    margin = 17.dp
                )
            }
        )


        // ====================================================
        // SUBTÍTULO
        // ====================================================

        Text(
            text = "Historial financiero",
            fontSize = 9.sp,
            color = GrayText,
            modifier = Modifier.constrainAs(subtitle) {
                top.linkTo(
                    title.bottom,
                    margin = 4.dp
                )
                start.linkTo(title.start)
            }
        )


        // ====================================================
        // BUSCADOR
        // ====================================================

        SearchBox(
            modifier = Modifier.constrainAs(search) {
                top.linkTo(
                    subtitle.bottom,
                    margin = 14.dp
                )
                start.linkTo(
                    parent.start,
                    margin = 17.dp
                )
                end.linkTo(
                    parent.end,
                    margin = 18.dp
                )
                width = Dimension.fillToConstraints
            }
        )


        // ====================================================
        // FILTROS
        // ====================================================

        Row(
            modifier = Modifier.constrainAs(filters) {
                top.linkTo(
                    search.bottom,
                    margin = 11.dp
                )
                start.linkTo(
                    parent.start,
                    margin = 17.dp
                )
            },
            horizontalArrangement = Arrangement.spacedBy(7.dp)
        ) {
            FilterButton(
                text = "Todos",
                selected = true
            )

            FilterButton(
                text = "Gastos",
                selected = false
            )

            FilterButton(
                text = "Ingresos",
                selected = false
            )
        }


        // ====================================================
        // HOY
        // ====================================================

        SectionTitle(
            text = "HOY",
            modifier = Modifier.constrainAs(todayTitle) {
                top.linkTo(
                    filters.bottom,
                    margin = 18.dp
                )
                start.linkTo(
                    parent.start,
                    margin = 17.dp
                )
            }
        )


        // ====================================================
        // COMIDA
        // ====================================================

        MovementItem(
            icon = "🍔",
            title = "Comida",
            subtitle = "12:30 · Restaurante",
            amount = "- C$ 250",
            amountColor = Red,
            modifier = Modifier.constrainAs(comida) {
                top.linkTo(
                    todayTitle.bottom,
                    margin = 12.dp
                )
                start.linkTo(
                    parent.start,
                    margin = 17.dp
                )
                end.linkTo(
                    parent.end,
                    margin = 17.dp
                )
                width = Dimension.fillToConstraints
            }
        )


        // ====================================================
        // TRANSPORTE
        // ====================================================

        MovementItem(
            icon = "🚕",
            title = "Transporte",
            subtitle = "08:10 · Bus",
            amount = "- C$ 80",
            amountColor = Red,
            modifier = Modifier.constrainAs(transporte) {
                top.linkTo(
                    comida.bottom,
                    margin = 12.dp
                )
                start.linkTo(comida.start)
                end.linkTo(comida.end)
                width = Dimension.fillToConstraints
            }
        )


        // ====================================================
        // CAFÉ
        // ====================================================

        MovementItem(
            icon = "☕",
            title = "Café",
            subtitle = "07:45 · Cafetería",
            amount = "- C$ 65",
            amountColor = Red,
            modifier = Modifier.constrainAs(cafe) {
                top.linkTo(
                    transporte.bottom,
                    margin = 12.dp
                )
                start.linkTo(comida.start)
                end.linkTo(comida.end)
                width = Dimension.fillToConstraints
            }
        )


        // ====================================================
        // AYER
        // ====================================================

        SectionTitle(
            text = "AYER",
            modifier = Modifier.constrainAs(yesterdayTitle) {
                top.linkTo(
                    cafe.bottom,
                    margin = 18.dp
                )
                start.linkTo(
                    parent.start,
                    margin = 17.dp
                )
            }
        )


        // ====================================================
        // SALARIO
        // ====================================================

        MovementItem(
            icon = "💼",
            title = "Salario",
            subtitle = "Ayer · Empresa",
            amount = "+ C$ 8,500",
            amountColor = Green,
            modifier = Modifier.constrainAs(salario) {
                top.linkTo(
                    yesterdayTitle.bottom,
                    margin = 12.dp
                )
                start.linkTo(comida.start)
                end.linkTo(comida.end)
                width = Dimension.fillToConstraints
            }
        )


        // ====================================================
        // BARRA INFERIOR
        // ====================================================

        BottomNavigation(
            modifier = Modifier.constrainAs(bottomBar) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }
        )
    }
}


// ============================================================
// BUSCADOR
// ============================================================

@Composable
private fun SearchBox(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .height(31.dp)
            .clip(RoundedCornerShape(11.dp))
            .background(Color.White)
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Buscar",
            tint = GrayText,
            modifier = Modifier.size(12.dp)
        )

        Spacer(modifier = Modifier.width(5.dp))

        Text(
            text = "Buscar movimiento",
            fontSize = 9.sp,
            color = GrayText
        )
    }
}


// ============================================================
// BOTONES DE FILTRO
// ============================================================

@Composable
private fun FilterButton(
    text: String,
    selected: Boolean
) {
    Box(
        modifier = Modifier
            .height(27.dp)
            .clip(RoundedCornerShape(14.dp))
            .then(
                if (selected) {
                    Modifier.background(Green)
                } else {
                    Modifier
                        .background(Color.White)
                        .border(
                            width = 1.dp,
                            color = LightBorder,
                            shape = RoundedCornerShape(14.dp)
                        )
                }
            )
            .padding(horizontal = 15.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 8.sp,
            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
            color = if (selected) Color.White else GrayText
        )
    }
}


// ============================================================
// TÍTULO DE SECCIÓN
// ============================================================

@Composable
private fun SectionTitle(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        modifier = modifier,
        fontSize = 7.sp,
        fontWeight = FontWeight.Bold,
        color = GrayText
    )
}


// ============================================================
// ELEMENTO DE MOVIMIENTO
// ============================================================

@Composable
private fun MovementItem(
    icon: String,
    title: String,
    subtitle: String,
    amount: String,
    amountColor: Color,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.height(38.dp),
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = icon,
            fontSize = 11.sp,
            modifier = Modifier
                .width(16.dp)
                .padding(top = 1.dp)
        )

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = DarkText
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = subtitle,
                fontSize = 7.sp,
                color = GrayText
            )
        }

        Text(
            text = amount,
            fontSize = 9.sp,
            fontWeight = FontWeight.SemiBold,
            color = amountColor,
            modifier = Modifier.padding(top = 2.dp)
        )
    }
}


// ============================================================
// NAVEGACIÓN INFERIOR
// ============================================================

@Composable
private fun BottomNavigation(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .height(49.dp)
            .background(Color.White)
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomItem(
            icon = Icons.Default.Home,
            label = "Inicio",
            selected = false,
            modifier = Modifier.weight(1f)
        )

        BottomItem(
            icon = Icons.Default.SwapVert,
            label = "Movimientos",
            selected = true,
            modifier = Modifier.weight(1f)
        )

        BottomItem(
            icon = Icons.Default.Add,
            label = "Agregar",
            selected = false,
            modifier = Modifier.weight(1f)
        )

        BottomItem(
            icon = Icons.Default.BarChart,
            label = "Estadísticas",
            selected = false,
            modifier = Modifier.weight(1f)
        )

        BottomItem(
            icon = Icons.Default.Settings,
            label = "Perfil",
            selected = false,
            modifier = Modifier.weight(1f)
        )
    }
}


// ============================================================
// ITEM DE NAVEGACIÓN
// ============================================================

@Composable
private fun BottomItem(
    icon: ImageVector,
    label: String,
    selected: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .clickable { },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = if (selected) Green else GrayText,
            modifier = Modifier.size(17.dp)
        )

        Spacer(modifier = Modifier.height(3.dp))

        Text(
            text = label,
            fontSize = 6.sp,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
            color = if (selected) Green else GrayText,
            textAlign = TextAlign.Center
        )
    }
}


// ============================================================
// VISTA PREVIA (PREVIEW)
// ============================================================

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MovimientosScreenPreview() {
    MovimientosScreen()
}