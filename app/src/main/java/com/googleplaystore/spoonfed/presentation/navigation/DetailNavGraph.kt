package com.googleplaystore.spoonfed.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.googleplaystore.spoonfed.presentation.detail_screen.DetailRoute

const val recipeIdArg = "recipeId"

fun NavController.navigateToDetail(recipeId: Int) {
    this.navigate("${Screens.DetailScreen.route}?recipeId={$recipeId}")
}


fun NavGraphBuilder.detailScreen(
    onBackClick: () -> Unit,
) {
    composable(
        route = "${Screens.DetailScreen.route}?recipeId={$recipeIdArg}",
        arguments = listOf(
            navArgument(recipeIdArg) {
                type = NavType.IntType
                defaultValue = 649977
            }
        )

    ) {
        DetailRoute(onBackClick = onBackClick)
    }

}