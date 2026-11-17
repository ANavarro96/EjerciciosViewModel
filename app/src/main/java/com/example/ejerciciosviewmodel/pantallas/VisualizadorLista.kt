package com.example.ejerciciosviewmodel.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ejerciciosviewmodel.shared.LMViewModel

@Composable
fun VisualizadorLista() {
    val viewModelListas: LMViewModel = viewModel()

    val lista by viewModelListas.listaValores.collectAsState()
    val mayoresQueDiez by viewModelListas.mayoresQueDiez.collectAsState()
    val menoresQueDiez by viewModelListas.menoresQueDiez.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Todos los valores: ${lista.size} elementos", fontSize = 22.sp)
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(lista) { valor ->
                CardNumero(valor = valor)
            }
        }

        Text(text = "Aquellos mayores que 10: ${mayoresQueDiez.size} elementos", fontSize = 22.sp)
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(mayoresQueDiez) { valor ->
                CardNumero(valor = valor)
            }
        }

        Text(text = "Aquellos menores que 10: ${menoresQueDiez.size} elementos", fontSize = 22.sp)
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(menoresQueDiez) { valor ->
                CardNumero(valor = valor)
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { viewModelListas.rellenarLista() }) {
                Text("Recargar lista")
            }

            Button(onClick = { viewModelListas.anyadirElemento() }) {
                Text("Añadir elemento")
            }
        }
    }
}

@Composable
fun CardNumero(valor: Int) {
    Card(modifier = Modifier.size(100.dp)) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = valor.toString(),
                fontSize = 32.sp,
                textAlign = TextAlign.Center,
                color = Color.Blue
            )
        }
    }
}