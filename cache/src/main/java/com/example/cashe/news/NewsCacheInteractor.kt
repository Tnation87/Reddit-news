package com.example.cashe.news

import com.example.cashe.AppDatabase
import com.example.cashe.CacheInteractor
import com.example.cashe.inmemory.cache
import com.example.cashe.models.NewsCache
import dagger.Reusable
import javax.inject.Inject

private const val SELECTED_ARTICLE = "SELECTED_ARTICLE"

@Reusable
class NewsCacheInteractor @Inject constructor(
    private val appDatabase: AppDatabase,
) : CacheInteractor {

    var selectedArticle: NewsCache? by cache(SELECTED_ARTICLE)

    fun getNews(): List<NewsCache>? {
        return appDatabase.newsDao().get()
    }

    fun putNews(news: List<NewsCache>) {
        appDatabase.newsDao().insertNews(news)
    }

}
