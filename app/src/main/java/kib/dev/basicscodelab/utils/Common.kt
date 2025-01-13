package kib.dev.basicscodelab.utils

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import kib.dev.basicscodelab.ui.theme.BasicsCodelabTheme

@Composable
fun PreviewUtil(content: @Composable () -> Unit) {
    BasicsCodelabTheme {
        Surface(content = content)
    }
}
