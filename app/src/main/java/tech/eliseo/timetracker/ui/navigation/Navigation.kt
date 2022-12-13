package tech.eliseo.timetracker.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import tech.eliseo.timetracker.ui.screen.analytics.AnalyticsScreen
import tech.eliseo.timetracker.ui.screen.categorylist.CategoryListScreen
import tech.eliseo.timetracker.ui.screen.main.MainScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "main"
    ) {
        composable(route = "main") {
            MainScreen(navController = navController, viewModel = hiltViewModel())
        }

        composable("category_list") {
            CategoryListScreen(
                navController = navController,
                viewModel = hiltViewModel(it)
            )
        }

        composable("analytics") {
            AnalyticsScreen(
                navController = navController,
                viewModel = hiltViewModel(it)
            )
        }

        /*dialog(
        route = ProductDetailNavigation.route,
        arguments = ProductDetailNavigation.arguments,
    ) {
        ProductDialogView(
            hiltViewModel(it)
        )
    }*/


    }
}