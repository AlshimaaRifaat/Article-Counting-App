package com.example.article_counting_app.domain.model

data class Article(
    val id: String,
    val name: String,
    val number: String,
    val count: Int? = null
)
