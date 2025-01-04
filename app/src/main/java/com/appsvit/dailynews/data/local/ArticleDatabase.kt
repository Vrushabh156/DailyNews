package com.appsvit.dailynews.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.appsvit.dailynews.data.models.Article

@Database(entities = [Article::class], version = 1)
@TypeConverters(SourceConverter::class)
abstract class ArticleDatabase: RoomDatabase() {
    abstract val articlesDao: ArticlesDao
}