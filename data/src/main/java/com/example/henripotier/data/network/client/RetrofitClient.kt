package com.example.henripotier.data.network.client

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

object RetrofitClient {
    fun create(baseUrl: String): Retrofit = create(baseUrl.toHttpUrl())

    @OptIn(ExperimentalSerializationApi::class)
    fun create(baseUrl: HttpUrl): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(Json.asConverterFactory(contentType))
            .baseUrl(baseUrl)
            .build()

    private val contentType = "application/json".toMediaType()
}