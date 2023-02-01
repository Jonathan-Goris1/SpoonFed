package com.googleplaystore.spoonfed.domain.models

data class AnalyzedInstruction(
    val steps: List<Step>? = emptyList()
)