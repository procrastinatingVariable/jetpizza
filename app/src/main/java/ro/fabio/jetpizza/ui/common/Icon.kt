package ro.fabio.jetpizza.ui.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.ui.graphics.vector.ImageVector

public val Icons.Filled.Minus: ImageVector
    get() {
        if (_minus != null) {
            return _minus!!
        }
        _minus = materialIcon(name = "Filled.Add") {
            materialPath {
                moveTo(19.0f, 10.5f)
                moveTo(19.0f, 10.5f)
                horizontalLineToRelative(-14f)
                verticalLineToRelative(2f)
                horizontalLineToRelative(14f)
                close()
            }
        }
        return _minus!!
    }

private var _minus: ImageVector? = null