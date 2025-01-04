package com.appsvit.dailynews.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("article")
data class Article(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val author: String? = "",
    val content: String? = "",
    val description: String? = "",
    val publishedAt: String? = "",
    val source: Source? = Source(),
    val title: String? = "",
    val url: String? = "",
    val urlToImage: String? = "",
)