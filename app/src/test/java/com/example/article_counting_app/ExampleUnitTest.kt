package com.example.article_counting_app

import com.example.article_counting_app.data.repository.InMemoryArticleRepository
import com.example.article_counting_app.domain.validation.ArticleInputValidator
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test

class ExampleUnitTest {
    @Test
    fun validator_rejects_short_article_name() {
        val error = ArticleInputValidator.validateArticleName("ab")
        assertEquals("Name must contain at least 3 characters.", error)
    }

    @Test
    fun validator_accepts_valid_article_inputs() {
        assertNull(ArticleInputValidator.validateArticleName("Notebook"))
        assertNull(ArticleInputValidator.validateArticleNumber("1234567"))
        assertNull(ArticleInputValidator.validateCount(999))
    }

    @Test
    fun repository_adds_article_and_updates_count() = runBlocking {
        val repository = InMemoryArticleRepository(seedData = emptyList())

        repository.addArticle(name = "Pencil", number = "7654321")
        val created = repository.observeArticles().first().firstOrNull()
        assertNotNull(created)
        assertEquals("Pencil", created?.name)
        assertEquals("7654321", created?.number)
        assertNull(created?.count)

        val createdId = created!!.id
        repository.updateCount(articleId = createdId, count = 55)
        val updated = repository.getArticleById(createdId)
        assertEquals(55, updated?.count)
    }
}