package kib.dev.basicscodelab.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kib.dev.basicscodelab.models.CreditCardData
import kib.dev.basicscodelab.ui.components.CreditCard
import kib.dev.basicscodelab.ui.components.Greeting
import kib.dev.basicscodelab.utils.PreviewUtil

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {},
        content = { innerPadding ->
            Surface(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(vertical = 16.dp, horizontal = 16.dp),
            ) {
                Column(modifier = Modifier) {
                    ShowCreditCard()

                    Spacer(modifier = Modifier.height(16.dp))

                    ShowGreetings()
                }
            }
        },
        bottomBar = {}
    )
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewHomeScreen() {
    PreviewUtil {
        HomeScreen(modifier = Modifier)
    }
}

@Composable
fun ShowCreditCard(
    modifier: Modifier = Modifier,
) {
    var isFlipped by remember { mutableStateOf(false) }

    val cardData = CreditCardData(
        cardNumber = "4111 •••• •••• 1111",
        cardHolderName = "JOHN DOE",
        expiryDate = "12/25",
        cvv = "•••",
        isFlipped = isFlipped
    )

    Surface(
        modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp),
    ) {
        CreditCard(
            cardData = cardData,
            onFlip = { isFlipped = !isFlipped }
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewShowCreditCard() {
    PreviewUtil {
        ShowCreditCard(modifier = Modifier)
    }
}

@Composable
fun ShowGreetings(modifier: Modifier = Modifier) {
    val names: List<String> = List(500) { "Volume ${it + 1}" }

    LazyColumn(modifier = modifier) {
        items(count = names.size) {
            Greeting(name = names[it])
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewShowGreetings() {
    PreviewUtil {
        ShowGreetings(modifier = Modifier)
    }
}
