package com.example.article_counting_app.presentation.count

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.article_counting_app.di.AppContainer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class CountArticleUiState(
    val articleId: String,
    val title: String = "Count Article",
    val countInput: String = "",
    val countError: String? = null,
    val shouldNavigateBack: Boolean = false
)

class CountArticleViewModel(
    private val articleId: String
) : ViewModel() {

    private val _uiState = MutableStateFlow(CountArticleUiState(articleId = articleId))
    val uiState: StateFlow<CountArticleUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val article = AppContainer.getArticleByIdUseCase(articleId)
            if (article != null) {
                _uiState.value = _uiState.value.copy(
                    title = "Count ${article.name}",
                    countInput = article.count?.toString().orEmpty()
                )
            }
        }
    }

    fun onCountChange(value: String) {
        val filtered = value.filter(Char::isDigit).take(3)
        _uiState.value = _uiState.value.copy(countInput = filtered, countError = null)
    }

    fun onSave() {
        val count = _uiState.value.countInput.toIntOrNull()
        if (count == null || count !in 0..999) {
            _uiState.value = _uiState.value.copy(countError = "Count must be a number between 0 and 999.")
            return
        }

        viewModelScope.launch {
            AppContainer.updateArticleCountUseCase(articleId = articleId, count = count)
            _uiState.value = _uiState.value.copy(shouldNavigateBack = true)
        }
    }

    fun onNavigatedBack() {
        _uiState.value = _uiState.value.copy(shouldNavigateBack = false)
    }
}

class CountArticleViewModelFactory(
    private val articleId: String
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CountArticleViewModel(articleId = articleId) as T
    }
}
