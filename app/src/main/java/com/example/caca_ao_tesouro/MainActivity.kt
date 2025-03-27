package com.example.caca_ao_tesouro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost

import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "home"
            ) {
               // Tela01
                composable ("home") {
                    TelaHome(
                        nomeTela = "Home",
                        clickB1 = { navController.navigate("Tela01") }
                    )
                }
                //Tela02
                composable ("Tela01") {
                    TelaPergunta(
                        nomeTela = "Pergunta01",
                        pergunta = "Quanto é 1+1?",
                        resposta = "2",
                        proximo = { navController.navigate("Tela02")},
                        anterior = { navController.navigate("Home") }
                    )
                }
                //Tela03
                composable ("Tela02") {
                    TelaPergunta(
                        nomeTela = "Pergunta02",
                        pergunta = "Quanto é 2+2?",
                        resposta = "4",
                        proximo = { navController.navigate("Tela03")},
                        anterior = { navController.navigate("Tela01") }
                    )
                }
                //Tela03
                composable ("Tela03") {
                    TelaPergunta(
                        nomeTela = "Pergunta03",
                        pergunta = "Quanto é 3+3?",
                        resposta = "6",
                        proximo = { navController.navigate("TelaPremio")},
                        anterior = { navController.navigate("Tela02") }
                    )
                }
                composable ("TelaPremio") {
                    TelaFinal(
                        clickB1 = { navController.navigate("Tela01") }
                    )
                }
            }
        }
    }
}

@Composable
fun TelaHome(
    nomeTela: String,
    clickB1: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Que tal uma caça ao tesouro?")
        Button(onClick = clickB1) { Text(text = "Iniciar caça ao tesouro") }
    }
}

@Composable
fun TelaFinal(
    clickB1: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Parabéns, caça ao tesouro finalizada!")
        Button(onClick = clickB1) { Text(text = "Voltar a Home") }
    }
}

@Composable
fun TelaPergunta(
    nomeTela: String,
    pergunta: String,
    resposta: String,
    proximo: () -> Unit,
    anterior: () -> Unit
) {
    val (textoDigitado, setTextoDigitado) = remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = pergunta)

        TextField(
            value = textoDigitado,
            onValueChange = {
                setTextoDigitado(it)
            },
            label = { Text("Digite a resposta") },
            modifier = Modifier.padding(top = 24.dp)
        )

        Row(
            modifier = Modifier.padding(top = 25.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Button(onClick = {
                if (textoDigitado.lowercase() == resposta.lowercase()) {
                    proximo()
                }
            }) {
                Text("Próximo")
            }
            Button(onClick = anterior) {
                Text("Voltar")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Teste() {
    TelaPergunta(
        nomeTela = "Pergunta01",
        pergunta = "Quanto é 1+1?",
        resposta = "2",
        proximo = {},
        anterior = {}
    )
    TelaHome(
        nomeTela = "Home",
        clickB1 =  {}
    )
    TelaFinal(
        clickB1 = {}
    )
}