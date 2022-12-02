package com.googleplaystore.spoonfed.data.remote.responses

data class ExtendedIngredientResponse(
    val aisle: String?,
    val amount: Double?,
    val consistency: String?,
    val id: Int?,
    val image: String,
    val measures: MeasuresResponse?,
    val meta: List<String>?,
    val name: String?,
    val nameClean: String?,
    val original: String?,
    val originalName: String?,
    val unit: String?
)