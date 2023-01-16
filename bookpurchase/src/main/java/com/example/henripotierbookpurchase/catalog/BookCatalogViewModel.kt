package com.example.henripotierbookpurchase.catalog

import androidx.lifecycle.viewModelScope
import com.example.henripotier.domain.model.Book
import com.example.henripotier.domain.usecase.GetBookCatalogUseCase
import com.example.henripotier.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookCatalogViewModel @Inject constructor(getBookCatalogUseCase: GetBookCatalogUseCase) : BaseViewModel(BookCatalogState.Loading) {

    init {
        viewModelScope.launch {
            getBookCatalogUseCase().collectLatest { books ->
                makeAction {
                    setState(BookCatalogState.Loaded(books))
                }
            }
        }
    }

    fun seeBookDetail(isbn: Book.ISBN) = makeAction {
        sendEvent(BookCatalogEvent.GoToBookDetail(isbn))
    }
}