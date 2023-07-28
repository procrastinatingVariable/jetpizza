package ro.fabio.jetpizza.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ro.fabio.jetpizza.MainActivity
import ro.fabio.jetpizza.ui.checkout.CheckoutScreen
import ro.fabio.jetpizza.ui.configure.ConfigureScreen
import ro.fabio.jetpizza.ui.home.HomeScreen


@Composable
fun Main() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = "🍕🤷‍♀️",
            fontSize = 150.sp,
        )
    }
}