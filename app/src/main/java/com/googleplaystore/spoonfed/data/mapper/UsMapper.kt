package com.googleplaystore.spoonfed.data.mapper

import com.googleplaystore.spoonfed.data.remote.responses.UsResponse
import com.googleplaystore.spoonfed.domain.models.Us

fun UsResponse.toDomainModel(): Us = Us(
    amount = amount,
    unitLong = unitLong,
    unitShort = unitShort
)