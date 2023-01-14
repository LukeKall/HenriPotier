package com.example.henripotier.data.book

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
class RemoteBookRepositoryTest {

    @MockK
    private lateinit var remoteBookDataSource: RemoteBookDataSource

    private lateinit var remoteBookRepository: RemoteBookRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        remoteBookRepository = RemoteBookRepository(remoteBookDataSource)
    }

    @Test
    fun `getBooks should return domain books corresponding to datasource books dto`() = runTest {
        every { remoteBookDataSource.getBooks() } returns flowOf(booksDto)

        val result = remoteBookRepository.getBooks().first()

        assertEquals(books, result)
    }

    @Test
    fun `getBooks should return empty list when datasource returns error`() = runTest {
        every { remoteBookDataSource.getBooks() } throws IllegalStateException()

        val result = remoteBookRepository.getBooks().first()

        assertEquals(emptyList(), result)
    }
}