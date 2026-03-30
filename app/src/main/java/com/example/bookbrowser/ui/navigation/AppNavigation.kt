package com.example.bookbrowser.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.bookbrowser.BuildConfig
import com.example.bookbrowser.ui.screens.BookDetailsScreen
import com.example.bookbrowser.ui.screens.BookListScreen
import com.example.bookbrowser.ui.screens.DebugScreen
import com.example.bookbrowser.ui.screens.FeatureScreen

object Screen {
    const val List = "book_list"
    const val Details = "book_details/{bookId}"
    const val Feature = "feature"
    const val Debug = "debug"
}

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController, startDestination = Screen.List) {

        composable(Screen.List) {
            BookListScreen(
                onBookClick = { id ->
                    navController.navigate("book_details/$id")
                },
                onNavigateToFeature = {
                    navController.navigate(Screen.Feature)
                },
                onNavigateToDebug = if (BuildConfig.DEBUG) {
                    { navController.navigate(Screen.Debug) }
                } else null
            )
        }

        composable(
            route = Screen.Details,
            arguments = listOf(
                navArgument("bookId") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            BookDetailsScreen(
                bookId = backStackEntry.arguments?.getString("bookId"),
                onBack = { navController.popBackStack() }
            )
        }

        // Задание 7 - экран feature (premium/free)
        composable(Screen.Feature) {
            FeatureScreen(onBack = { navController.popBackStack() })
        }

        // Задание 5 - debug экран
        if (BuildConfig.DEBUG) {
            composable(Screen.Debug) {
                DebugScreen(onBack = { navController.popBackStack() })
            }
        }
    }
}