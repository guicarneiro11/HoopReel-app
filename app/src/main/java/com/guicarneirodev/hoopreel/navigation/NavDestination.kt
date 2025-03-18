package com.guicarneirodev.hoopreel.navigation

sealed class NavDestination (val route: String) {
    data object Highlights: NavDestination("highlights")
    data object Favorites: NavDestination("favorites")
    data object Player: NavDestination("player/{videoId}") {
        fun createRoute(videoId: String) = "player/$videoId"
    }
}