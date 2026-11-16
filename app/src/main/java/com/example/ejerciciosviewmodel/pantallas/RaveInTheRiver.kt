package com.example.ejerciciosviewmodel.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RaveInTheRiver(){
    val segundosState = rememberTextFieldState("")

    Column {
        CuadroTexto(
            state = segundosState,
            label = "Introduce un número de segundos"
        )
        Text("", fontSize = 25.sp)
        RadioGroup()
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { }, enabled = true) {
                Text("Empezar!")
            }
        }
    }
}


@Composable
fun RadioGroup() {
    val opciones = arrayListOf("SUAVE", "MEDIO", " A TOPE")
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        opciones.forEach {
            RadioButton(selected = false, onClick = { /*TODO*/ })
            Text(it)
        }
    }
}