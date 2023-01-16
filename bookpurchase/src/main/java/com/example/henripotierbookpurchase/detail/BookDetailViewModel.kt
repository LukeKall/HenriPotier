package com.example.henripotierbookpurchase.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.henripotier.domain.model.Book
import com.example.henripotier.domain.usecase.GetBookUseCase
import com.example.henripotier.mvi.BaseViewModel
import com.example.henripotierbookpurchase.catalog.BookPurchaseArgumentKeys
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModel @Inject constructor(
    private val getBookUseCase: GetBookUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel(BookDetailState.Loading) {

    private val isbn = savedStateHandle.get<String>(BookPurchaseArgumentKeys.ISBN.key)?.let { Book.ISBN(it) }

    init {
        load()
    }

    fun reload() {
        makeAction { setState(BookDetailState.Loading) }
        load()
    }

    private fun load() {
        viewModelScope.launch {
            isbn?.run {
                getBookUseCase(this).collectLatest { book ->
                    makeAction {
                        setState(book.getState())
                    }
                }
            } ?: makeAction { setState(BookDetailState.Error) }
        }
    }

    private fun Book?.getState() = this?.let { BookDetailState.Loaded(it) } ?: BookDetailState.Error
}