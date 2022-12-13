package com.googleplaystore.spoonfed.presentation.detail_screen

import com.googleplaystore.spoonfed.domain.models.Recipe

data class DetailScreenState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val recipe: Recipe = Recipe(),
    val searchQuery: String = ""
)
