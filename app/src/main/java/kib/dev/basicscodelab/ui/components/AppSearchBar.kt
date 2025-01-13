package kib.dev.basicscodelab.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kib.dev.basicscodelab.utils.PreviewUtil

@Composable
fun AppSearchBar(modifier: Modifier = Modifier) {
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.primary,
            focusedContainerColor = MaterialTheme.colorScheme.surface
        ),
        placeholder = {
//            Text(stringResource(R.string.placeholder_search))
            Text("The placey holder")
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(24.dp).padding(horizontal = 12.dp),
    )
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun AppSearchBarPreview() {
    PreviewUtil {
        Box(
            Modifier.fillMaxSize()
        ) {
            AppSearchBar(modifier = Modifier.align(Alignment.Center))
        }
    }
}
