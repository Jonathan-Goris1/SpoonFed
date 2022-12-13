package com.googleplaystore.spoonfed.domain.models

data class Equipment(
    val id: Int? = 0,
    val image: String? = "",
    val localizedName: String? = "",
    val name: String? = "",
    val temperature: Temperature? = Temperature()
)