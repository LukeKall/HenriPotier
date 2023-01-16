package com.example.henripotier.designsystem.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.example.henripotier.domain.model.CENT
import java.text.NumberFormat
import java.util.*

@Composable
fun Price(
    priceInCents: Int,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.titleSmall
) = Text(
    text = formatPrice(priceInCents),
    style = style,
    modifier = modifier
)

fun formatPrice(priceInCents: Int): String =
    NumberFormat.getCurrencyInstance(Locale.getDefault()).apply {
        currency = Currency.getInstance(Locale.FRANCE)
    }.format(priceInCents / CENT.toDouble())