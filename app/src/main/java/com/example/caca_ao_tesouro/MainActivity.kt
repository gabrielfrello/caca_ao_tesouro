package com.example.caca_ao_tesouro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost

import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navigationController = rememberNavController()

            NavHost(
                navController = navigationController,
                startDestination = "/home"
            ) {
               // Tela01
                composable (route = "/home") {
                    Tela(
                        nomeTela = "Vamos participar de uma caça ao tesouro?",
                        clickB1 = { navigationController.navigate(route = "/idTela02") },
                        clickB2 = { navigationController.navigate(route = "/idTela03") }
                    )
                }
                //Tela02
                composable (route = "/idTela02") {
                    Tela(
                        nomeTela = "Quanto é 1+1?",
                        clickB1 = { navigationController.navigate(route = "/home") },
                        clickB2 = { navigationController.navigate(route = "/idTela03") }
                    )
                }
                //Tela03
                composable (route = "/idTela03") {
                    Tela(
                        nomeTela = "Tela03",
                        clickB1 = { navigationController.navigate(route = "/idTela02") },
                        clickB2 = { navigationController.navigate(route = "/idTela03") }
                    )
                }
            }
        }
    }
}

@Composable
fun Tela(
    nomeTela: String,
    clickB1: () -> Unit,
    clickB2: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(nomeTela)
        Button(onClick = clickB1) { Text(text = "Iniciar caça ao tesouro") }
        Button(onClick = clickB2) { Text(text = "B2") }
    }
}


@Preview(showBackground = true)
@Composable
fun Teste() {
    Tela(nomeTela = "Tela teste", {}, {})
}