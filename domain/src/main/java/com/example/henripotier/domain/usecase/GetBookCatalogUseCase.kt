package com.example.henripotier.domain.usecase

import com.example.henripotier.domain.repository.BookRepository
import javax.inject.Inject

class GetBookCatalogUseCase @Inject constructor(private val bookRepository: BookRepository) {

    operator fun invoke() = bookRepository.getBooks()
}