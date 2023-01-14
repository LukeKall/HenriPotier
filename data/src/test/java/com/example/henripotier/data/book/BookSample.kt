package com.example.henripotier.data.book

import com.example.henripotier.domain.model.Book

val booksDto = listOf(
    BookDto(
        isbn = "c8fabf68-8374-48fe-a7ea-a00ccd07afff",
        title = "Henri Potier à l'école des sorciers",
        price = 35,
        cover = "https://url1.com",
        synopsis = listOf(
            "sysnopsis00",
            "sysnopsis01"
        )
    ),
    BookDto(
        isbn = "a460afed-e5e7-4e39-a39d-c885c05db861",
        title = "Henri Potier et la Chambre des secrets",
        price = 25,
        cover = "https://url2.com",
        synopsis = listOf(
            "sysnopsis10",
            "sysnopsis11"
        )
    ),
    BookDto(
        isbn = "fcd1e6fa-a63f-4f75-9da4-b560020b6acc",
        title = "Henri Potier et le Prisonnier d'Azkaban",
        price = 30,
        cover = "https://url3.com",
        synopsis = listOf(
            "sysnopsis10",
            "sysnopsis11"
        )
    )
)

val books = listOf(
    Book(
        isbn = Book.ISBN("c8fabf68-8374-48fe-a7ea-a00ccd07afff"),
        title = "Henri Potier à l'école des sorciers",
        priceInCents = 3500,
        coverUrl = "https://url1.com",
        synopsis = listOf(
            "sysnopsis00",
            "sysnopsis01"
        )
    ),
    Book(
        isbn = Book.ISBN("a460afed-e5e7-4e39-a39d-c885c05db861"),
        title = "Henri Potier et la Chambre des secrets",
        priceInCents = 2500,
        coverUrl = "https://url2.com",
        synopsis = listOf(
            "sysnopsis10",
            "sysnopsis11"
        )
    ),
    Book(
        isbn = Book.ISBN("fcd1e6fa-a63f-4f75-9da4-b560020b6acc"),
        title = "Henri Potier et le Prisonnier d'Azkaban",
        priceInCents = 3000,
        coverUrl = "https://url3.com",
        synopsis = listOf(
            "sysnopsis10",
            "sysnopsis11"
        )
    )
)