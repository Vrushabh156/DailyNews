package com.appsvit.dailynews.data.models

data class NewsItem(
    val articles: List<Article>,
    val status: String?,
    val totalResults: Int?,
)

