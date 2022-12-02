package com.googleplaystore.spoonfed.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.googleplaystore.spoonfed.presentation.home_screen.HomeScreen

fun NavGraphBuilder.homeNavGraph(
    navController: NavHostController
){
    navigation(
        startDestination = Screens.Home.route,
        route = HOME_ROUTE
    ){
        composable(Screens.Home.route) {
           HomeScreen()
        }
        composable(Screens.DetailScreen.route) {

        }

    }

}