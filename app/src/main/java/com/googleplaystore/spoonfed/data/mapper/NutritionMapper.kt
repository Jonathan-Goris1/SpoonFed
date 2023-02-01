package com.googleplaystore.spoonfed.data.mapper

import com.googleplaystore.spoonfed.data.remote.responses.NutrientXResponse
import com.googleplaystore.spoonfed.data.remote.responses.NutritionResponse
import com.googleplaystore.spoonfed.domain.models.NutrientX
import com.googleplaystore.spoonfed.domain.models.Nutrition

fun NutritionResponse.toDomainModel(): Nutrition = Nutrition(
    nutrients = nutrients?.map { it.toDomainModel() }
)

fun NutrientXResponse.toDomainModel(): NutrientX = NutrientX(
    amount = amount,
    name = name,
    unit = unit
)