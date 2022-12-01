package com.googleplaystore.spoonfed.data.mapper

import com.googleplaystore.spoonfed.data.remote.responses.IngredientResponse
import com.googleplaystore.spoonfed.domain.models.Ingredient

fun IngredientResponse.toDomainModel(): Ingredient = Ingredient(
    id = id,
    image = image,
    localizedName = localizedName,
    name = name
)