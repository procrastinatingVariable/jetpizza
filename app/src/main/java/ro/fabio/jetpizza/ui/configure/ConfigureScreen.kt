package ro.fabio.jetpizza.ui.configure

import androidx.compose.runtime.Composable
import ro.fabio.jetpizza.model.allPizzas

@Composable
fun ConfigureScreen(pizzaId: Long) {
    ConfigureUi(allPizzas.first { pizza -> pizza.id == pizzaId })
}