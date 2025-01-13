package kib.dev.basicscodelab

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kib.dev.basicscodelab.models.CreditCardData
import kib.dev.basicscodelab.ui.components.CreditCard
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
            Greetings()
        }
    }
}

@Composable
fun Greetings(
    modifier: Modifier = Modifier,
    names: List<String> = List(500) { "Volume ${it + 1}" },
) {
    var isFlipped by remember { mutableStateOf(false) }

    val cardData = CreditCardData(
        cardNumber = "4111 •••• •••• 1111",
        cardHolderName = "JOHN DOE",
        expiryDate = "12/25",
        cvv = "•••",
        isFlipped = isFlipped
    )

    Scaffold(modifier = modifier) { innerPadding ->
        Surface(
            modifier = modifier.padding(innerPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(modifier = modifier) {
                /*names.forEach { name ->
                    Greeting(name, modifier = modifier)
                }*/
                Surface(modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                    CreditCard(
                        cardData = cardData,
                        onFlip = { isFlipped = !isFlipped }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
                    items(count = names.size) {
                        Greeting(name = names[it])
                    }
                }
            }

        }
    }
}

@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier,
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    val extraPadding by animateDpAsState(
        if (expanded) 24.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ), label = "greeting_expanded_animation_extraPadding"
    )
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp),
    ) {
        Row(modifier = Modifier.padding(12.dp)) {
            Column(
                modifier = Modifier
                    .weight(1F)
                    .padding(bottom = extraPadding.coerceAtLeast(0.dp))
            ) {
                Text(text = "Hello,")
                Text(
                    text = name,
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.ExtraBold),
                )
                if (expanded) {
                    Text(
                        text = "$name Lorem ipsum for the quick brown fox.",
                        modifier = Modifier.padding(top = 6.dp),
                    )
                }
            }
            ElevatedButton(
                onClick = {
                    Log.d("Greeting", "Showing more of $name")
                    expanded = !expanded
                },
                modifier = modifier,
            ) {
                Text(if (expanded) "Show less" else "Show more")
            }
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

@Preview(
    showBackground = true,
    showSystemUi = false,
    name = "Light",
)
@Preview(
    showBackground = true,
    name = "Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showSystemUi = false,
)
@Composable
fun GreetingPreview() {
    PreviewUtil {
        Greeting("Android")
    }
}
