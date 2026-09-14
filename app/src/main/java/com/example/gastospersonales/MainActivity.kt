package com.example.gastospersonales

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gastospersonales.Pantallas.InicioDeSesionScreen
import com.example.gastospersonales.ui.theme.GastosPersonalesTheme
import com.example.gastospersonales.Navegacion.AppNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GastosPersonalesTheme {
                // Surface que actúa como el fondo base de la app
                Surface(modifier = Modifier.fillMaxSize()) {

                    // Inicio de la app delegando el control a la navegación
                    AppNavigation()

                }
            }
        }
    }
}