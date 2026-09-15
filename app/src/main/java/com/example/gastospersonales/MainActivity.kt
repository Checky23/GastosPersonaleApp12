package com.example.gastospersonales

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.gastospersonales.Navegacion.AppNavigation
import com.example.gastospersonales.ui.theme.GastosPersonalesTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
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