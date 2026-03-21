package com.example.article_counting_app.domain.repository

import com.example.article_counting_app.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {
    fun observeArticles(): Flow<List<Article>>
    suspend fun addArticle(name: String, number: String)
    suspend fun updateCount(articleId: String, count: Int)
    suspend fun getArticleById(articleId: String): Article?
}
