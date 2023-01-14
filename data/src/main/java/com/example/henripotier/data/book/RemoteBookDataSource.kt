package com.example.henripotier.data.book

import kotlinx.coroutines.flow.Flow

interface RemoteBookDataSource {
    fun getBooks(): Flow<List<BookDto>>
}