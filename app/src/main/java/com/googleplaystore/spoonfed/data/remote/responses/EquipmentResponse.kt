package com.googleplaystore.spoonfed.data.remote.responses

data class EquipmentResponse(
    val id: Int?,
    val image: String?,
    val localizedName: String?,
    val name: String?,
    val temperature: TemperatureResponse?
)