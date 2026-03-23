package com.example.article_counting_app.presentation.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.article_counting_app.di.AppContainer
import com.example.article_counting_app.domain.validation.ArticleInputValidator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class CreateArticleUiState(
    val name: String = "",
    val number: String = "",
    val nameError: String? = null,
    val numberError: String? = null,
    val shouldNavigateBack: Boolean = false
)

class CreateArticleViewModel(
    private val addArticle: suspend (String, String) -> Unit = { name, number ->
        AppContainer.addArticleUseCase(name = name, number = number)
    },
    private val validateName: (String) -> String? = ArticleInputValidator::validateArticleName,
    private val validateNumber: (String) -> String? = ArticleInputValidator::validateArticleNumber
) : ViewModel() {
    private val _uiState = MutableStateFlow(CreateArticleUiState())
    val uiState: StateFlow<CreateArticleUiState> = _uiState.asStateFlow()

    fun onNameChange(value: String) {
        _uiState.value = _uiState.value.copy(name = value, nameError = null)
    }

    fun onNumberChange(value: String) {
        val filtered = value.filter(Char::isDigit).take(7)
        _uiState.value = _uiState.value.copy(number = filtered, numberError = null)
    }

    fun onSave() {
        val current = _uiState.value
        val name = current.name.trim()
        val number = current.number.trim()

        val nameError = validateName(name)
        val numberError = validateNumber(number)

        if (nameError != null || numberError != null) {
            _uiState.value = current.copy(nameError = nameError, numberError = numberError)
            return
        }

        viewModelScope.launch {
            addArticle(name, number)
            _uiState.value = _uiState.value.copy(shouldNavigateBack = true)
        }
    }

    fun onNavigatedBack() {
        _uiState.value = _uiState.value.copy(shouldNavigateBack = false)
    }
}
