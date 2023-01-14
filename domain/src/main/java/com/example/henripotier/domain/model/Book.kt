package com.example.henripotier.domain.model

data class Book(
    val isbn: ISBN,
    val title: String,
    val priceInCents: Int,
    val coverUrl: String,
    val synopsis: List<String>
) {
    @JvmInline
    value class ISBN(private val value: String)
}