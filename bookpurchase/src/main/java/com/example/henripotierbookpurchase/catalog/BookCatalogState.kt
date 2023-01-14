package com.example.henripotierbookpurchase.catalog

import com.example.henripotier.domain.model.Book
import com.example.henripotier.mvi.UIState

sealed class BookCatalogState : UIState {
    object Loading : BookCatalogState()
    data class Loaded(val books: List<Book>) : BookCatalogState()
}