package com.example.ejerciciosviewmodel.shared

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.toList
import kotlin.random.Random

class LMViewModel : ViewModel(){

    // Variable que representa los datos
    private val _listaValores = MutableStateFlow(mutableStateListOf<Int>().apply { addAll(obtenerDatos()) })
    val listaValores = _listaValores.asStateFlow()

    // Tened cuidado y diferenciad entre lista mutable (controla la UI y gestiona el estado)
    // con un List normal (cambios a dicho tipo NO conllevan una recomposición).

    // TODO: Función que devuelve una lista con 100 elementos entre el 0 y el 30.
    fun obtenerDatos() : ArrayList<Int>{

        return ArrayList<Int>()
    }
    fun rellenarLista(){
        // TODO: Esta función limpia la lista de valores y la vuelve a recargar
    }

    fun anyadirElemento(){
       // TODO: Esta función añade un elemento a la lista de valores.
       // TODO: Debe añadirlo en PRIMERA posición

    }




}