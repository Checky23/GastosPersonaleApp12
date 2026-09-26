package com.example.gastospersonales.UI.ComponentesVisuales

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gastospersonales.UI.Temas.Fondo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectorTipoMovimiento(
    valorSeleccionado: String,
    onValorSeleccionadoChange: (String) -> Unit
) {

    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        modifier = Modifier.height(50.dp),
        expanded = expanded, onExpandedChange = {
            expanded = !expanded
        }) {

        TextField(
            value = valorSeleccionado, onValueChange = {}, readOnly = true, label = {
                Text("Tipo de movimiento")
            }, trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            }, modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            modifier = Modifier.background(Fondo),
            expanded = expanded, onDismissRequest = {
                expanded = false
            }) {

            DropdownMenuItem(text = { Text("Egreso") }, onClick = {
                onValorSeleccionadoChange("Egreso")
                expanded = false
            })

            DropdownMenuItem(text = { Text("Ingreso") }, onClick = {
                onValorSeleccionadoChange("Ingreso")
                expanded = false
            })
        }
    }
}