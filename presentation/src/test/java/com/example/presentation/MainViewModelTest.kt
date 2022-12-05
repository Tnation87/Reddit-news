package com.example.presentation

import com.example.presentation.models.NewsUiModel
import com.example.presentation.news.MainContract
import com.example.presentation.news.MainViewModel
import com.example.repos.models.NewsItem
import com.example.use_cases.GetNewsUseCase
import com.example.use_cases.SelectArticleUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.`when`

class MainViewModelTest {

    private lateinit var viewModel: MainViewModel

    @Mock
    private lateinit var getNewsUseCase: GetNewsUseCase

    @Mock
    private lateinit var selectArticleUseCase: SelectArticleUseCase

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(StandardTestDispatcher())
        viewModel = MainViewModel(
            getNewsUseCase,
            selectArticleUseCase
        )
    }

    @Test
    fun successfulGetNewsScenario() = runBlocking {
        `when`(getNewsUseCase()).thenReturn(listOf(NewsItem("", "", "", "")))

        viewModel.handleIntent(MainContract.MainIntent.ShowArticles)

        val testObserver = viewModel.uiState.take(2).toList()

        assert(testObserver[0] is MainContract.MainViewState.Loading)
        assert(testObserver[1] is MainContract.MainViewState.ListSuccess)
    }

    @Test
    fun failingGetNewsScenario() = runBlocking {
        `when`(getNewsUseCase()).thenReturn(emptyList())

        viewModel.handleIntent(MainContract.MainIntent.ShowArticles)

        val testObserver = viewModel.uiState.take(2).toList()

        assert(testObserver[0] is MainContract.MainViewState.Loading)
        assert(testObserver[1] is MainContract.MainViewState.ListError)
    }

    @Test
    fun successfulSelectArticleScenario() = runBlocking {
        viewModel.handleIntent(MainContract.MainIntent.OnArticleClicked(NewsUiModel("", "", "", "")))

        val testObserver = viewModel.uiState.take(2).toList()

        assert(testObserver[0] is MainContract.MainViewState.Loading)
        assert(testObserver[1] is MainContract.MainViewState.ViewSuccess)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun cleanup() {
        Dispatchers.resetMain()
    }

}