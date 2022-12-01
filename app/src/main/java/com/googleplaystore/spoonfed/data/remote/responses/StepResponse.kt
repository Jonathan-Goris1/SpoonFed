package com.googleplaystore.spoonfed.data.remote.responses

data class StepResponse(
    val equipment: List<EquipmentResponse>,
    val ingredients: List<IngredientResponse>,
    val length: LengthResponse,
    val number: Int,
    val step: String
)