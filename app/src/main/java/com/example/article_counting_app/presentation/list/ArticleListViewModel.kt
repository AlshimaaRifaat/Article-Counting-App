package com.example.article_counting_app.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.article_counting_app.di.AppContainer
import com.example.article_counting_app.domain.model.Article
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

data class ArticleListUiState(
    val articles: List<Article> = emptyList()
)

class ArticleListViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ArticleListUiState())
    val uiState: StateFlow<ArticleListUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            AppContainer.getArticlesUseCase().collectLatest { items ->
                _uiState.value = _uiState.value.copy(articles = items)
            }
        }
    }
}
