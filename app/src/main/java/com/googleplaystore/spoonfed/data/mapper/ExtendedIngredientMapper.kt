package com.googleplaystore.spoonfed.data.mapper

import com.googleplaystore.spoonfed.data.remote.responses.ExtendedIngredientResponse
import com.googleplaystore.spoonfed.domain.models.ExtendedIngredient

fun ExtendedIngredientResponse.toDomainModel(): ExtendedIngredient = ExtendedIngredient(
    aisle = aisle,
    amount = amount,
    consistency = consistency,
    id = id,
    image = image,
    measures = measures.toDomainModel(),
    meta = meta,
    name = name,
    nameClean = nameClean,
    original = original,
    originalName = originalName,
    unit = unit
)