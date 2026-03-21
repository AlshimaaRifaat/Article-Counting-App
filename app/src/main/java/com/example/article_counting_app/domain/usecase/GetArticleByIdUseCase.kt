package com.example.article_counting_app.domain.usecase

import com.example.article_counting_app.domain.model.Article
import com.example.article_counting_app.domain.repository.ArticleRepository

class GetArticleByIdUseCase(
    private val repository: ArticleRepository
) {
    suspend operator fun invoke(articleId: String): Article? {
        return repository.getArticleById(articleId)
    }
}
