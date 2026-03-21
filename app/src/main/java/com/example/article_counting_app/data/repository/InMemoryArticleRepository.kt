package com.example.article_counting_app.data.repository

import com.example.article_counting_app.domain.model.Article
import com.example.article_counting_app.domain.repository.ArticleRepository
import java.util.UUID
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class InMemoryArticleRepository(
    seedData: List<Article> = MockArticles.seed()
) : ArticleRepository {

    private val articles = MutableStateFlow(seedData)

    override fun observeArticles(): Flow<List<Article>> = articles.asStateFlow()

    override suspend fun addArticle(name: String, number: String) {
        articles.update { current ->
            current + Article(
                id = UUID.randomUUID().toString(),
                name = name,
                number = number,
                count = null
            )
        }
    }

    override suspend fun updateCount(articleId: String, count: Int) {
        articles.update { current ->
            current.map { article ->
                if (article.id == articleId) {
                    article.copy(count = count)
                } else {
                    article
                }
            }
        }
    }

    override suspend fun getArticleById(articleId: String): Article? {
        return articles.value.firstOrNull { it.id == articleId }
    }
}
