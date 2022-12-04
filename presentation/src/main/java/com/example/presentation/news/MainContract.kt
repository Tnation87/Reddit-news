package com.example.presentation.news

import com.example.presentation.UiIntent
import com.example.presentation.UiState
import com.example.presentation.models.NewsUiModel

class MainContract {

    // Events that user performed
    sealed class MainIntent : UiIntent {
        object OnArticleClicked : MainIntent()
        object ShowArticles : MainIntent()
    }

    sealed class MainViewState: UiState {
        object Idle : MainViewState()
        object Loading : MainViewState()
        data class Success(val newsList : List<NewsUiModel>) : MainViewState()
        object Error: MainViewState()
    }
}