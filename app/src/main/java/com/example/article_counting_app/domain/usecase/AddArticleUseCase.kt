package com.example.article_counting_app.domain.usecase

import com.example.article_counting_app.domain.repository.ArticleRepository

class AddArticleUseCase(
    private val repository: ArticleRepository
) {
    suspend operator fun invoke(name: String, number: String) {
        repository.addArticle(name = name, number = number)
    }
}
