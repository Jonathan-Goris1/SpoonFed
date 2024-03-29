package com.googleplaystore.spoonfed.presentation.navigation

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

private const val TAG = "NavHost"
@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun AppNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = HOME_ROUTE

    ) {

        homeNavGraph(
            onRecipeClick = { recipeID ->
                Log.d(TAG, "Before NavHost Call: $recipeID")
                navController.navigateToDetail(recipeID)
                Log.d(TAG, "After NavHost Call: $recipeID")
            }
        )

        detailNavGraph(
            onBackClick = navController::popBackStack
        )

    }

}