package ro.fabio.jetpizza.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import ro.fabio.jetpizza.R

object ImageAssets {
    val Logo: Painter
        @Composable get() = painterResource(R.drawable.ic_logo)
}