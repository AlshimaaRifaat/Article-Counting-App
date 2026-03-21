package com.example.article_counting_app.di

import com.example.article_counting_app.data.repository.InMemoryArticleRepository
import com.example.article_counting_app.domain.repository.ArticleRepository
import com.example.article_counting_app.domain.usecase.AddArticleUseCase
import com.example.article_counting_app.domain.usecase.GetArticleByIdUseCase
import com.example.article_counting_app.domain.usecase.GetArticlesUseCase
import com.example.article_counting_app.domain.usecase.UpdateArticleCountUseCase

object AppContainer {
    private val repository: ArticleRepository = InMemoryArticleRepository()

    val getArticlesUseCase = GetArticlesUseCase(repository)
    val addArticleUseCase = AddArticleUseCase(repository)
    val updateArticleCountUseCase = UpdateArticleCountUseCase(repository)
    val getArticleByIdUseCase = GetArticleByIdUseCase(repository)
}
