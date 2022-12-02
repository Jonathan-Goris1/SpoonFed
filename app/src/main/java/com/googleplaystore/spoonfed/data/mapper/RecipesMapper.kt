package com.googleplaystore.spoonfed.data.mapper

import com.googleplaystore.spoonfed.data.remote.responses.RecipesResponse
import com.googleplaystore.spoonfed.domain.models.Recipes

fun RecipesResponse.toDomainModel(): Recipes = Recipes(
    recipes = recipes?.map { it.toDomainModel() }
)