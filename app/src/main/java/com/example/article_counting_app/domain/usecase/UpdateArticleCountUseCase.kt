package com.example.article_counting_app.domain.usecase

import com.example.article_counting_app.domain.repository.ArticleRepository

class UpdateArticleCountUseCase(
    private val repository: ArticleRepository
) {
    suspend operator fun invoke(articleId: String, count: Int) {
        repository.updateCount(articleId = articleId, count = count)
    }
}
