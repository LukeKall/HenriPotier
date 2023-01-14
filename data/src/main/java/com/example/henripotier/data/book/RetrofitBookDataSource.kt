package com.example.henripotier.data.book

import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import javax.inject.Inject
import javax.inject.Singleton

interface BookApi {
    @GET("books")
    suspend fun getBooks(): List<BookDto>
}

@Singleton
class RetrofitBookDataSource @Inject constructor(retrofit: Retrofit) : RemoteBookDataSource {

    private val bookApi = retrofit.create<BookApi>()

    override fun getBooks() = flow {
        emit(bookApi.getBooks())
    }
}