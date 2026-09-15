package com.example.gastospersonales.Pantallas.Inicio


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.compose.rememberNavController
import com.example.gastospersonales.ui.theme.ColorBlanco
import com.example.gastospersonales.ui.theme.NegroTitulo
import com.example.gastospersonales.ui.theme.RojoGasto
import com.example.gastospersonales.ui.theme.SubTituloGris
import com.example.gastospersonales.ui.theme.VerdeApp


// ------------------------------------------------------------
// PANTALLA PRINCIPAL
// ------------------------------------------------------------

@Composable
fun InicioScreen() {


    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorBlanco)
    ) {
        // SALUDO
        Text(
            text = "Buenos días 👋",
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            color = SubTituloGris,
            modifier = Modifier.constrainAs(createRef()) {
                start.linkTo(parent.start, 24.dp)
                top.linkTo(parent.top, 36.dp)
            }
        )

        // TÍTULO PRINCIPAL

        Text(
            text = "Resumen financiero",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = NegroTitulo,
            modifier = Modifier.constrainAs(createRef()) {
                start.linkTo(parent.start, 24.dp)
                top.linkTo(parent.top, 66.dp)
            }
        )

        // TARJETA DEL SALDO DISPONIBLE

        TarjetaDeSaldoScreen(
            modifier = Modifier.constrainAs(createRef()) {
                start.linkTo(parent.start, 24.dp)
                end.linkTo(parent.end, 24.dp)
                top.linkTo(parent.top, 112.dp)
            },
        )

        // SECCIÓN "ESTE MES"

        Text(
            text = "Este mes",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = NegroTitulo,
            modifier = Modifier.constrainAs(createRef()) {
                start.linkTo(parent.start, 24.dp)
                top.linkTo(parent.top, 300.dp)
            }
        )


        // ----------------------------------------------------
        // TARJETA DE INGRESOS
        // ----------------------------------------------------

        ResumenMensualCard (modifier = Modifier.constrainAs(createRef()) {
            start.linkTo(parent.start, 24.dp)
            top.linkTo(parent.top, 340.dp)
        },)

        // TARJETA DE GASTOS

        Surface(
            modifier = Modifier
                .width(163.dp)
                .height(86.dp)
                .constrainAs(createRef()) {
                    end.linkTo(parent.end, 24.dp)
                    top.linkTo(parent.top, 340.dp)
                },
            shape = RoundedCornerShape(16.dp),
            color = Color.White
        ) {

            ConstraintLayout(
                modifier = Modifier.fillMaxSize()
            ) {

                Text(
                    text = "Gastos",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = SubTituloGris,
                    modifier = Modifier.constrainAs(createRef()) {
                        start.linkTo(parent.start, 18.dp)
                        top.linkTo(parent.top, 20.dp)
                    }
                )

                Text(
                    text = "C$ 6,650",
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold,
                    color = RojoGasto,
                    modifier = Modifier.constrainAs(createRef()) {
                        start.linkTo(parent.start, 18.dp)
                        top.linkTo(parent.top, 48.dp)
                    }
                )
            }
        }


        // ----------------------------------------------------
        // MOVIMIENTOS RECIENTES
        // ----------------------------------------------------

        Text(
            text = "Movimientos recientes",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = NegroTitulo,
            modifier = Modifier.constrainAs(createRef()) {
                start.linkTo(parent.start, 24.dp)
                top.linkTo(parent.top, 444.dp)
            }
        )


        // ----------------------------------------------------
        // MOVIMIENTO 1 - COMIDA
        // ----------------------------------------------------

        Text(
            text = "🍔  Comida",
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            color = NegroTitulo,
            modifier = Modifier.constrainAs(createRef()) {
                start.linkTo(parent.start, 38.dp)
                top.linkTo(parent.top, 492.dp)
            }
        )

        Text(
            text = "Hoy · 12:30",
            fontSize = 11.sp,
            color = SubTituloGris,
            modifier = Modifier.constrainAs(createRef()) {
                start.linkTo(parent.start, 38.dp)
                top.linkTo(parent.top, 512.dp)
            }
        )

        Text(
            text = "− C$ 250",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = RojoGasto,
            modifier = Modifier.constrainAs(createRef()) {
                end.linkTo(parent.end, 44.dp)
                top.linkTo(parent.top, 498.dp)
            }
        )


        // ----------------------------------------------------
        // MOVIMIENTO 2 - TRANSPORTE
        // ----------------------------------------------------

        Text(
            text = "🚌  Transporte",
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            color = NegroTitulo,
            modifier = Modifier.constrainAs(createRef()) {
                start.linkTo(parent.start, 38.dp)
                top.linkTo(parent.top, 548.dp)
            }
        )

        Text(
            text = "Hoy · 08:10",
            fontSize = 11.sp,
            color = SubTituloGris,
            modifier = Modifier.constrainAs(createRef()) {
                start.linkTo(parent.start, 38.dp)
                top.linkTo(parent.top, 568.dp)
            }
        )

        Text(
            text = "− C$ 80",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = RojoGasto,
            modifier = Modifier.constrainAs(createRef()) {
                end.linkTo(parent.end, 44.dp)
                top.linkTo(parent.top, 554.dp)
            }
        )


        // ----------------------------------------------------
        // MOVIMIENTO 3 - SALARIO
        // ----------------------------------------------------

        Text(
            text = "💼  Salario",
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            color = NegroTitulo,
            modifier = Modifier.constrainAs(createRef()) {
                start.linkTo(parent.start, 38.dp)
                top.linkTo(parent.top, 604.dp)
            }
        )

        Text(
            text = "Ayer",
            fontSize = 11.sp,
            color = SubTituloGris,
            modifier = Modifier.constrainAs(createRef()) {
                start.linkTo(parent.start, 38.dp)
                top.linkTo(parent.top, 624.dp)
            }
        )

        Text(
            text = "＋ C$ 8,500",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = VerdeApp,
            modifier = Modifier.constrainAs(createRef()) {
                end.linkTo(parent.end, 44.dp)
                top.linkTo(parent.top, 610.dp)
            }
        )


        // ----------------------------------------------------
        // BOTÓN REGISTRAR MOVIMIENTO
        // ----------------------------------------------------

        Button(
            onClick = {
                // Aquí posteriormente podemos navegar
                // hacia la pantalla para registrar un movimiento.
            },
            modifier = Modifier
                .width(342.dp)
                .height(58.dp)
                .constrainAs(createRef()) {
                    start.linkTo(parent.start, 24.dp)
                    end.linkTo(parent.end, 24.dp)
                    top.linkTo(parent.top, 680.dp)
                },
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                text = "+   Registrar movimiento",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }


        // ----------------------------------------------------
        // BARRA DE NAVEGACIÓN INFERIOR
        // ----------------------------------------------------

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(createRef()) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
            color = Color.Transparent
        ) {
            // La barra inferior se puede implementar
            // posteriormente con NavigationBar.
        }

    }
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        // --------------------------------------------------------
        // NAVIGATION BAR
        // Se coloca pegada a la parte inferior de la pantalla.
        // --------------------------------------------------------

        NavigationBar(
            modifier = Modifier
                .align(Alignment.BottomCenter),
            containerColor = Color.White
        ) {

            // ----------------------------------------------------
            // BOTÓN IZQUIERDO
            // ----------------------------------------------------

            NavigationBarItem(
                selected = true,
                onClick = {},
                icon = {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "Inicio"
                    )
                },
                label = {
                    Text("Inicio")
                }
            )

            // ----------------------------------------------------
            // ESPACIO PARA EL BOTÓN CENTRAL
            // Dejamos este espacio vacío para que el FAB
            // pueda colocarse encima de la barra.
            // ----------------------------------------------------

            Spacer(
                modifier = Modifier.width(70.dp)
            )

            // ----------------------------------------------------
            // BOTÓN DERECHO
            // ----------------------------------------------------

            NavigationBarItem(
                selected = false,
                onClick = {},
                icon = {
                    Icon(
                        imageVector = Icons.Default.AccessTime,
                        contentDescription = "Historial"
                    )
                },
                label = {
                    Text("Historial")
                }
            )
        }

        // --------------------------------------------------------
        // BOTÓN CENTRAL +
        // --------------------------------------------------------
        // Este botón queda flotando encima del NavigationBar.
        // Posteriormente aquí puedes navegar a RegistrarScreen
        // o abrir cualquier otra acción.

        FloatingActionButton(
            onClick = {
                // Acción del botón +
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 25.dp),
            containerColor = VerdeApp,
            contentColor = Color.White
        ) {

            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Agregar"
            )
        }
    }

}

@Preview
@Composable
fun InicioPreviow() {
    val navController = rememberNavController()
    InicioScreen()
}
