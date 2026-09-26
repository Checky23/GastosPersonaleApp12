package com.example.gastospersonales.UI.ComponentesVisuales

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun UsuarioNuevo(
    tituloText: String, modifierTitulo: Modifier = Modifier,
    tituloField: String, onValueChange: (String) -> Unit, placeholder: String,
    modifierField: Modifier = Modifier, keyboard: KeyboardType,
    contraseñaVisual: Boolean = false, hayErrorPassword: Boolean = false,
    esContrasena: Boolean = false
) {


    Text(
        text = tituloText,
        fontSize = 13.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color(0xFF17202A),
        modifier = modifierTitulo
    )

    TextField(
        value = tituloField,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholder,
                fontSize = 14.sp,
                color = Color(0xFF6B7280)
            )
        },
        singleLine = true,
        shape = RoundedCornerShape(12.dp),
        colors = TextFieldDefaults.colors(

            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,

            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedIndicatorColor = Color(0xFFE7EAF0),
            unfocusedIndicatorColor = Color(0xFFE7EAF0)
        ),
        modifier = modifierField
            .fillMaxWidth()
            .height(50.dp),
        isError = hayErrorPassword,
        visualTransformation =
            if (!esContrasena || contraseñaVisual) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
        keyboardOptions = KeyboardOptions(keyboardType = keyboard)

    )


}

@Preview
@Composable
fun UsuarioNuevoPreview() {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(Color.Red)


    ) {
        val (tituloPrincipal, titulo, subtitulo) = createRefs()
        var Usuario = ""

        UsuarioNuevo(
            tituloText = "Nombre",
            modifierTitulo = Modifier.constrainAs(tituloPrincipal) {

                top.linkTo(parent.top, margin = 10.dp)
                start.linkTo(parent.start, margin = 20.dp)
            },

            tituloField = Usuario,
            onValueChange = { Usuario = it },
            placeholder = "Nombre del Usuario", modifierField = Modifier
                .constrainAs(titulo) {
                    top.linkTo(tituloPrincipal.bottom, margin = 10.dp)
                }, keyboard = KeyboardType.Text
        )

    }

}