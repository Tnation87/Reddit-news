package com.example.cashe.news

import com.example.cashe.AppDatabase
import com.example.cashe.CacheInteractor
import com.example.cashe.models.NewsCache
import dagger.Reusable
import javax.inject.Inject

@Reusable
class NewsCacheInteractor @Inject constructor(
    private val appDatabase: AppDatabase,
) : CacheInteractor {

    fun getNews(): List<NewsCache>? {
        return appDatabase.newsDao().get()
    }

    fun putNews(news: List<NewsCache>) {
        appDatabase.newsDao().insertNews(news)
    }

}
