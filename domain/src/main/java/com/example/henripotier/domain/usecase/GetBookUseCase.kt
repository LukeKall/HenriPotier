package com.example.henripotier.domain.usecase

import com.example.henripotier.domain.model.Book
import com.example.henripotier.domain.repository.BookRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetBookUseCase @Inject constructor(private val bookRepository: BookRepository) {

    operator fun invoke(isbn: Book.ISBN) = bookRepository.getBooks().map { books -> books.firstOrNull { it.isbn == isbn } }
}