package com.example.henripotier.mvi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun EventListener(
    events: Flow<UIEvent>,
    onEvent: (UIEvent, CoroutineScope) -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(events) {
        events.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED).onEach { onEvent(it, this) }.collect()
    }
}
