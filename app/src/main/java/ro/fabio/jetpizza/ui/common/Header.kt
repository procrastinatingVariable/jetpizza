package ro.fabio.jetpizza.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ro.fabio.jetpizza.ui.ImageAssets

@Composable
fun Header(
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
