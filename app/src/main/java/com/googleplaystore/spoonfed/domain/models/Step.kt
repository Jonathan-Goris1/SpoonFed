package com.googleplaystore.spoonfed.domain.models

data class Step(
    val equipment: List<Equipment>? = emptyList(),
    val ingredients: List<Ingredient>? = emptyList(),
    val length: Length? = Length(),
    val number: Int? = 0,
    val step: String? = ""
)