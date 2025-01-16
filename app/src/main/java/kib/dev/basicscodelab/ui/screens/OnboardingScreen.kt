package kib.dev.basicscodelab.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kib.dev.basicscodelab.utils.PreviewUtil

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    onContinue: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Welcome to the Basics Codelab!")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onContinue,
        ) {
            Text("Continue")
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    PreviewUtil {
        OnboardingScreen(
            onContinue = {
                Log.d("Greeting", "Showing more")
            },
        )
    }
}
