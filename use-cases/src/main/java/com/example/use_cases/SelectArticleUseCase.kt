package com.example.use_cases

import com.example.repos.models.NewsItem
import com.example.repos.news.NewsRepo
import dagger.Reusable
import javax.inject.Inject

@Reusable
class SelectArticleUseCase @Inject constructor(private val newsRepo: NewsRepo) {
    operator fun invoke(article: NewsItem) {
        newsRepo.selectArticle(article)
    }
}