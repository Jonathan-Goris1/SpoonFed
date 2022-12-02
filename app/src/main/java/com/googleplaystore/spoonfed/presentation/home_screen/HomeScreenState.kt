package com.googleplaystore.spoonfed.presentation.home_screen

import com.googleplaystore.spoonfed.domain.models.Recipe

data class HomeScreenState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val recipes: List<Recipe>? = emptyList(),
    val searchQuery: String = ""
)
