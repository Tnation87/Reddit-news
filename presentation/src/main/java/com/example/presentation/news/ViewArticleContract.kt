package com.example.presentation.news

import com.example.presentation.UiIntent
import com.example.presentation.UiState
import com.example.presentation.models.NewsUiModel

class ViewArticleContract {

    object GetArticleIntent : UiIntent

    sealed class ViewArticleState: UiState {
        object Idle : ViewArticleState()
        object Loading : ViewArticleState()
        object Error: ViewArticleState()
        data class Success(val article: NewsUiModel): ViewArticleState()
    }
}