package ro.fabio.jetpizza.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ro.fabio.jetpizza.model.Pizza
import ro.fabio.jetpizza.model.allPizzas
import ro.fabio.jetpizza.model.formattedPrice

@Composable
fun PizzaInfo(pizza: Pizza, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        UrlImage(
            url = pizza.imageUrl,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )

        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
            Text(
                pizza.name,
                style = MaterialTheme.typography.headlineMedium,
            )

            Text(
                pizza.description,
                style = MaterialTheme.typography.bodyMedium,
            )

            Text(
                pizza.formattedPrice,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.End)
            )
        }
    }
}

@Preview
@Composable
fun PizzaInfoPreview() {
    PizzaInfo(allPizzas.first())
}