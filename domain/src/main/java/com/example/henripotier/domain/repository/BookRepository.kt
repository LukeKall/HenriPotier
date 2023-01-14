package com.example.henripotier.domain.repository

import com.example.henripotier.domain.model.Book
import kotlinx.coroutines.flow.Flow

interface BookRepository {

    fun getBooks(): Flow<List<Book>>
}