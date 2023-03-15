package com.googleplaystore.spoonfed.presentation.navigation

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.googleplaystore.spoonfed.presentation.detail_screen.DetailRoute


private const val TAG = "detailNavGraph"
fun NavController.navigateToDetail(recipeId: Int) {
    this.navigate("${Screens.DetailScreen.route}?recipeId={$recipeId}")
    Log.d(TAG, "navigateToDetail: $recipeId")
}


@RequiresApi(Build.VERSION_CODES.M)
fun NavGraphBuilder.detailNavGraph(
    onBackClick: () -> Unit,
) {
    composable(
        route = "${Screens.DetailScreen.route}?recipeId={recipeId}",
        arguments = listOf(
            navArgument("recipeId") {
                type = NavType.IntType
                defaultValue = 649977
            }
        )

    ) {
        DetailRoute(onBackClick = onBackClick, modifier = Modifier)
    }

}