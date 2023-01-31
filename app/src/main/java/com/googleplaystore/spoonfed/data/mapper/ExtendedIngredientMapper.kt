package com.googleplaystore.spoonfed.data.mapper

import com.googleplaystore.spoonfed.data.remote.responses.ExtendedIngredientResponse
import com.googleplaystore.spoonfed.domain.models.ExtendedIngredient
import com.googleplaystore.spoonfed.util.DoubleConvertToFraction

fun ExtendedIngredientResponse.toDomainModel(): ExtendedIngredient = ExtendedIngredient(
    aisle = aisle,
    amount = DoubleConvertToFraction.convertToFraction(amount ?: 0.0),
    consistency = consistency,
    id = id,
    image = image,
    measures = measures?.toDomainModel(),
    meta = meta,
    name = name,
    nameClean = nameClean,
    original = original,
    originalName = originalName,
    unit = unit
)