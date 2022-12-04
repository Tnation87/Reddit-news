package com.example.presentation.news

import androidx.lifecycle.viewModelScope
import com.example.presentation.BaseViewModel
import com.example.presentation.mappers.Mappers.newsMapper
import com.example.use_cases.GetNewsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(val newsUseCase: GetNewsUseCase) :
    BaseViewModel<MainContract.MainIntent, MainContract.MainViewState>() {
    override fun createInitialState(): MainContract.MainViewState {
        return MainContract.MainViewState.Idle
    }

    override fun handleIntent(intent: MainContract.MainIntent) {
        when (intent) {
            is MainContract.MainIntent.ShowArticles -> {
                getNews()
            }
            is MainContract.MainIntent.OnArticleClicked -> {

            }
        }
    }

    /**
     * Gets all news
     */
    private fun getNews() {
        viewModelScope.launch {
            // Set Loading
            setState { MainContract.MainViewState.Loading }

            val newsList = newsUseCase()
            if (newsList.isNotEmpty())
                setState {
                    MainContract.MainViewState.Success(newsList.map {
                        newsMapper.mapToUiModel(
                            it
                        )
                    })
                }
            else setState { MainContract.MainViewState.Error }
        }
    }
}