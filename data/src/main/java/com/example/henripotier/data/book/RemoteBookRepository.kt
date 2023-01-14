package com.example.henripotier.data.book

import com.example.henripotier.domain.model.Book
import com.example.henripotier.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteBookRepository @Inject constructor(private val remoteBookDataSource: RemoteBookDataSource) : BookRepository {
    override fun getBooks(): Flow<List<Book>> =
        runCatching {
            remoteBookDataSource.getBooks()
                .map { books -> books.map { it.toDomain() } }
        }.getOrElse { flowOf(emptyList()) }
}