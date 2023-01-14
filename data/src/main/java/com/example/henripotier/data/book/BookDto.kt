package com.example.henripotier.data.book

import com.example.henripotier.domain.model.Book
import com.example.henripotier.domain.model.CENT

@kotlinx.serialization.Serializable
data class BookDto(
    val isbn: String,
    val title: String,
    val price: Int,
    val cover: String,
    val synopsis: List<String>
) {
    fun toDomain() = Book(
        isbn = Book.ISBN(isbn),
        title = title,
        priceInCents = price * CENT,
        coverUrl = cover,
        synopsis = synopsis
    )
}
