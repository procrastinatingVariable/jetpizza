package ro.fabio.jetpizza.model


data class Sauce(
    val name: String,
    val price: Float,
)


val sauces = listOf(
    Sauce("Sos dulce", 8.99f),
    Sauce("Sos picant", 9.99f),
)