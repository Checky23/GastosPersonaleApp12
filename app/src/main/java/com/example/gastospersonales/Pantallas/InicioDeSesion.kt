package com.example.gastospersonales.Pantallas

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gastospersonales.FireBase.iniciarSesionConGoogle
import com.example.gastospersonales.Navegacion.Screen
import com.example.gastospersonales.ui.theme.NegroTitulo
import com.example.gastospersonales.ui.theme.SubTituloGris
import com.example.gastospersonales.ui.theme.VerdeApp
import kotlinx.coroutines.launch

@Composable
fun InicioDeSesionScreen(navController: NavHostController) {

    // Estados temporales para los campos
    var correo = remember { mutableStateOf("") }
    var contraseña = remember { mutableStateOf("") }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F9FC))
            .padding(horizontal = 24.dp)
    ) {

        // Referencias para el constraintlayout
        val (
            titulo,
            subtitulo,
            correoLabel,
            correoField,
            contraseñaLabel,
            contraseñaField,
            botonLogin,
            olvidar,
            separador,
            continuarGoogle,
            crearCuenta
        ) = createRefs()

        // ---------------- TXT Control De Gastos ----------------

        Text(
            text = "ControlDeGastos",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = VerdeApp,
            modifier = Modifier.constrainAs(titulo) {
                top.linkTo(parent.top, margin = 40.dp)
                start.linkTo(parent.start)
            }
        )

        // ---------------- TXT . Tu dinero en orden ----------------

        Text(
            text = "Tu dinero, en orden.",
            fontSize = 14.sp,
            color = SubTituloGris,
            modifier = Modifier.constrainAs(subtitulo) {
                top.linkTo(titulo.bottom, margin = 8.dp)
                start.linkTo(parent.start)
            }
        )

        // ---------------- TEXT CORREO ELECTRONICO ----------------

        Text(
            text = "Correo electrónico",
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = NegroTitulo,
            modifier = Modifier.constrainAs(correoLabel) {
                top.linkTo(subtitulo.bottom, margin = 50.dp)
                start.linkTo(parent.start)
            }
        )

        // ---- TEXTFIELHOLDER  PETICION DE CORREO ---

        TextField(
            value = correo.value,
            onValueChange = { correo.value = it },
            placeholder = {
                Text(
                    text = "correo@ejemplo.com",
                    color = Color(0xFF6B7280),
                    fontSize = 14.sp
                )
            },
            singleLine = true,
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .constrainAs(correoField) {
                    top.linkTo(correoLabel.bottom, margin = 10.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        // ---------------- TXT DESCRIPTIVO CONTRASEÑA  ----------------

        Text(
            text = "Contraseña",
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = NegroTitulo,
            modifier = Modifier.constrainAs(contraseñaLabel) {
                top.linkTo(correoField.bottom, margin = 30.dp)
                start.linkTo(parent.start)
            }
        )

        // ------- PETICION DE CONTRASEÑA
        TextField(
            value = contraseña.value,
            onValueChange = { contraseña.value = it },
            placeholder = {
                Text(
                    text = "••••••••",
                    color = Color(0xFF6B7280)
                )
            },
            singleLine = true,
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .constrainAs(contraseñaField) {
                    top.linkTo(contraseñaLabel.bottom, margin = 10.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        // ---------------- INICIAR SESIÓN ----------------

        Button(
            onClick = {

            },
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2E7D5B)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .constrainAs(botonLogin) {
                    top.linkTo(contraseñaField.bottom, margin = 29.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            Text(
                text = "Iniciar sesión",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // ---------------- OLVIDASTE CONTRASEÑA ----------------

        TextButton(
            onClick = {
                // Recuperación de contraseña
            },
            modifier = Modifier.constrainAs(olvidar) {
                top.linkTo(botonLogin.bottom, margin = 4.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {
            Text(
                text = "¿Olvidaste tu contraseña?",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF4778D0)
            )
        }

        // ---------------- SEPARADOR ----------------

        androidx.compose.material3.HorizontalDivider(
            color = Color(0xFFE7EAF0),
            modifier = Modifier.constrainAs(separador) {
                top.linkTo(olvidar.bottom, margin = 26.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        // ---------------- GOOGLE ----------------

        val context = LocalContext.current
        val scope = rememberCoroutineScope()

        val webClientId =
            "1021709456536-alfu7gq32e4h3v5sq8696eidkakcam80.apps.googleusercontent.com"

        OutlinedButton(
            onClick = {


                scope.launch {
                    iniciarSesionConGoogle(
                        context = context,
                        webClientId = webClientId,

                        onSuccess = {

                            Log.d("GoogleAuth", "Inicio de sesión exitoso")
                            navController.navigate(Screen.InicioScreen.ruta){
                                popUpTo(Screen.InicioDeSesionScreen.ruta){
                                    inclusive = true
                                }
                            }


                        },

                        onError = { error ->
                            Log.e("GoogleAuth", error)
                        }


                    )
                }

            },
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .constrainAs(continuarGoogle) {
                    top.linkTo(separador.bottom, margin = 64.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            Text(
                text = "Continuar con Google",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF17202A)
            )
        }

        // ---------------- CREAR CUENTA ----------------

        TextButton(
            onClick = {
                // Aquí posteriormente navegaremos al registro
            },
            modifier = Modifier.constrainAs(crearCuenta) {
                top.linkTo(continuarGoogle.bottom, margin = 40.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {
            Text(
                text = "Crear una cuenta",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2E7D5B)
            )
        }
    }
}

@Preview
@Composable
fun VistaInicioDeSesion() {
    val navController = rememberNavController()
    InicioDeSesionScreen(navController)
}
