package kib.dev.basicscodelab.models

data class CreditCardData(
    val cardNumber: String = "•••• •••• •••• ••••",
    val cardHolderName: String = "CARDHOLDER NAME",
    val expiryDate: String = "••/••",
    val cvv: String = "•••",
    val isFlipped: Boolean = false
)
