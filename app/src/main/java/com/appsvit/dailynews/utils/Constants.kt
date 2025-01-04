package com.appsvit.dailynews.utils

object Constants {
    const val DATABASE_NAME: String = "articleDb"
    const val ENTITY_NAME: String = "article"
    const val ERR: String = "An unknown error has occurred!"
    const val FIRST: String = "first"
    const val LOSING: String = "Poor Connection!"
    const val UNAVAILABLE: String = "No internet connection! Showing limited content"
    const val BASE_URL: String = "https://newsapi.org/v2/"
    const val API_KEY: String = "bebd2f761eb04962bf9ef7066007972f"
    val categories = listOf(
        "General",
        "Business",
        "Politics",
        "Science",
        "Sports",
        "Health",
        "Entertainment",
        "Technology"
    )
}