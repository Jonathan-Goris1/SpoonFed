package com.googleplaystore.spoonfed.data.mapper

import com.googleplaystore.spoonfed.data.remote.responses.EquipmentResponse
import com.googleplaystore.spoonfed.domain.models.Equipment

fun EquipmentResponse.toDomainModel(): Equipment = Equipment(
    id = id,
    image = image,
    localizedName = localizedName,
     name = name,
    temperature = temperature?.toDomainModel()
)