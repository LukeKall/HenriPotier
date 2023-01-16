package com.example.henripotier.designsystem.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.henripotier.domain.model.CENT
import java.text.NumberFormat
import java.util.*

@Composable
fun Price(priceInCents: Int, modifier: Modifier = Modifier) =
    Text(
        text = formatPrice(priceInCents),
        style = MaterialTheme.typography.titleSmall,
        modifier = modifier
    )

fun formatPrice(priceInCents: Int): String =
    NumberFormat.getCurrencyInstance(Locale.getDefault()).apply {
        currency = Currency.getInstance(Locale.FRANCE)
    }.format(priceInCents / CENT.toDouble())