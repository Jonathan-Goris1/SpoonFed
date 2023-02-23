package com.googleplaystore.spoonfed.presentation.navigation

const val HOME_ROUTE = "home_route"


sealed class Screens(val route: String) {

    object Home : Screens("home")
    object DetailScreen : Screens("detail_screen")




}

