package com.example.repos.mapper

import com.example.cashe.models.NewsCache
import com.example.repos.models.NewsItem

class NewsCacheMapper: ItemMapper<NewsCache, NewsItem> {
    override fun mapFromItem(model: NewsItem): NewsCache {
        throw UnsupportedOperationException()
    }

    override fun mapToItem(model: NewsCache): NewsItem {
        return with(model) {
            NewsItem(
                id = id,
                thumbnail = thumbnail,
                title = title,
                body = body
            )
        }
    }
}