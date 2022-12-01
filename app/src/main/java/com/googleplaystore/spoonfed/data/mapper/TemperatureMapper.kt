package com.googleplaystore.spoonfed.data.mapper

import com.googleplaystore.spoonfed.data.remote.responses.TemperatureResponse
import com.googleplaystore.spoonfed.domain.models.Temperature

fun TemperatureResponse.toDomainModel(): Temperature = Temperature(
    number = number,
    unit = unit
)