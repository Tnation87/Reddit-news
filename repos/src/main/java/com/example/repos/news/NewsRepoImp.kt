package com.example.repos.news

import com.example.cashe.news.NewsCacheInteractor
import com.example.remote.api.ApiServiceInteractor
import com.example.remote.api.errors.ResponseError
import com.example.repos.mapper.Mappers.newsCacheMapper
import com.example.repos.mapper.Mappers.newsRemoteMapper
import com.example.repos.models.NewsItem
import javax.inject.Inject

class NewsRepoImp @Inject constructor(
    private val apiService: ApiServiceInteractor,
    private val interactor: NewsCacheInteractor
) : NewsRepo {

    override suspend fun getNews(): List<NewsItem?>?  {
        return try {
            // First try to get data from remote
            val newsList = apiService.getKotlinNewsAsync().await().data?.children ?: return null
            val mappedList = newsList.map { newsRemoteMapper.mapToItem(it.newsItem ?: return@map null) }
            interactor.putNews(mappedList.mapNotNull { newsCacheMapper.mapFromItem(it ?: return@mapNotNull null) })
            mappedList
        } catch (e: ResponseError) {
            // Error getting data from remote, so we will try to get it from local cache
            interactor.getNews().orEmpty().map { newsCacheMapper.mapToItem(it) }
        }
    }

    override fun selectArticle(article: NewsItem) {
        interactor.selectedArticle = newsCacheMapper.mapFromItem(article)
    }

    override fun getSelectedArticle(): NewsItem? {
        return newsCacheMapper.mapToItem(interactor.selectedArticle ?: return null)
    }
}