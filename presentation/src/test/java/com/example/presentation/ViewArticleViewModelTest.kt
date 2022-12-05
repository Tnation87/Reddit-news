package com.example.presentation

import com.example.presentation.news.ViewArticleContract
import com.example.presentation.news.ViewArticleViewModel
import com.example.repos.models.NewsItem
import com.example.use_cases.GetSelectedArticleUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ViewArticleViewModelTest {

    private lateinit var viewModel: ViewArticleViewModel

    @Mock
    private lateinit var getSelectedArticleUseCase: GetSelectedArticleUseCase

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(StandardTestDispatcher())
        viewModel = ViewArticleViewModel(
            getSelectedArticleUseCase
        )
    }

    @Test
    fun successfulScenario() = runBlocking {
        Mockito.`when`(getSelectedArticleUseCase()).thenReturn(NewsItem("", "", "", ""))

        viewModel.handleIntent(ViewArticleContract.GetArticleIntent)

        val testObserver = viewModel.uiState.take(2).toList()

        assert(testObserver[0] is ViewArticleContract.ViewArticleState.Loading)
        assert(testObserver[1] is ViewArticleContract.ViewArticleState.Success)
    }

    @Test
    fun failingScenario() = runBlocking {
        viewModel.handleIntent(ViewArticleContract.GetArticleIntent)

        val testObserver = viewModel.uiState.take(2).toList()

        assert(testObserver[0] is ViewArticleContract.ViewArticleState.Loading)
        assert(testObserver[1] is ViewArticleContract.ViewArticleState.Error)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun cleanup() {
        Dispatchers.resetMain()
    }
}