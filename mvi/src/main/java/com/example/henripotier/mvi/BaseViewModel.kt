package com.example.henripotier.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class BaseViewModel(initialState: UIState = EmptyState) : ViewModel() {

    private val actionsChannel = Channel<Action>()
    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<UIState>
        get() = _state.asStateFlow()
    private val _events = MutableSharedFlow<UIEvent>()
    val events: SharedFlow<UIEvent>
        get() = _events.asSharedFlow()

    init {
        viewModelScope.launch {
            for (action in actionsChannel) {
                action.invoke(_state.value)
            }
        }
    }

    fun makeAction(action: suspend Action.(UIState) -> Unit) {
        actionsChannel.trySend(object : Action {
            override suspend fun invoke(currentState: UIState) {
                action(currentState)
            }
        })
    }

    override fun onCleared() {
        super.onCleared()
        actionsChannel.close()
    }

    protected fun Action.setState(state: UIState) {
        this@BaseViewModel._state.update { state }
    }

    protected suspend fun Action.sendEvent(event: UIEvent) {
        this@BaseViewModel._events.emit(event)
    }
}