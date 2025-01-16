package kib.dev.basicscodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kib.dev.basicscodelab.ui.screens.HomeScreen
import kib.dev.basicscodelab.ui.screens.OnboardingScreen
import kib.dev.basicscodelab.ui.theme.BasicsCodelabTheme
import kib.dev.basicscodelab.utils.PreviewUtil

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BasicsCodelabTheme {
                BasicsCodelabTheme {
                    MyApp(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}

@Composable
fun MyApp(
    modifier: Modifier = Modifier
) {
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    Surface(modifier = modifier) {
        if (shouldShowOnboarding) {
            OnboardingScreen(
                onContinue = {
                    shouldShowOnboarding = false
                },
            )
        } else {
            HomeScreen(modifier = modifier)
        }
    }
}

@Preview(name = "myApp-light", showSystemUi = true)
@Composable
fun MyAppPreview() {
    PreviewUtil {
        MyApp()
    }
}
