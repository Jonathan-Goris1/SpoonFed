package com.googleplaystore.spoonfed.presentation.home_screen

import com.googleplaystore.spoonfed.domain.models.Recipe

data class HomeScreenState(
    val isLoading: Boolean = true,
    val hasError: Boolean= false,
    val errorMessage: String? = null,
    val recipes: List<Recipe>? = emptyList(),
    val searchQuery: String = ""
)
