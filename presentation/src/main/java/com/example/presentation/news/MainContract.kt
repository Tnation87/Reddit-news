package com.example.presentation.news

import com.example.presentation.UiIntent
import com.example.presentation.UiState
import com.example.presentation.models.NewsUiModel

class MainContract {

    sealed class MainIntent : UiIntent {
        data class OnArticleClicked(val article: NewsUiModel) : MainIntent()
        object ShowArticles : MainIntent()
    }

    sealed class MainViewState: UiState {
        object Idle : MainViewState()
        object Loading : MainViewState()
        data class ListSuccess(val newsList : List<NewsUiModel>) : MainViewState()
        object ListError: MainViewState()
        object ViewSuccess: MainViewState()
    }
}