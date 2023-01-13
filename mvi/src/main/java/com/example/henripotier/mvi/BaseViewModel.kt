package com.example.henripotier.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    private val actionsChannel = Channel<Action>()
    private val state = MutableStateFlow<UIState>(EmptyState)
    private val events = MutableSharedFlow<UIEvent>()

    init {
        viewModelScope.launch {
            for (action in actionsChannel) {
                action.invoke(state.value)
            }
        }
    }

    fun makeAction(action: Action) {
        actionsChannel.trySend(action)
    }

    override fun onCleared() {
        super.onCleared()
        actionsChannel.close()
    }

    protected fun Action.setState(state: UIState) {
        this@BaseViewModel.state.update { state }
    }

    protected suspend fun Action.sendEvent(event: UIEvent) {
        this@BaseViewModel.events.emit(event)
    }
}