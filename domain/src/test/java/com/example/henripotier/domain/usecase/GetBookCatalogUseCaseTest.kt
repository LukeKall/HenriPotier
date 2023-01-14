package com.example.henripotier.domain.usecase

import com.example.henripotier.domain.model.Book
import com.example.henripotier.domain.repository.BookRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class GetBookCatalogUseCaseTest {

    @MockK
    private lateinit var bookRepository: BookRepository

    private lateinit var getBookCatalogUseCase: GetBookCatalogUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getBookCatalogUseCase = GetBookCatalogUseCase(bookRepository)
    }

    @Test
    fun `get book catalog should return all books`() = runTest {
        every { bookRepository.getBooks() } returns flowOf(books)

        val catalog = getBookCatalogUseCase().first()

        assertEquals(books, catalog)
    }

    private val books = listOf(
        Book(
            isbn = Book.ISBN("isbn1"),
            title = "Title",
            priceInCents = 2000,
            coverUrl = "https://url.com",
            synopsis = listOf("sysnopsis1")
        ),
        Book(
            isbn = Book.ISBN("isbn1"),
            title = "1Title",
            priceInCents = 2000,
            coverUrl = "https://url.com",
            synopsis = listOf("sysnopsis2")
        ),
        Book(
            isbn = Book.ISBN("isbn1"),
            title = "BTitle",
            priceInCents = 2000,
            coverUrl = "https://url.com",
            synopsis = listOf("sysnopsis3")
        )
    )
}