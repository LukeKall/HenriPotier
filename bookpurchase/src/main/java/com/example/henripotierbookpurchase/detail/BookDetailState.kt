package com.example.henripotierbookpurchase.detail

import com.example.henripotier.domain.model.Book
import com.example.henripotier.mvi.UIState

sealed class BookDetailState : UIState {
    object Loading : BookDetailState()
    data class Loaded(val book: Book) : BookDetailState()
    object Error : BookDetailState()
}