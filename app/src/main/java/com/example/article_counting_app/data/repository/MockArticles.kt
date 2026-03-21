package com.example.article_counting_app.data.repository

import com.example.article_counting_app.domain.model.Article

object MockArticles {
    fun seed(): List<Article> {
        return listOf(
            Article(id = "A-1", name = "Coffee Beans", number = "1000001", count = 14),
            Article(id = "A-2", name = "Tea Bags", number = "1000002", count = 3),
            Article(id = "A-3", name = "Paper Cups", number = "1000003", count = null),
            Article(id = "A-4", name = "Sugar Sticks", number = "1000004", count = null)
        )
    }
}
