package com.example.ejerciciosviewmodel.navegacion

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.ejerciciosviewmodel.pantallas.ConversorTemperaturas
import com.example.ejerciciosviewmodel.pantallas.ConversorTemperaturasSinVM
import com.example.ejerciciosviewmodel.pantallas.PantallaMenu
import com.example.ejerciciosviewmodel.pantallas.RaveInTheRiver
import com.example.ejerciciosviewmodel.pantallas.VisualizadorLista
import com.example.ejerciciosviewmodel.shared.Rutas

@Composable
fun GrafoNavegacion() {
    val backStack = rememberNavBackStack(Rutas.Menu)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Rutas.Menu> {
                PantallaMenu(
                    onNavigateToTemperaturaSinVM = { backStack.add(Rutas.TemperaturaSinVM) },
                    onNavigateToTemperatura = { backStack.add(Rutas.Temperatura) },
                    onNavigateToRave = { backStack.add(Rutas.Rave) },
                    onNavigateToListas = { backStack.add(Rutas.Listas) }
                )
            }

            entry<Rutas.TemperaturaSinVM> {
                ConversorTemperaturasSinVM()
            }

            entry<Rutas.Temperatura> {
                ConversorTemperaturas()
            }

            entry<Rutas.Rave> {
                RaveInTheRiver()
            }

            entry<Rutas.Listas> {
                VisualizadorLista()
            }
        }
    )
}