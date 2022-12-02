package com.googleplaystore.spoonfed.data.mapper

import com.googleplaystore.spoonfed.data.remote.responses.MeasuresResponse
import com.googleplaystore.spoonfed.domain.models.Measures

fun MeasuresResponse.toDomainModel(): Measures = Measures(
    metric = metric?.toDomainModel(),
    us = us?.toDomainModel()
)