package com.googleplaystore.spoonfed.domain.models


data class Recipe(
    val aggregateLikes: Int? = 0,
    val analyzedInstructions: List<AnalyzedInstruction>? = emptyList(),
    val cheap: Boolean? = false,
    val cookingMinutes: Int? = 0,
    val creditsText: String? = "",
    val cuisines: List<String>? = emptyList(),
    val dairyFree: Boolean? = false,
    val diets: List<String>? = emptyList(),
    val dishTypes: List<String>? = emptyList(),
    val extendedIngredients: List<ExtendedIngredient>? = emptyList(),
    val gaps: String? = "",
    val glutenFree: Boolean? = false,
    val healthScore: Int? = 0,
    val id: Int? = 0,
    val nutrition: Nutrition? = Nutrition(),
    val image: String? = "",
    val imageType: String? = "",
    val instructions: String? = "",
    val license: String? = "",
    val lowFodmap: Boolean? = false,
    val occasions: List<String>? = emptyList(),
    val originalId: Any? = Any(),
    val preparationMinutes: Int? = 0,
    val pricePerServing: Double? = 0.0,
    val readyInMinutes: Int? = 0,
    val servings: Int? = 0,
    val sourceName: String? = "",
    val sourceUrl: String? = "",
    val spoonacularSourceUrl: String? = "",
    val summary: String? = "",
    val sustainable: Boolean? = false,
    val title: String? = "",
    val vegan: Boolean? = false,
    val vegetarian: Boolean? = false,
    val veryHealthy: Boolean? = false,
    val veryPopular: Boolean? = false,
    val weightWatcherSmartPoints: Int? = 0
)