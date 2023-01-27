package com.googleplaystore.spoonfed.presentation.detail_screen

import com.googleplaystore.spoonfed.domain.models.Recipe

data class DetailScreenState(
    val isLoading: Boolean = false,
    val hasError: Boolean= false,
    val errorMessage: String? = null,
    val recipe: Recipe = Recipe(),

)
