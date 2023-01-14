package com.example.henripotier.data.di

import android.content.Context
import com.example.henripotier.data.R
import com.example.henripotier.data.book.RemoteBookDataSource
import com.example.henripotier.data.book.RemoteBookRepository
import com.example.henripotier.data.book.RetrofitBookDataSource
import com.example.henripotier.data.network.client.RetrofitClient
import com.example.henripotier.domain.repository.BookRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsBookRepository(remoteBookRepository: RemoteBookRepository): BookRepository

    @Binds
    fun bindsNetworkBookDataSource(retrofitBookDataSource: RetrofitBookDataSource): RemoteBookDataSource
}

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun providesRetrofitClient(@ApplicationContext context: Context): Retrofit =
        RetrofitClient.create(context.getString(R.string.base_url))
}
