package com.example.article_counting_app.domain.usecase

import com.example.article_counting_app.domain.model.Article
import com.example.article_counting_app.domain.repository.ArticleRepository
import kotlinx.coroutines.flow.Flow

class GetArticlesUseCase(
    private val repository: ArticleRepository
) {
    operator fun invoke(): Flow<List<Article>> = repository.observeArticles()
}
