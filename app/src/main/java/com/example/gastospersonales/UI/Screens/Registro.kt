package com.example.gastospersonales.UI.Screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gastospersonales.UI.ComponentesVisuales.UsuarioNuevo
import com.example.gastospersonales.UI.Navegacion.Screen
import com.example.gastospersonales.ViewModel.RegistroViewModel


@Composable
fun RegistroScreen(navController: NavHostController, viewModel: RegistroViewModel = viewModel()) {

    val uiState = viewModel.uiState
    val context = LocalContext.current

    LaunchedEffect(uiState.cuentaCreada) {

        if (uiState.cuentaCreada) {

            Toast.makeText(
                context,
                "Cuenta creada correctamente",
                Toast.LENGTH_SHORT
            ).show()

            navController.navigate(
                Screen.InicioDeSesionScreen.ruta
            ) {
                popUpTo(Screen.RegistroScreen.ruta) {
                    inclusive = true
                }
            }
        }
    }

    var contraseñaVisual = remember { mutableStateOf(false) }


    // ---------- CONTENEDOR PRINCIPAL ----------

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F9FC))
            .padding(20.dp)
    ) {

        val (contenido) = createRefs()


        // ---------- CONTENIDO DESPLAZABLE ----------

        LazyColumn(
            modifier = Modifier

                //width es el ancho del contenido
                .fillMaxSize()
                .constrainAs(contenido) {

                    top.linkTo(parent.top)

                    bottom.linkTo(parent.bottom)

                    start.linkTo(parent.start)

                    end.linkTo(parent.end)
                }
        ) {

            // ---------- TÍTULO PRINCIPAL ----------

            item {

                Text(
                    text = "📌  Crear una cuenta",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF17202A),
                    modifier = Modifier.padding(
                        top = 5.dp,
                        start = 20.dp
                    )
                )
            }


            // ---------- TÍTULO ----------

            item {

                Text(
                    text = "Empieza a organizar tu dinero.",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF17202A),
                    modifier = Modifier.padding(
                        top = 10.dp
                    )
                )
            }


            // ---------- SUBTÍTULO ----------

            item {

                Text(
                    text = "Solo necesitamos unos datos básicos.",
                    fontSize = 13.sp,
                    color = Color(0xFF6B7280),
                    modifier = Modifier.padding(
                        top = 10.dp
                    )
                )
            }


            // ---------- NOMBRE ----------

            item {

                UsuarioNuevo(
                    tituloText = "Nombre",
                    tituloField = uiState.nombre,
                    onValueChange = { viewModel.cambiarNombre(it) },
                    placeholder = "Tu nombre",
                    keyboard = KeyboardType.Text,
                    modifierTitulo = Modifier.padding(
                        top = 20.dp
                    ),
                    modifierField = Modifier.padding(
                        top = 6.dp
                    ),
                    esContrasena = false

                )
            }


            // ---------- CORREO ----------

            item {

                UsuarioNuevo(
                    tituloText = "Correo electrónico",
                    tituloField = uiState.correo,
                    onValueChange = { viewModel.cambiarCorreo(it) },
                    placeholder = "ejemplo@correo.com",
                    keyboard = KeyboardType.Email,
                    modifierTitulo = Modifier.padding(
                        top = 20.dp
                    ),
                    modifierField = Modifier.padding(
                        top = 6.dp
                    ),
                    esContrasena = false

                )
            }


            // ---------- CONTRASEÑA ----------

            item {

                UsuarioNuevo(
                    tituloText = "Contraseña",
                    tituloField = uiState.contraseña,
                    onValueChange = {
                        viewModel.cambiarContraseña(it)
                    },
                    placeholder = "••••••••",
                    keyboard = KeyboardType.Password,
                    modifierTitulo = Modifier.padding(
                        top = 20.dp
                    ),
                    modifierField = Modifier.padding(
                        top = 6.dp
                    ),
                    esContrasena = true,
                    contraseñaVisual = contraseñaVisual.value,

                )
            }


            // ---------- CONFIRMAR CONTRASEÑA ----------

            item {

                UsuarioNuevo(
                    tituloText = "Confirmar contraseña",
                    tituloField = uiState.confirmarContraseña,
                    onValueChange = {
                        viewModel.cambiarConfirmarContraseña(it)

                    },
                    placeholder = "••••••••",
                    keyboard = KeyboardType.Password,
                    modifierTitulo = Modifier.padding(
                        top = 20.dp
                    ),
                    modifierField = Modifier.padding(
                        top = 6.dp
                    ),
                    esContrasena = true,
                    contraseñaVisual = contraseñaVisual.value,

                )
            }


            // ---------- ERROR ----------

            if (uiState.error != null) {

                item {

                    Text(
                        text = uiState.error!!,
                        fontSize = 12.sp,
                        color = Color.Red,
                        modifier = Modifier.padding(
                            top = 4.dp
                        )
                    )
                }
            }


            // ---------- CHECKBOX ----------

            item {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 5.dp
                        )
                ) {

                    Checkbox(
                        checked = contraseñaVisual.value,

                        onCheckedChange = {
                            contraseñaVisual.value = it
                        }
                    )

                    Text(
                        text = "Mostrar Contraseña",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF17202A),
                        modifier = Modifier.padding(
                            top = 15.dp,
                            start = 5.dp
                        )
                    )
                }
            }


            // ---------- CREAR CUENTA ----------

            item {

                Button(
                    onClick = {

                        viewModel.crearCuenta()

                        /*if (uiState.contraseña != uiState.confirmarContraseña) {
                            hayErrorPassword = true
                        } else {
                            hayErrorPassword = false

                            // ---------- CREAR CUENTA ----------
                            scope.launch {
                                CreacionDeCuenta(
                                    correo = correo.value,
                                    contraseña = contrasena.value,
                                    onSuccess = {
                                        // Cuenta creada correctamente este sale en consola
                                        Log.d("FirebaseAuth", "Cuenta creada correctamente")

                                        //Mensaje Emergente de Confirmacion
                                        Toast.makeText(context,
                                            "Cuenta creada correctamente", Toast.LENGTH_SHORT
                                        ).show()

                                        //Navega a la pantalla de inicio de sesion
                                        navController.navigate(Screen.InicioDeSesionScreen.ruta
                                        ) { popUpTo(Screen.InicioDeSesionScreen.ruta) {
                                            inclusive = true }
                                        }

                                    },
                                    onError = { error ->
                                        // Error al crear la cuenta este sale en consola
                                        Log.e(
                                            "FirebaseAuth",
                                            "Error al crear cuenta",
                                            Exception(error)
                                        )

                                        //Mensaje Emergente de Error
                                        Toast.makeText(
                                            context,
                                            "Error al crear cuenta",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                )
                            }
                        }*/

                    },
                    enabled = !uiState.cargando,

                    shape = RoundedCornerShape(16.dp),

                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2E7D5B)
                    ),

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .padding(
                            top = 5.dp,
                            start = 4.dp,
                            end = 4.dp
                        )
                ) {

                    Text(
                        text = if (uiState.cargando) {
                            "Creando cuenta..."
                        } else {
                            "Crear cuenta"
                        },
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }


            // ---------- CUENTA EXISTENTE ----------

            item {

                Text(
                    text = "¿Ya tienes una cuenta?",
                    fontSize = 13.sp,
                    color = Color(0xFF6B7280),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 20.dp,


                            )
                )
            }


            // ---------- INICIAR SESIÓN ----------

            item {

                Text(
                    text = "Iniciar sesión",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2E7D5B),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {

                            navController.navigate(
                                Screen.InicioDeSesionScreen.ruta
                            )
                        }
                        .padding(
                            top = 10.dp
                        )
                )
            }


            // ---------- CAJA INFORMATIVA ----------

            item {

                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                        .padding(
                            top = 10.dp
                        )
                        .background(
                            Color(0xFFE7F5EE),
                            shape = RoundedCornerShape(16.dp)
                        )
                ) {

                    val (infoIcono, infoTitulo, infoSubtitulo) = createRefs()

                    Text(
                        text = "✓",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2E7D5B),
                        modifier = Modifier.constrainAs(infoIcono) {
                            top.linkTo(parent.top, margin = 15.dp)
                            start.linkTo(parent.start, margin = 40.dp)
                        }
                    )


                    Text(
                        text = "Tus datos estarán disponibles",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF17202A),
                        modifier = Modifier.constrainAs(infoTitulo) {
                            top.linkTo(parent.top, margin = 15.dp)
                            start.linkTo(infoIcono.end, margin = 10.dp)
                        }
                    )


                    Text(
                        text = "en todos tus dispositivos.",
                        fontSize = 12.sp,
                        color = Color(0xFF6B7280),
                        modifier = Modifier.constrainAs(infoSubtitulo) {
                            top.linkTo(infoTitulo.bottom, margin = 4.dp)
                            start.linkTo(infoIcono.end, margin = 10.dp)
                        }
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun VistaRegistroScreen() {
    val navController = rememberNavController()
    RegistroScreen(navController)
}
