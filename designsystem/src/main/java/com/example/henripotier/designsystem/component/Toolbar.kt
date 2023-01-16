package com.example.henripotier.designsystem.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.example.henripotier.designsystem.R

@Composable
fun ToolbarText(title: String) =
    Text(
        text = title,
        style = MaterialTheme.typography.headlineLarge,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )

@Composable
fun ToolbarNavigationIcon(goBack: () -> Unit) =
    IconButton(onClick = goBack) {
        Icon(Icons.Default.ArrowBack, contentDescription = stringResource(R.string.back))
    }
