package com.example.ejerciciosviewmodel.shared

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface Rutas : NavKey {
    @Serializable
    data object Menu : Rutas

    @Serializable
    data object TemperaturaSinVM : Rutas

    @Serializable
    data object Temperatura : Rutas

    @Serializable
    data object Rave : Rutas

    @Serializable
    data object Listas : Rutas
}