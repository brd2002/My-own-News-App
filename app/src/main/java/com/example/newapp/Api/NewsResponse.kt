package com.example.newapp.Api

data class NewsResponse(
    val available: Int,
    val news: List<News>,
    val number: Int,
    val offset: Int
)