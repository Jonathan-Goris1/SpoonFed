package com.googleplaystore.spoonfed.data.mapper

import com.googleplaystore.spoonfed.data.remote.responses.LengthResponse
import com.googleplaystore.spoonfed.domain.models.Length

fun LengthResponse.toDomainModel(): Length = Length(
    number = number,
    unit = unit
)