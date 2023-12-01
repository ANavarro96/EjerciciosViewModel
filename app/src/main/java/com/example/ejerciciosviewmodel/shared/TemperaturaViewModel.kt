package com.example.ejerciciosviewmodel.shared

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TemperaturaViewModel : ViewModel(){

    // Esta variable privada no es accesible desde fuera, y representa
    // el flow de nuestra variable de estado.
    private val _redondeo = MutableStateFlow(false)

    // Esta variable publica recoge y actualiza el valor del estado mutable, y es el que usará
    // la interfaz.
    val redondeo = _redondeo.asStateFlow()

    fun actualizarSwitch(nuevoValor : Boolean){
       _redondeo.value = nuevoValor
    }

}