package com.example.article_counting_app.navigation

sealed class Routes(val route: String) {
    data object ArticleList : Routes("article_list")
    data object CreateArticle : Routes("create_article")
    data object CountArticle : Routes("count_article/{articleId}") {
        fun withId(articleId: String): String = "count_article/$articleId"
    }
}
