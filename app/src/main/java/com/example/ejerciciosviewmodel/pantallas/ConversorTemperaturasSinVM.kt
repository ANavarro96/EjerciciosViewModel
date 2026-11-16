package com.example.ejerciciosviewmodel.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt




@Composable
fun ConversorTemperaturasSinVM(modifier: Modifier = Modifier) {
    val temperaturaState = rememberTextFieldState("")
    var redondeo by rememberSaveable { mutableStateOf(false) }
    var resultado by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier.padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Cambia de C a F",
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        CuadroTextoSinVM(
            state = temperaturaState,
            label = "Introduce la temperatura"
        )

        FilaRedondeoSinVM(
            roundUp = redondeo,
            onRoundUpChanged = { redondeo = it }
        )

        Text(
            text = resultado,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Button(onClick = {
            resultado = calcularTemperatura(temperaturaState, redondeo)
        }) {
            Text(text = "Cambiar temperatura")
        }
    }
}

fun calcularTemperatura(temperaturaState: TextFieldState, redondeo: Boolean) : String {
    val tempText = temperaturaState.text.toString()
    val tempDouble = tempText.toDoubleOrNull()
    if (tempDouble != null) {
        val fahrenheit = (tempDouble * 1.8) + 32
        return if (redondeo) {
            "${fahrenheit.roundToInt()} °F"
        } else {
            "${String.format("%.2f", fahrenheit)} °F"
        }
    } else {
        return "Introduce una temperatura válida"
    }
}


@Composable
private fun CuadroTextoSinVM(
    state: TextFieldState,
    label: String
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = label, modifier = Modifier.padding(bottom = 4.dp))
        OutlinedCard(modifier = Modifier.fillMaxWidth()) {
            BasicTextField(
                state = state,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}

@Composable
private fun FilaRedondeoSinVM(
    roundUp: Boolean,
    onRoundUpChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .size(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "¿Quieres redondear?")
        Switch(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End),
            checked = roundUp,
            onCheckedChange = onRoundUpChanged,
            colors = SwitchDefaults.colors(
                uncheckedThumbColor = Color.Yellow,
                checkedThumbColor = Color.Red
            )
        )
    }
}