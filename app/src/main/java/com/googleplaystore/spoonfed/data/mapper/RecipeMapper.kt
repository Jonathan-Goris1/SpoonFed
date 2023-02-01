package com.googleplaystore.spoonfed.data.mapper

import com.googleplaystore.spoonfed.data.remote.responses.RecipeResponse
import com.googleplaystore.spoonfed.domain.models.Recipe

fun RecipeResponse.toDomainModel(): Recipe = Recipe(
    analyzedInstructions = analyzedInstructions?.map { it.toDomainModel() },
    extendedIngredients = extendedIngredients?.map { it.toDomainModel() },
    id = id,
    image = image,
    instructions = instructions,
    servings = servings,
    title = title,
    nutrition = nutrition?.toDomainModel()
)