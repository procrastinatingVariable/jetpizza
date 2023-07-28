package ro.fabio.jetpizza.ui.configure

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ro.fabio.jetpizza.model.Pizza
import ro.fabio.jetpizza.model.Sauce
import ro.fabio.jetpizza.model.allPizzas
import ro.fabio.jetpizza.model.sauces
import ro.fabio.jetpizza.ui.common.Header
import ro.fabio.jetpizza.ui.common.Minus
import ro.fabio.jetpizza.ui.common.PizzaInfo

enum class PizzaSize {
    SMALL, MEDIUM, LARGE;
}

@Composable
fun ConfigureUi(pizza: Pizza) {
    val selectedCrustType = remember { mutableStateOf(CrustType.THIN) }
    val sauceSelection = remember {
        val selection = sauces.map { SauceSelection(it, 0) }
        mutableStateOf(selection)
    }
    Box {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(8.dp)
                .padding(bottom = CheckoutButtonHeight)
        ) {
            Header(
                onProfileClick = { /* don't do anything */ },
                onFavoritesClick = { /* don't do anything */ },
                onMoreClick = { /* don't do anything */ },
                modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
            )

            PizzaInfo(
                pizza = pizza,
            )

            val selectedSize = remember { mutableStateOf(PizzaSize.MEDIUM) }
            SizeSelector(
                size = selectedSize.value,
                onSizeChanged = { newSize -> selectedSize.value = newSize }
            )

            Divider(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            )

            PizzaConfiguration(
                selectedCrustType = selectedCrustType.value,
                onCrustTypeSelected = { newValue ->
                    selectedCrustType.value = newValue
                },
                selectedSauces = sauceSelection.value,
                onSauceSelectionUpdated = { newSelection -> sauceSelection.value = newSelection }
            )

        }

        val totalSum = pizza.price + sauceSelection.value.sumOf { selection ->
            (selection.amount * selection.sauce.price).toDouble()
        }
        CheckoutButton(
            totalSum = totalSum.toFloat(),
            onClick = {},
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

val CheckoutButtonHeight = 64.dp
@Composable
fun CheckoutButton(
    totalSum: Float,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        onClick = onClick,
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier
            .fillMaxWidth()
            .height(CheckoutButtonHeight)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                Icons.Filled.ShoppingCart,
                contentDescription = "checkout",
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(64.dp)
            )

            Text(
                text = "%.2f LEI".format(totalSum),
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Composable
private fun SizeSelector(
    size: PizzaSize,
    onSizeChanged: (PizzaSize) -> Unit,
    modifier: Modifier = Modifier
) {
    val sizeProgress = when (size) {
        PizzaSize.SMALL -> 0f
        PizzaSize.MEDIUM -> 0.5f
        PizzaSize.LARGE -> 1f
    }
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom,
        ) {
            Text("S", style = MaterialTheme.typography.labelSmall)
            Text("M", style = MaterialTheme.typography.labelMedium)
            Text("L", style = MaterialTheme.typography.labelLarge)
        }

        Slider(
            value = sizeProgress,
            onValueChange = { newValue ->
                when (newValue) {
                    0f -> onSizeChanged(PizzaSize.SMALL)
                    0.5f -> onSizeChanged(PizzaSize.MEDIUM)
                    1f -> onSizeChanged(PizzaSize.LARGE)
                }
            },
            steps = 3,
            modifier = modifier,
        )
    }
}

data class SauceSelection(
    val sauce: Sauce,
    val amount: Int
) {
    fun increment(): SauceSelection {
        return copy(amount = amount + 1)
    }

    fun decrement(): SauceSelection {
        return copy(amount = amount - 1)
    }
}

@Composable
fun PizzaConfiguration(
    selectedCrustType: CrustType,
    onCrustTypeSelected: (CrustType) -> Unit,
    selectedSauces: List<SauceSelection>,
    onSauceSelectionUpdated: (List<SauceSelection>) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        CrustSelector(
            selectedType = selectedCrustType,
            onTypeSelected = onCrustTypeSelected
        )

        Divider(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )

        SauceSelector(
            sauceSelection = selectedSauces,
            onSauceUpdated = onSauceSelectionUpdated,
        )
    }
}

enum class CrustType {
    THIN, THICK, WHOLE;
}

@Composable
fun CrustSelector(
    selectedType: CrustType,
    onTypeSelected: (CrustType) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Alege tipul de blat", modifier = Modifier.padding(horizontal = 8.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            CrustTypeButton(
                isSelected = selectedType == CrustType.THIN,
                text = "Subtire",
                onClick = { onTypeSelected(CrustType.THIN) },
                modifier = Modifier.weight(1f),
            )

            CrustTypeButton(
                isSelected = selectedType == CrustType.THICK,
                text = "Pufos",
                onClick = { onTypeSelected(CrustType.THICK) },
                modifier = Modifier.weight(1f)
            )
        }

        CrustTypeButton(
            isSelected = selectedType == CrustType.WHOLE,
            text = "Din faina integrala",
            onClick = { onTypeSelected(CrustType.WHOLE) },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun CrustTypeButton(
    isSelected: Boolean,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = if(isSelected) {
        ButtonDefaults.textButtonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    } else {
        ButtonDefaults.textButtonColors(
            containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
            contentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f)
        )
    }
    TextButton(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(50),
        colors = colors
    ) {
        Text(text)
    }
}

@Composable
fun SauceSelector(
    sauceSelection: List<SauceSelection>,
    onSauceUpdated: (List<SauceSelection>) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        sauceSelection.forEachIndexed { index, sauce ->
            SauceRow(
                sauce,
                onIncrementSauce = {
                    val mutableSelection = sauceSelection.toMutableList()
                    mutableSelection[index] = sauce.increment()
                    onSauceUpdated(mutableSelection)
                },
                onDecrementSauce = {
                    val mutableSelection = sauceSelection.toMutableList()
                    mutableSelection[index] = sauce.decrement()
                    onSauceUpdated(mutableSelection)
                }
            )
        }
    }
}

@Composable
fun SauceRow(
    selection: SauceSelection,
    onIncrementSauce: () -> Unit,
    onDecrementSauce: () -> Unit,
    modifier: Modifier = Modifier
) {
    val total = selection.amount * selection.sauce.price
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            selection.sauce.name,
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.weight(1f))

        QuantitySelector(
            selection.amount,
            onIncrement = onIncrementSauce,
            onDecrement = onDecrementSauce,
        )

        Text(
            "+ %.2f LEI".format(total),
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.width(100.dp),
            textAlign = TextAlign.End,
        )
    }
}

@Composable
fun QuantitySelector(
    amount: Int,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        OutlinedIconButton(onClick = onDecrement, enabled = amount > 0) {
            Icon(
                Icons.Filled.Minus,
                "decrement sauce",
            )
        }

        Text(
            amount.toString(),
            style = MaterialTheme.typography.labelLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.width(16.dp)
        )

        OutlinedIconButton(onClick = onIncrement, enabled = amount < 5) {
            Icon(
                Icons.Filled.Add,
                "increment sauce"
            )
        }
    }
}

@Preview
@Composable
fun ConfigureUiPreview() {
    ConfigureUi(
        allPizzas.first()
    )
}