package com.example.use_cases

import com.example.repos.models.NewsItem
import com.example.repos.news.NewsRepo
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetNewsUseCase @Inject constructor(private val newsRepo: NewsRepo) {
    suspend operator fun invoke(
    ): List<NewsItem> {
        return newsRepo.getNews()?.filterNotNull().orEmpty()
    }
}