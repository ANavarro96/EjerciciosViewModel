package com.example.ejerciciosviewmodel.shared

import androidx.compose.foundation.text.input.TextFieldState
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.math.roundToInt

// Clase que representa TODO el estado de la pantalla (propiedades inmutables val)
data class PantallaTemperaturaState(
    val redondeo: Boolean = false,
    val resultado: String = "",
    val texto: TextFieldState = TextFieldState("")
)

class TemperaturaViewModel : ViewModel() {

    private val _pantallaTemperaturaState = MutableStateFlow(PantallaTemperaturaState())
    val pantallaTemperaturaState = _pantallaTemperaturaState.asStateFlow()

    fun actualizarSwitch(nuevoValor: Boolean) {
        _pantallaTemperaturaState.update { currentState ->
            currentState.copy(redondeo = nuevoValor)
        }
    }

    fun actualizarResultado() {
        val current = _pantallaTemperaturaState.value
        val tempText = current.texto.text.toString()
        val tempDouble = tempText.toDoubleOrNull()

        val nuevoResultado = if (tempDouble != null) {
            val fahrenheit = (tempDouble * 1.8) + 32
            if (current.redondeo) {
                "${fahrenheit.roundToInt()} °F"
            } else {
                "${String.format("%.2f", fahrenheit)} °F"
            }
        } else {
            "Introduce una temperatura válida"
        }

        _pantallaTemperaturaState.update { currentState ->
            currentState.copy(resultado = nuevoResultado)
        }
    }
}