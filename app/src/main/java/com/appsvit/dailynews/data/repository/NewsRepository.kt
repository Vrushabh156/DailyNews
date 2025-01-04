package com.appsvit.dailynews.data.repository

import com.appsvit.dailynews.data.models.Article
import com.appsvit.dailynews.data.models.NewsItem
import com.appsvit.dailynews.utils.Response
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getTopHeadlines(): Flow<Response<NewsItem?>>
    fun getHeadlinesByCategory(category: String): Flow<Response<NewsItem?>>
    fun getNewsByQuery(query: String): Flow<Response<NewsItem?>>
    fun fetchDataFromDb(): Flow<Response<List<Article>>>
}
