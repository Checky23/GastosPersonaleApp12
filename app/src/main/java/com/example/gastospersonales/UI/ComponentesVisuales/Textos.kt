package com.example.gastospersonales.UI.ComponentesVisuales

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import com.example.gastospersonales.UI.Temas.NegroTitulo

@Composable
 fun TextosTitulo(
    texto: String,
    fontSize: TextUnit,
    modifier: Modifier = Modifier
) {
    Text(
        text = texto,
        fontSize = fontSize,
        fontWeight = FontWeight.Bold,
        color = NegroTitulo,
        modifier = modifier
    )
}