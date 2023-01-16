package com.example.henripotierbookpurchase.catalog

import com.example.henripotier.domain.model.Book
import com.example.henripotier.mvi.UIEvent

sealed class BookCatalogEvent : UIEvent {
    data class GoToBookDetail(val isbn: Book.ISBN) : BookCatalogEvent()
}
