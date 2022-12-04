package com.example.use_cases

import com.example.repos.models.NewsItem
import com.example.repos.news.NewsRepo
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetSelectedArticleUseCase @Inject constructor(private val newsRepo: NewsRepo) {
    operator fun invoke(): NewsItem? {
        return newsRepo.getSelectedArticle()
    }
}