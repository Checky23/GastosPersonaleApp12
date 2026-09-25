package com.example.gastospersonales.UI.Screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gastospersonales.Data.FireBase.iniciarSesionConGoogle
import com.example.gastospersonales.UI.ComponentesVisuales.UsuarioNuevo
import com.example.gastospersonales.UI.Navegacion.Screen
import com.example.gastospersonales.UI.Temas.SubTituloGris
import com.example.gastospersonales.UI.Temas.VerdeApp
import com.example.gastospersonales.ViewModel.InicioDeSesionViewModel
import kotlinx.coroutines.launch
import androidx.compose.runtime.LaunchedEffect

@Composable
fun InicioDeSesionScreen(navController: NavHostController) {


    // LocalContext para obtener el contexto de la aplicación
    val context = LocalContext.current
    // CoroutineScope para lanzar corutinas es decir las funciones suspendidas
    val scope = rememberCoroutineScope()

    val viewModel : InicioDeSesionViewModel = viewModel()
    val datosLogin = viewModel.uiState


    LaunchedEffect(datosLogin.sesionIniciada){
        if (datosLogin.sesionIniciada){
            navController.navigate(Screen.InicioScreen.ruta){
                popUpTo(Screen.InicioDeSesionScreen.ruta){
                    inclusive = true
                }
            }
        }

    }



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
            errorLogin,
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


        // ---------------- CAMPO CORREO ELECTRÓNICO ----------------

        UsuarioNuevo(
            tituloText = "Correo electrónico",
            modifierTitulo = Modifier.constrainAs(correoLabel) {
                top.linkTo(subtitulo.bottom, margin = 50.dp)
                start.linkTo(parent.start)
            },
            tituloField = datosLogin.correo,
            onValueChange = { correoActualizado ->
                viewModel.cambiarCorreo(correoActualizado)
            },
            placeholder = "correo@ejemplo.com",
            modifierField = Modifier.constrainAs(correoField) {
                top.linkTo(correoLabel.bottom, margin = 10.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            keyboard = KeyboardType.Email,
            // Como no es contraseña, podemos omitir los demás parámetros que tienen valores por defecto
            esContrasena = false
        )

        // ---------------- CAMPO CONTRASEÑA (Adaptado) ----------------

        UsuarioNuevo(
            tituloText = "Contraseña",
            modifierTitulo = Modifier.constrainAs(contraseñaLabel) {
                top.linkTo(correoField.bottom, margin = 30.dp)
                start.linkTo(parent.start)
            },
            tituloField = datosLogin.contraseña, // Asumiendo que esta es tu variable de estado
            onValueChange = { contraseñaActualizada ->
                viewModel.cambiarContraseña(contraseñaActualizada)
            },
            placeholder = "••••••••",
            modifierField = Modifier.constrainAs(contraseñaField) {
                top.linkTo(contraseñaLabel.bottom, margin = 10.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            keyboard = KeyboardType.Password,

            // Aquí usamos los parámetros específicos que definiste para contraseñas
            esContrasena = true,
            contraseñaVisual = false // Esto mantendrá los caracteres ocultos por defecto
        )



        Text(
            //?: se puede entender como: si el valor de la izquierda existe, úsalo;
            // si es null, usa el valor de la derecha.
            text = datosLogin.error ?: "",
            color = Color.Red,
            fontSize = 12.sp,
            modifier = Modifier.constrainAs(errorLogin) {
                top.linkTo(contraseñaField.bottom, margin = 6.dp)
                start.linkTo(parent.start)
            }
        )



        // ---------------- INICIAR SESIÓN ----------------

        Button(
            onClick = {
                viewModel.iniciarSesion()



            },
            //se desactiva si hay un error o si está cargando
            enabled = !datosLogin.cargando,
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2E7D5B)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .constrainAs(botonLogin) {
                    top.linkTo(errorLogin.bottom, margin = 29.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            Text(
                // Si está cargando, muestra "Iniciando sesión..."
                text = if (datosLogin.cargando)
                    "Iniciando sesión..."
                else
                    "Iniciar sesión",
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

        HorizontalDivider(
            color = Color(0xFFE7EAF0),
            modifier = Modifier.constrainAs(separador) {
                top.linkTo(olvidar.bottom, margin = 26.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        // ---------------- GOOGLE ----------------



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
                navController.navigate(Screen.RegistroScreen.ruta)
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
