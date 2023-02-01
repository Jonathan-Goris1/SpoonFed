package com.googleplaystore.spoonfed.data.mapper

import com.googleplaystore.spoonfed.data.remote.responses.ExtendedIngredientResponse
import com.googleplaystore.spoonfed.domain.models.ExtendedIngredient
import com.googleplaystore.spoonfed.util.DoubleConvertToFraction

fun ExtendedIngredientResponse.toDomainModel(): ExtendedIngredient = ExtendedIngredient(
    amount = DoubleConvertToFraction.convertToFraction(amount ?: 0.0),
    name = name,
    unit = unit
)