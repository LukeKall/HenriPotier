package com.example.henripotier.data.di

import android.content.Context
import com.example.henripotier.data.R
import com.example.henripotier.data.network.client.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun providesRetrofitClient(@ApplicationContext context: Context): Retrofit =
        RetrofitClient.create(context.getString(R.string.base_url))
}
