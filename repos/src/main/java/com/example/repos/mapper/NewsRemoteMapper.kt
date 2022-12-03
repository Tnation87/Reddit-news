package com.example.repos.mapper

import com.example.remote.api.models.NewsRemote
import com.example.repos.models.NewsItem

class NewsRemoteMapper : ItemMapper<NewsRemote, NewsItem> {
    override fun mapFromItem(model: NewsItem): NewsRemote {
        throw UnsupportedOperationException()
    }

    override fun mapToItem(model: NewsRemote): NewsItem {
        return with(model) {
            NewsItem(
                id = id,
                title = title,
                thumbnail = thumbnail,
                body = body
            )
        }
    }

}