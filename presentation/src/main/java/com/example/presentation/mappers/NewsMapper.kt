package com.example.presentation.mappers

import com.example.presentation.models.NewsUiModel
import com.example.repos.models.NewsItem

class NewsMapper :
    UiModelMapper<NewsItem, NewsUiModel> {
        override fun mapFromUiModel(model: NewsUiModel): NewsItem {
            throw UnsupportedOperationException()
        }

        override fun mapToUiModel(model: NewsItem): NewsUiModel {
            return with(model) {
                NewsUiModel(
                    id = id,
                    thumbnail = thumbnail,
                    title = title,
                    body = body,
                    selected = false
                )
            }
        }
    }