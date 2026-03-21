package com.example.article_counting_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.article_counting_app.presentation.count.CountArticleScreen
import com.example.article_counting_app.presentation.create.CreateArticleScreen
import com.example.article_counting_app.presentation.list.ArticleListScreen

@Composable
fun ArticleNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.ArticleList.route
    ) {
        composable(route = Routes.ArticleList.route) {
            ArticleListScreen(
                onAddClick = { navController.navigate(Routes.CreateArticle.route) },
                onArticleClick = { articleId ->
                    navController.navigate(Routes.CountArticle.withId(articleId))
                }
            )
        }
        composable(route = Routes.CreateArticle.route) {
            CreateArticleScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable(
            route = Routes.CountArticle.route,
            arguments = listOf(navArgument("articleId") { type = NavType.StringType })
        ) { backStackEntry ->
            val articleId = backStackEntry.arguments?.getString("articleId").orEmpty()
            CountArticleScreen(
                articleId = articleId,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
