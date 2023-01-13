package com.example.henripotier.mvi

fun interface Action {
    suspend fun invoke(currentState: UIState)
}