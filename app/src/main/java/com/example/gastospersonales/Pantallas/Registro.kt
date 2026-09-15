package com.example.gastospersonales.Pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text

import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout


@Composable
fun RegistroScreen() {

    var nombre = remember { mutableStateOf("") }
    var correo = remember { mutableStateOf("") }
    var contraseña = remember { mutableStateOf("") }
    var confirmarContraseña = remember { mutableStateOf("") }
    var aceptaTerminos = remember { mutableStateOf(false) }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F9FC))
    ) {

        // ---------- VOLVER ----------

        Text(
            text = "‹",
            fontSize = 32.sp,
            color = Color(0xFF17202A),
            modifier = Modifier.constrainAs(createRef()) {

                top.linkTo(
                    parent.top,
                    margin = 22.dp
                )

                start.linkTo(
                    parent.start,
                    margin = 24.dp
                )
            }
        )

        // ---------- TITULO ----------

        Text(
            text = "Crear una cuenta",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF17202A),
            modifier = Modifier.constrainAs(createRef()) {

                top.linkTo(
                    parent.top,
                    margin = 27.dp
                )

                start.linkTo(
                    parent.start,
                    margin = 62.dp
                )
            }
        )

        // ---------- TITULO PRINCIPAL ----------

        Text(
            text = "Empieza a organizar tu dinero.",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF17202A),
            modifier = Modifier.constrainAs(createRef()) {

                top.linkTo(
                    parent.top,
                    margin = 90.dp
                )

                start.linkTo(
                    parent.start,
                    margin = 24.dp
                )

                end.linkTo(
                    parent.end,
                    margin = 24.dp
                )
            }
        )

        // ---------- SUBTITULO ----------

        Text(
            text = "Solo necesitamos unos datos básicos.",
            fontSize = 13.sp,
            color = Color(0xFF6B7280),
            modifier = Modifier.constrainAs(createRef()) {

                top.linkTo(
                    parent.top,
                    margin = 115.dp
                )

                start.linkTo(
                    parent.start,
                    margin = 24.dp
                )
            }
        )

        // ---------- NOMBRE ----------

        Text(
            text = "Nombre",
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF17202A),
            modifier = Modifier.constrainAs(createRef()) {

                top.linkTo(
                    parent.top,
                    margin = 148.dp
                )

                start.linkTo(
                    parent.start,
                    margin = 24.dp
                )
            }
        )

        TextField(
            value = nombre.value,
            onValueChange = {
                nombre.value = it
            },
            placeholder = {
                Text(
                    text = "Tu nombre",
                    fontSize = 14.sp,
                    color = Color(0xFF6B7280)
                )
            },
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Color(0xFFE7EAF0),
                unfocusedIndicatorColor = Color(0xFFE7EAF0)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .constrainAs(createRef()) {

                    top.linkTo(
                        parent.top,
                        margin = 179.dp
                    )

                    start.linkTo(
                        parent.start,
                        margin = 24.dp
                    )

                    end.linkTo(
                        parent.end,
                        margin = 24.dp
                    )
                }
        )

        // ---------- CORREO ----------

        Text(
            text = "Correo electrónico",
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF17202A),
            modifier = Modifier.constrainAs(createRef()) {

                top.linkTo(
                    parent.top,
                    margin = 237.dp
                )

                start.linkTo(
                    parent.start,
                    margin = 24.dp
                )
            }
        )

        TextField(
            value = correo.value,
            onValueChange = {
                correo.value = it
            },
            placeholder = {
                Text(
                    text = "ejemplo@correo.com",
                    fontSize = 14.sp,
                    color = Color(0xFF6B7280)
                )
            },
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Color(0xFFE7EAF0),
                unfocusedIndicatorColor = Color(0xFFE7EAF0)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .constrainAs(createRef()) {

                    top.linkTo(
                        parent.top,
                        margin = 251.dp
                    )

                    start.linkTo(
                        parent.start,
                        margin = 24.dp
                    )

                    end.linkTo(
                        parent.end,
                        margin = 24.dp
                    )
                }
        )

        // ---------- CONTRASEÑA ----------

        Text(
            text = "Contraseña",
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF17202A),
            modifier = Modifier.constrainAs(createRef()) {

                top.linkTo(
                    parent.top,
                    margin = 309.dp
                )

                start.linkTo(
                    parent.start,
                    margin = 24.dp
                )
            }
        )

        TextField(
            value = contraseña.value,
            onValueChange = {
                contraseña.value = it
            },
            placeholder = {
                Text(
                    text = "••••••••",
                    fontSize = 14.sp,
                    color = Color(0xFF17202A)
                )
            },
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Color(0xFFE7EAF0),
                unfocusedIndicatorColor = Color(0xFFE7EAF0)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .constrainAs(createRef()) {

                    top.linkTo(
                        parent.top,
                        margin = 323.dp
                    )

                    start.linkTo(
                        parent.start,
                        margin = 24.dp
                    )

                    end.linkTo(
                        parent.end,
                        margin = 24.dp
                    )
                }
        )

        // ---------- CONFIRMAR CONTRASEÑA ----------

        Text(
            text = "Confirmar contraseña",
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF17202A),
            modifier = Modifier.constrainAs(createRef()) {

                top.linkTo(
                    parent.top,
                    margin = 381.dp
                )

                start.linkTo(
                    parent.start,
                    margin = 24.dp
                )
            }
        )

        TextField(
            value = confirmarContraseña.value,
            onValueChange = {
                confirmarContraseña.value = it
            },
            placeholder = {
                Text(
                    text = "••••••••",
                    fontSize = 14.sp,
                    color = Color(0xFF17202A)
                )
            },
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Color(0xFFE7EAF0),
                unfocusedIndicatorColor = Color(0xFFE7EAF0)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .constrainAs(createRef()) {

                    top.linkTo(
                        parent.top,
                        margin = 395.dp
                    )

                    start.linkTo(
                        parent.start,
                        margin = 24.dp
                    )

                    end.linkTo(
                        parent.end,
                        margin = 24.dp
                    )
                }
        )

        // ---------- CHECKBOX ----------

        Checkbox(
            checked = aceptaTerminos.value,
            onCheckedChange = {
                aceptaTerminos.value = it
            },
            modifier = Modifier.constrainAs(createRef()) {

                top.linkTo(
                    parent.top,
                    margin = 464.dp
                )

                start.linkTo(
                    parent.start,
                    margin = 20.dp
                )
            }
        )

        Text(
            text = "Acepto los términos y condiciones",
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF17202A),
            modifier = Modifier.constrainAs(createRef()) {

                top.linkTo(
                    parent.top,
                    margin = 473.dp
                )

                start.linkTo(
                    parent.start,
                    margin = 52.dp
                )
            }
        )

        // ---------- CREAR CUENTA ----------

        Button(
            onClick = {
                // Crear cuenta
            },
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2E7D5B)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .constrainAs(createRef()) {

                    top.linkTo(
                        parent.top,
                        margin = 530.dp
                    )

                    start.linkTo(
                        parent.start,
                        margin = 24.dp
                    )

                    end.linkTo(
                        parent.end,
                        margin = 24.dp
                    )
                }
        ) {
            Text(
                text = "Crear cuenta",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // ---------- TEXTO CUENTA EXISTENTE ----------

        Text(
            text = "¿Ya tienes una cuenta?",
            fontSize = 13.sp,
            color = Color(0xFF6B7280),
            modifier = Modifier.constrainAs(createRef()) {

                top.linkTo(
                    parent.top,
                    margin = 610.dp
                )

                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        // ---------- INICIAR SESIÓN ----------

        Text(
            text = "Iniciar sesión",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF2E7D5B),
            modifier = Modifier.constrainAs(createRef()) {

                top.linkTo(
                    parent.top,
                    margin = 635.dp
                )

                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        // ---------- CAJA INFORMATIVA ----------

        androidx.compose.foundation.layout.Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .background(
                    color = Color(0xFFE7F5EE),
                    shape = RoundedCornerShape(14.dp)
                )
                .constrainAs(createRef()) {

                    top.linkTo(
                        parent.top,
                        margin = 685.dp
                    )

                    start.linkTo(
                        parent.start,
                        margin = 24.dp
                    )

                    end.linkTo(
                        parent.end,
                        margin = 24.dp
                    )
                }
        )

        // ---------- CHECK INFORMACIÓN ----------

        Text(
            text = "✓",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF2E7D5B),
            modifier = Modifier.constrainAs(createRef()) {

                top.linkTo(
                    parent.top,
                    margin = 700.dp
                )

                start.linkTo(
                    parent.start,
                    margin = 42.dp
                )
            }
        )

        // ---------- TEXTO INFORMACIÓN ----------

        Text(
            text = "Tus datos estarán disponibles",
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF17202A),
            modifier = Modifier.constrainAs(createRef()) {

                top.linkTo(
                    parent.top,
                    margin = 700.dp
                )

                start.linkTo(
                    parent.start,
                    margin = 70.dp
                )
            }
        )

        Text(
            text = "en todos tus dispositivos.",
            fontSize = 12.sp,
            color = Color(0xFF6B7280),
            modifier = Modifier.constrainAs(createRef()) {

                top.linkTo(
                    parent.top,
                    margin = 722.dp
                )

                start.linkTo(
                    parent.start,
                    margin = 70.dp
                )
            }
        )
    }
}

@Preview
@Composable
fun VistaRegistroScreen() {
    RegistroScreen()
}
