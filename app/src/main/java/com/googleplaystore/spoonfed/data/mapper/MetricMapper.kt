package com.googleplaystore.spoonfed.data.mapper

import com.googleplaystore.spoonfed.data.remote.responses.MetricResponse
import com.googleplaystore.spoonfed.domain.models.Metric

fun MetricResponse.toDomainModel(): Metric = Metric(
    amount = amount,
    unitLong = unitLong,
    unitShort = unitShort
)