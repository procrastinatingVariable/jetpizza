@file:OptIn(ExperimentalMaterial3Api::class)

package ro.fabio.jetpizza.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ro.fabio.jetpizza.model.Pizza
import ro.fabio.jetpizza.model.allPizzas
import ro.fabio.jetpizza.model.formattedPrice
import ro.fabio.jetpizza.ui.ImageAssets
import ro.fabio.jetpizza.ui.common.UrlImage

@Composable
fun HomeScreen(onPizzaSelected: (Pizza) -> Unit) {
    Surface {
        val searchResult = remember { mutableStateOf(allPizzas) }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            item {
                Header(
                    onProfileClick = { /* don't do anything */ },
                    onFavoritesClick = { /* don't do anything */ },
                    onMoreClick = { /* don't do anything */ },
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                )
            }

            item {
                SearchField(
                    onSearchClick = { query ->
                        searchResult.value = allPizzas.filter { pizza -> pizza.name.contains(query, ignoreCase = true) }
                    }
                )
            }

            items(searchResult.value) { pizza ->
                PizzaListItem(
                    pizza = pizza,
                    onPizzaClick = onPizzaSelected
                )
            }
        }
    }
}

@Composable
private fun Header(
    modifier: Modifier = Modifier,
    onProfileClick: () -> Unit,
    onFavoritesClick: () -> Unit,
    onMoreClick: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            ImageAssets.Logo,
            "logo",

            )

        Row {
            OutlinedIconButton(
                onClick = onProfileClick,
            ) {
                Icon(
                    Icons.Outlined.Person,
                    contentDescription = "profile"
                )
            }

            OutlinedIconButton(
                onClick = onFavoritesClick,
            ) {
                Icon(
                    Icons.Outlined.Favorite,
                    contentDescription = "favorite"
                )
            }

            OutlinedIconButton(
                onClick = onMoreClick,
            ) {
                Icon(
                    Icons.Outlined.MoreVert,
                    contentDescription = "profile"
                )
            }
        }
    }
}

@Composable
private fun SearchField(
    onSearchClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val query = remember { mutableStateOf("") }
    OutlinedTextField(
        value = query.value,
        onValueChange = { newValue -> query.value = newValue },
        modifier = modifier.fillMaxWidth(),
        maxLines = 1,
        singleLine = true,
        label = {
            Text("Search")
        },
        trailingIcon = {
            Icon(
                Icons.Outlined.Search,
                "search",
                modifier = Modifier.clickable {
                    onSearchClick(query.value)
                }
            )
        }
    )
}

@Composable
private fun PizzaListItem(
    pizza: Pizza,
    onPizzaClick: (Pizza) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedCard(
        onClick = { onPizzaClick(pizza) },
        modifier = modifier.fillMaxWidth()
    ) {
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
fun PizzaListItemPreview() {
    PizzaListItem(
        pizza = allPizzas.first(),
        onPizzaClick = {}
    )
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        onPizzaSelected = {}
    )
}