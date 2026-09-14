package com.example.gastospersonales.Pantallas

import android.widget.Button
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gastospersonales.Navegacion.Screen

@Composable
fun InicioScreen ( navController: NavHostController){

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F9FC))
            .padding(horizontal = 24.dp)
    ){

        Button(
            onClick = {
                navController.navigate(Screen.InicioDeSesionScreen.ruta)
            },
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2E7D5B)),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .constrainAs(createRef()) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
        )
        {

        }
    }

}

@Preview
@Composable
fun InicioPreviow(){
    val navController = rememberNavController()
    InicioScreen(navController)
}
