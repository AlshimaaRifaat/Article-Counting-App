package com.example.article_counting_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.article_counting_app.navigation.ArticleNavHost
import com.example.article_counting_app.ui.theme.ArticleCountingAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArticleCountingAppTheme {
                ArticleNavHost()
            }
        }
    }
}