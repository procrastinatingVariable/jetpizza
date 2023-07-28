package ro.fabio.jetpizza.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ro.fabio.jetpizza.MainActivity
import ro.fabio.jetpizza.ui.checkout.CheckoutScreen
import ro.fabio.jetpizza.ui.configure.ConfigureScreen
import ro.fabio.jetpizza.ui.home.HomeScreen
import java.lang.IllegalArgumentException


@Composable
fun Main() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(
                onPizzaSelected = { pizza ->
                    navController.navigate("configure/${pizza.id}")
                }
            )
        }

        composable(
            "configure/{pizzaId}",
            arguments = listOf(
                navArgument("pizzaId") { type = NavType.LongType }
            )
        ) { backstackEntry ->
            val args = backstackEntry.arguments
            val pizzaId = args?.getLong("pizzaId") ?: throw IllegalArgumentException("pizza id must not be null")
            ConfigureScreen(pizzaId)
        }

    }
}