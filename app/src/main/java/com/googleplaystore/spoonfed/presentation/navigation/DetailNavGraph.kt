package com.googleplaystore.spoonfed.presentation.navigation

import android.util.Log
import androidx.compose.ui.Modifier
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.googleplaystore.spoonfed.presentation.detail_screen.DetailRoute

const val recipeIdArg = "recipeId"
private const val TAG = "detailNavGraph"
fun NavController.navigateToDetail(recipeId: Int) {
    Log.d(TAG, "navigateToDetail: $recipeId")
    this.navigate("${Screens.DetailScreen.route}?recipeId={$recipeId}")
}



fun NavGraphBuilder.detailNavGraph(
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
        DetailRoute(onBackClick = onBackClick, modifier = Modifier)
    }

}