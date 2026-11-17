package com.example.ejerciciosviewmodel.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ejerciciosviewmodel.shared.RaveViewModel

@Composable
fun RaveInTheRiver(
    modifier: Modifier = Modifier,
    raveViewModel: RaveViewModel = viewModel()
) {
    // Pa que veais que pasa si no lo pongo en una clase
    val backgroundColor by raveViewModel.backgroundColor.collectAsState()
    val modoSeleccionado by raveViewModel.modoSeleccionado.collectAsState()
    val estaEjecutando by raveViewModel.estaEjecutando.collectAsState()
    val tiempoRestante by raveViewModel.tiempoRestante.collectAsState()

    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        CuadroTextoRave(
            state = raveViewModel.segundosState,
            label = "Introduce un número de segundos"
        )

        Spacer(modifier = Modifier.height(16.dp))

        RadioGroupRave(
            opciones = listOf("SUAVE", "MEDIO", "A TOPE"),
            seleccionado = modoSeleccionado,
            onSeleccionarModo = { raveViewModel.seleccionarModo(it) }
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (estaEjecutando) {
            Text(
                text = "¡Party activada! Tiempo restante: ${tiempoRestante}s",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        } else {
            Text(text = "Rave lista", fontSize = 20.sp)
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                focusManager.clearFocus()
                raveViewModel.iniciarRave()
            },
            enabled = !estaEjecutando
        ) {
            Text(if (estaEjecutando) "¡En proceso!" else "Empezar!")
        }
    }
}

@Composable
fun CuadroTextoRave(
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
fun RadioGroupRave(
    opciones: List<String>,
    seleccionado: String,
    onSeleccionarModo: (String) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        opciones.forEach { opcion ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = (opcion == seleccionado),
                    onClick = { onSeleccionarModo(opcion) }
                )
                Text(text = opcion)
            }
        }
    }
}