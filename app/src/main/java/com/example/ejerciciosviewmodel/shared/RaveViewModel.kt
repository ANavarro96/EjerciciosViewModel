package com.example.ejerciciosviewmodel.shared

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ejerciciosdialogosylistas.utils.FuncionesUtiles
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class RaveViewModel : ViewModel() {

    val segundosState = TextFieldState("")

    private val _modoSeleccionado = MutableStateFlow("SUAVE")
    val modoSeleccionado = _modoSeleccionado.asStateFlow()

    private val _backgroundColor = MutableStateFlow(Color.White)
    val backgroundColor = _backgroundColor.asStateFlow()

    private val _estaEjecutando = MutableStateFlow(false)
    val estaEjecutando = _estaEjecutando.asStateFlow()

    private val _tiempoRestante = MutableStateFlow(0)
    val tiempoRestante = _tiempoRestante.asStateFlow()

    private var raveJob: Job? = null

    fun seleccionarModo(modo: String) {
        _modoSeleccionado.value = modo
    }

    fun iniciarRave() {
        val segundos = segundosState.text.toString().toIntOrNull()
        if (segundos == null || segundos <= 0) return

        raveJob?.cancel()

        val intervaloMs = when (_modoSeleccionado.value) {
            "SUAVE" -> 1000L
            "MEDIO" -> 500L
            "A TOPE" -> 150L
            else -> 1000L
        }

        // Un hilo
        raveJob = viewModelScope.launch {
            _estaEjecutando.value = true
            val tiempoFin = System.currentTimeMillis() + (segundos * 1000L)

            while (System.currentTimeMillis() < tiempoFin) {
                _backgroundColor.value = FuncionesUtiles.ColorRandom()
                val restante = ((tiempoFin - System.currentTimeMillis()) / 1000L).toInt() + 1
                _tiempoRestante.value = maxOf(0, restante)
                // sleep() en Java
                delay(intervaloMs.milliseconds)
            }

            _backgroundColor.value = Color.White
            _estaEjecutando.value = false
            _tiempoRestante.value = 0
        }
    }
}