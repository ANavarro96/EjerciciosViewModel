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
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ejerciciosviewmodel.shared.TemperaturaViewModel

@Composable
fun ConversorTemperaturas(
    modifier: Modifier = Modifier,
    temperaturaViewModel: TemperaturaViewModel = viewModel()
) {
    val state by temperaturaViewModel.pantallaTemperaturaState.collectAsStateWithLifecycle()

    PantallaTemperaturas(
        redondeo = state.redondeo,
        result = state.resultado,
        temperaturaState = state.texto,
        onRedondeoChanged = { temperaturaViewModel.actualizarSwitch(it) },
        onCalcularClick = { temperaturaViewModel.actualizarResultado() },
        modifier = modifier
    )
}

@Composable
fun PantallaTemperaturas(
    redondeo: Boolean,
    result: String,
    temperaturaState: TextFieldState,
    onRedondeoChanged: (Boolean) -> Unit,
    onCalcularClick: () -> Unit,
    modifier: Modifier = Modifier
) {
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

        CuadroTexto(
            state = temperaturaState,
            label = "Introduce la temperatura"
        )

        FilaRedondeo(
            roundUp = redondeo,
            onRoundUpChanged = onRedondeoChanged
        )

        Text(
            text = result,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Button(onClick = onCalcularClick) {
            Text(text = "Cambiar temperatura")
        }
    }
}

@Composable
fun CuadroTexto(
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
fun FilaRedondeo(
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