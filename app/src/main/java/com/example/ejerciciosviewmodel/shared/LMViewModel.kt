package com.example.ejerciciosviewmodel.shared

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.random.Random

class LMViewModel : ViewModel() {

    // Tres flujos sencillos para controlar el estado de cada lista
    private val _listaValores = MutableStateFlow<List<Int>>(emptyList())
    val listaValores = _listaValores.asStateFlow()

    private val _mayoresQueDiez = MutableStateFlow<List<Int>>(emptyList())
    val mayoresQueDiez = _mayoresQueDiez.asStateFlow()

    private val _menoresQueDiez = MutableStateFlow<List<Int>>(emptyList())
    val menoresQueDiez = _menoresQueDiez.asStateFlow()

    // esto se lanza una vez, al principio, como si fuese el constructor
    init {
        rellenarLista()
    }

    // Devuelve una lista con 100 números aleatorios entre 0 y 30
    fun obtenerDatos(): ArrayList<Int> {
        val listaNueva = arrayListOf<Int>()
        repeat(100) { // for (i in 0..100)
            listaNueva.add(Random.nextInt(0, 31))
        }
        return listaNueva
    }

    // Función auxiliar sencilla para actualizar las tres listas juntas
    private fun actualizarListas(nuevaLista: List<Int>) {
        _listaValores.value = nuevaLista
        _mayoresQueDiez.value = nuevaLista.filter { it > 10 }
        _menoresQueDiez.value = nuevaLista.filter { it < 10 }
    }

    // Recarga la lista desde cero
    fun rellenarLista() {
        actualizarListas(obtenerDatos())
    }

    // Añade un elemento al principio de la lista
    fun anyadirElemento() {
        val nuevaLista = mutableListOf(Random.nextInt(0, 31))
        nuevaLista.addAll(_listaValores.value)
        actualizarListas(nuevaLista)
        /** Esto lo hago así porque gestiono 3 listas, y como le dije a Guille,
         * Hay que gestionar las 3
         *
         *
         * Si no, me serviría:
         * _listaValores.value.add(Random.nextInt(0,31))
         */
    }
}