package com.example.ejerciciosviewmodel.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun PantallaMenu(
    onNavigateToTemperaturaSinVM: () -> Unit,
    onNavigateToTemperatura: () -> Unit,
    onNavigateToRave: () -> Unit,
    onNavigateToListas: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        BotonMenu(texto = "Ejercicio 0", funcion = onNavigateToTemperaturaSinVM)
        BotonMenu(texto = "Ejercicio 1", funcion = onNavigateToTemperatura)
        BotonMenu(texto = "Ejercicio 2", funcion = onNavigateToRave)
        BotonMenu(texto = "Ejercicio 3", funcion = onNavigateToListas)
    }
}

@Composable
fun BotonMenu(texto: String, funcion: () -> Unit) {
    Button(onClick = funcion) {
        Text(texto)
    }
}