package com.example.repos.news

import com.example.repos.models.NewsItem

interface NewsRepo {
    suspend fun getNews(): List<NewsItem?>?
}