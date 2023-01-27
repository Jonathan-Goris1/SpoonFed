package com.googleplaystore.spoonfed.presentation.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.googleplaystore.spoonfed.presentation.detail_screen.DetailScreen
import com.googleplaystore.spoonfed.presentation.home_screen.HomeScreen

fun NavGraphBuilder.homeNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = Screens.Home.route,
        route = HOME_ROUTE
    ) {
        composable(Screens.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(
            route = "${Screens.DetailScreen.route}?recipeId={recipeId}",
            arguments = listOf(
                navArgument(name = "recipeId") {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )

        ) { backStackEntry ->
            DetailScreen(
                recipeID = backStackEntry.arguments?.getInt("recipeId"),
                onNavigateBackToHomeScreen = { navController.popBackStack() })

        }

    }

}