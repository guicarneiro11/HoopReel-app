package com.guicarneirodev.hoopreel.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.guicarneirodev.hoopreel.feature.favorites.ui.FavoritesScreen
import com.guicarneirodev.hoopreel.feature.highlights.ui.HighlightsScreen
import com.guicarneirodev.hoopreel.feature.highlights.ui.details.PlayerDetailsScreen
import com.guicarneirodev.hoopreel.feature.highlights.ui.statistics.StatisticsScreen
import com.guicarneirodev.hoopreel.feature.player.ui.PlayerScreen
import com.guicarneirodev.hoopreel.feature.search.ui.SearchScreen
import com.guicarneirodev.hoopreel.feature.settings.ui.ThemeSelectionScreen

@Composable
fun AppNavigation(navController: NavController) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = NavDestination.Highlights.route
    ) {
        composable(NavDestination.Highlights.route) {
            HighlightsScreen(
                onVideoClick = { videoId ->
                    navController.navigate(
                        NavDestination.Player.createRoute(videoId)
                    )
                },
                onSeeAllClick = { playerId ->
                    navController.navigate(
                        NavDestination.PlayerDetails.createRoute(playerId)
                    )
                }
            )
        }

        composable(
            route = NavDestination.PlayerDetails.route,
            arguments = listOf(
                navArgument("playerId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val playerId = backStackEntry.arguments?.getString("playerId")
                ?: return@composable

            PlayerDetailsScreen(
                playerId = playerId,
                onVideoClick = { videoId ->
                    navController.navigate(
                        NavDestination.Player.createRoute(videoId)
                    )
                },
                onBackPressed = {
                    navController.popBackStack()
                }
            )
        }

        composable(NavDestination.Favorites.route) {
            FavoritesScreen(
                onVideoClick = { videoId ->
                    navController.navigate(
                        NavDestination.Player.createRoute(videoId)
                    )
                }
            )
        }

        composable(NavDestination.Search.route) {
            SearchScreen(
                onVideoClick = { videoId ->
                    navController.navigate(
                        NavDestination.Player.createRoute(videoId)
                    )
                }
            )
        }

        composable(
            route = NavDestination.Player.route,
            arguments = listOf(
                navArgument("videoId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val videoId = backStackEntry.arguments?.getString("videoId")
                ?: return@composable

            PlayerScreen(
                videoId = videoId,
                onBackPressed = {
                    navController.popBackStack()
                }
            )
        }

        composable(NavDestination.Statistics.route) {
            StatisticsScreen()
        }

        composable(NavDestination.ThemeSelection.route) {
            ThemeSelectionScreen(
                onBackPressed = {
                    navController.popBackStack()
                }
            )
        }
    }
}