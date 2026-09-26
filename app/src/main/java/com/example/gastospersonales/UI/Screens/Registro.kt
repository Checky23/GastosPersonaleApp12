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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gastospersonales.UI.ComponentesVisuales.UsuarioNuevo
import com.example.gastospersonales.UI.Navegacion.Screen
import com.example.gastospersonales.UI.Temas.Dimens
import com.example.gastospersonales.UI.Temas.Fondo
import com.example.gastospersonales.UI.Temas.NegroTitulo
import com.example.gastospersonales.UI.Temas.SubTituloGris
import com.example.gastospersonales.UI.Temas.TextSizes
import com.example.gastospersonales.UI.Temas.VerdeApp
import com.example.gastospersonales.UI.Temas.VerdeClaro
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

    val contraseñaVisual = remember { mutableStateOf(false) }

    // ---------- CONTENEDOR PRINCIPAL ----------

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Fondo)
            .padding(Dimens.EspacioGrande)
    ) {

        val (contenido) = createRefs()

        // ---------- CONTENIDO DESPLAZABLE ----------

        LazyColumn(
            modifier = Modifier
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
                    fontSize = TextSizes.Titulo2,
                    fontWeight = FontWeight.Bold,
                    color = NegroTitulo,
                    modifier = Modifier.padding(
                        top = 5.dp,
                        start = Dimens.EspacioGrande
                    )
                )
            }

            // ---------- TÍTULO ----------

            item {
                Text(
                    text = "Empieza a organizar tu dinero.",
                    fontSize = TextSizes.Titulo2,
                    fontWeight = FontWeight.Bold,
                    color = NegroTitulo,
                    modifier = Modifier.padding(
                        top = Dimens.EspacioPequeno
                    )
                )
            }

            // ---------- SUBTÍTULO ----------

            item {
                Text(
                    text = "Solo necesitamos unos datos básicos.",
                    fontSize = TextSizes.Cuerpo,
                    color = SubTituloGris,
                    modifier = Modifier.padding(
                        top = Dimens.EspacioPequeno
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
                        top = Dimens.EspacioMedio
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
                        top = Dimens.EspacioMedio
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
                    onValueChange = { viewModel.cambiarContraseña(it) },
                    placeholder = "••••••••",
                    keyboard = KeyboardType.Password,
                    modifierTitulo = Modifier.padding(
                        top = Dimens.EspacioMedio
                    ),
                    modifierField = Modifier.padding(
                        top = 6.dp
                    ),
                    esContrasena = true,
                    contraseñaVisual = contraseñaVisual.value
                )
            }

            // ---------- CONFIRMAR CONTRASEÑA ----------

            item {
                UsuarioNuevo(
                    tituloText = "Confirmar contraseña",
                    tituloField = uiState.confirmarContraseña,
                    onValueChange = { viewModel.cambiarConfirmarContraseña(it) },
                    placeholder = "••••••••",
                    keyboard = KeyboardType.Password,
                    modifierTitulo = Modifier.padding(
                        top = Dimens.EspacioMedio
                    ),
                    modifierField = Modifier.padding(
                        top = 6.dp
                    ),
                    esContrasena = true,
                    contraseñaVisual = contraseñaVisual.value
                )
            }

            // ---------- ERROR ----------

            if (uiState.error != null) {
                item {
                    Text(
                        text = uiState.error!!,
                        fontSize = TextSizes.Etiqueta,
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
                        .padding(top = 5.dp)
                ) {
                    Checkbox(
                        checked = contraseñaVisual.value,
                        onCheckedChange = { contraseñaVisual.value = it }
                    )

                    Text(
                        text = "Mostrar Contraseña",
                        fontSize = TextSizes.Etiqueta,
                        fontWeight = FontWeight.Medium,
                        color = NegroTitulo,
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
                    onClick = { viewModel.crearCuenta() },
                    enabled = !uiState.cargando,
                    shape = RoundedCornerShape(Dimens.RadioEsquina),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = VerdeApp
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
                        fontSize = TextSizes.Subtitulo2,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

            // ---------- CUENTA EXISTENTE ----------

            item {
                Text(
                    text = "¿Ya tienes una cuenta?",
                    fontSize = TextSizes.Cuerpo,
                    color = SubTituloGris,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = Dimens.EspacioMedio
                        )
                )
            }

            // ---------- INICIAR SESIÓN ----------

            item {
                Text(
                    text = "Iniciar sesión",
                    fontSize = TextSizes.Cuerpo,
                    fontWeight = FontWeight.Bold,
                    color = VerdeApp,
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
                        .padding(top = 10.dp)
                        .background(
                            VerdeClaro,
                            shape = RoundedCornerShape(Dimens.RadioEsquina)
                        )
                ) {
                    val (infoIcono, infoTitulo, infoSubtitulo) = createRefs()

                    Text(
                        text = "✓",
                        fontSize = TextSizes.Subtitulo1,
                        fontWeight = FontWeight.Bold,
                        color = VerdeApp,
                        modifier = Modifier.constrainAs(infoIcono) {
                            top.linkTo(parent.top, margin = 15.dp)
                            start.linkTo(parent.start, margin = 40.dp)
                        }
                    )

                    Text(
                        text = "Tus datos estarán disponibles",
                        fontSize = TextSizes.Cuerpo,
                        fontWeight = FontWeight.SemiBold,
                        color = NegroTitulo,
                        modifier = Modifier.constrainAs(infoTitulo) {
                            top.linkTo(parent.top, margin = 15.dp)
                            start.linkTo(infoIcono.end, margin = 10.dp)
                        }
                    )

                    Text(
                        text = "en todos tus dispositivos.",
                        fontSize = TextSizes.Etiqueta,
                        color = SubTituloGris,
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