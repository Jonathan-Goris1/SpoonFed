package com.googleplaystore.spoonfed.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.googleplaystore.spoonfed.presentation.home_screen.HomeRoute


fun NavGraphBuilder.homeNavGraph(
    onRecipeClick: (Int) -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit,
) {
    navigation(
        startDestination = Screens.Home.route,
        route = HOME_ROUTE
    ) {
        composable(Screens.Home.route) {
            HomeRoute(onRecipeClick)
        }
        nestedGraphs()

    }

}