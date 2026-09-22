package com.example.gastospersonales.UI.ComponentesVisuales

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.gastospersonales.UI.Temas.VerdeApp

@Composable
fun BottomNavigationBar( onClickAction :() -> Unit ,
    modifier: Modifier = Modifier

) {
    Box(
        modifier = modifier
    ) {

        // Barra inferior
        NavigationBar(
            modifier = Modifier.align(Alignment.BottomCenter),
            containerColor = Color.White
        ) {

            NavigationBarItem(
                selected = true,
                onClick = { },
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

            // Espacio para dejar libre el centro
            Spacer(
                modifier = Modifier.width(70.dp)
            )

            NavigationBarItem(
                selected = false,
                onClick = { },
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

        // Botón + centrado sobre la barra
        FloatingActionButton(
            onClick = {
                onClickAction()
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 25.dp),
            containerColor = VerdeApp,
            contentColor = Color.White
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Agregar movimiento"
            )
        }
    }
}

@Preview
@Composable
fun BottonNavegationBarPreviwe ( ){
    val Navegation = rememberNavController()
    BottomNavigationBar(onClickAction = {})
}