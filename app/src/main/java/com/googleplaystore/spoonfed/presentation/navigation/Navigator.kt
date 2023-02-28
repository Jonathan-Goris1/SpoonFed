package com.googleplaystore.spoonfed.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun Navigator(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = HOME_ROUTE

    ) {

        homeNavGraph(
            onRecipeClick = { recipeID ->
                navController.navigateToDetail(recipeID)
            },
            nestedGraphs = {
                detailScreen(
                    onBackClick = navController::popBackStack
                )
            }
        )

    }

}