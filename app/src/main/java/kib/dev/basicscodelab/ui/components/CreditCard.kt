package kib.dev.basicscodelab.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kib.dev.basicscodelab.models.CreditCardData
import kib.dev.basicscodelab.utils.PreviewUtil

@Composable
fun CreditCard(
    cardData: CreditCardData,
    modifier: Modifier = Modifier,
    onFlip: () -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { onFlip() },
        color = MaterialTheme.colorScheme.primary
    ) {
        if (!cardData.isFlipped) {
            CreditCardFront(cardData)
        } else {
            CreditCardBack(cardData)
        }
    }
}

@Composable
private fun CreditCardFront(
    cardData: CreditCardData,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Chip and Logo Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            EmvChip()
            CardLogo()
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Card Number
        Text(
            text = cardData.cardNumber,
            style = MaterialTheme.typography.titleLarge.copy(
                fontFamily = FontFamily.Monospace,
                color = Color.White
            ),
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        // Card Holder and Expiry Date Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "CARD HOLDER",
                    style = MaterialTheme.typography.labelSmall.copy(color = Color.White.copy(alpha = 0.7f))
                )
                Text(
                    text = cardData.cardHolderName,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = Color.White,
                        fontFamily = FontFamily.Monospace
                    )
                )
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "VALID THRU",
                    style = MaterialTheme.typography.labelSmall.copy(color = Color.White.copy(alpha = 0.7f))
                )
                Text(
                    text = cardData.expiryDate,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = Color.White,
                        fontFamily = FontFamily.Monospace
                    )
                )
            }
        }
    }
}

@Composable
private fun CreditCardBack(
    cardData: CreditCardData,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        // Magnetic Stripe
        Spacer(modifier = Modifier.height(24.dp))
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            color = Color.Black.copy(alpha = 0.8f)
        ) { }

        Spacer(modifier = Modifier.height(16.dp))

        // CVV Strip
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            Surface(
                modifier = Modifier
                    .weight(0.8f)
                    .height(40.dp),
                color = Color.White
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = cardData.cvv,
                        modifier = Modifier.padding(horizontal = 8.dp),
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Security Text
        Text(
            text = "This card is property of the issuing bank.\nUse subject to cardholder agreement.",
            style = MaterialTheme.typography.labelSmall.copy(color = Color.White),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
private fun EmvChip(
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .width(45.dp)
            .height(35.dp),
        color = Color(0xFFFFD700),
        shape = RoundedCornerShape(4.dp)
    ) { }
}

@Composable
private fun CardLogo(
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .size(40.dp),
        color = Color.White,
        shape = RoundedCornerShape(20.dp)
    ) { }
}

@Preview(name = "Credit Card Front - Light")
@Preview(
    name = "Credit Card Front - Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun CreditCardFrontPreview() {
    MaterialTheme {
        CreditCard(
            cardData = CreditCardData(),
            onFlip = {}
        )
    }
}

@Preview(name = "Credit Card Back - Light")
@Preview(
    name = "Credit Card Back - Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun CreditCardBackPreview() {
    MaterialTheme {
        CreditCard(
            cardData = CreditCardData(isFlipped = true),
            onFlip = {}
        )
    }
}

/// Example usage in a screen
@Composable
fun SampleCreditCardScreen() {
    var isFlipped by remember { mutableStateOf(false) }

    val cardData = CreditCardData(
        cardNumber = "4111 •••• •••• 1111",
        cardHolderName = "JOHN DOE",
        expiryDate = "12/25",
        cvv = "•••",
        isFlipped = isFlipped
    )

    Scaffold(
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CreditCard(
                    cardData = cardData,
                    onFlip = { isFlipped = !isFlipped }
                )
            }
        }
    )
}

@Preview(name = "Credit Card Screen", showSystemUi = true)
@Preview(
    name = "Credit Card Screen - Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showSystemUi = true,
)
@Composable
fun CreditCardScreenPreview() {
    PreviewUtil {
        SampleCreditCardScreen()
    }
}
