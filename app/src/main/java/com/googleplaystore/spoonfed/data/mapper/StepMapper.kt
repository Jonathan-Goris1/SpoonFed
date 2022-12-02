package com.googleplaystore.spoonfed.data.mapper

import com.googleplaystore.spoonfed.data.remote.responses.StepResponse
import com.googleplaystore.spoonfed.domain.models.Step

fun StepResponse.toDomainModel(): Step = Step(
    equipment = equipment?.map { it.toDomainModel() },
    ingredients = ingredients?.map { it.toDomainModel() },
    length = length?.toDomainModel(),
    number = number,
    step = step

)