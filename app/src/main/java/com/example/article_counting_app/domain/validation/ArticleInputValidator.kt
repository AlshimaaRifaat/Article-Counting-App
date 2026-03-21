package com.example.article_counting_app.domain.validation

object ArticleInputValidator {
    fun validateArticleName(name: String): String? {
        return if (name.trim().length < 3) {
            "Name must contain at least 3 characters."
        } else {
            null
        }
    }

    fun validateArticleNumber(number: String): String? {
        return if (number.trim().length != 7) {
            "Article number must be exactly 7 digits."
        } else {
            null
        }
    }

    fun validateCount(count: Int?): String? {
        return if (count == null || count !in 0..999) {
            "Count must be a number between 0 and 999."
        } else {
            null
        }
    }
}
