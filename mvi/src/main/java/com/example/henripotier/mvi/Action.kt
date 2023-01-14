package com.example.henripotier.mvi

interface Action {
    suspend fun invoke(currentState: UIState)
}