package com.googleplaystore.spoonfed.domain.models

data class NutrientX(
    val amount: Double? = 0.0,
    val name: String? = "",
    val percentOfDailyNeeds: Double? = 0.0,
    val unit: String? = ""
)
