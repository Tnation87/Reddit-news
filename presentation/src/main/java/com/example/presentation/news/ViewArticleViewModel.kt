package com.example.presentation.news

import com.example.presentation.BaseViewModel
import com.example.presentation.mappers.Mappers.newsMapper
import com.example.use_cases.GetSelectedArticleUseCase
import javax.inject.Inject

class ViewArticleViewModel @Inject constructor(val getSelectedArticleUseCase: GetSelectedArticleUseCase) :
    BaseViewModel<ViewArticleContract.GetArticleIntent, ViewArticleContract.ViewArticleState>() {
    override fun createInitialState(): ViewArticleContract.ViewArticleState {
        return ViewArticleContract.ViewArticleState.Idle
    }

    override fun handleIntent(intent: ViewArticleContract.GetArticleIntent) {
        // Set Loading
        setState { ViewArticleContract.ViewArticleState.Loading }

        val selectedArticle = getSelectedArticleUseCase()
        if (selectedArticle != null)
            setState { ViewArticleContract.ViewArticleState.Success(newsMapper.mapToUiModel(selectedArticle)) }
        else setState { ViewArticleContract.ViewArticleState.Error }
    }
}