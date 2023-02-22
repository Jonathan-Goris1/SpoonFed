package com.googleplaystore.spoonfed.domain.models


data class Recipe(
    val analyzedInstructions: List<AnalyzedInstruction>? = emptyList(),
    val extendedIngredients: List<ExtendedIngredient>? = emptyList(),
    val id: Int? = 0,
    val nutrition: Nutrition? = Nutrition(),
    val image: String? = "",
    val instructions: String? = "",
    val servings: Int? = 0,
    val title: String? = "",

    )