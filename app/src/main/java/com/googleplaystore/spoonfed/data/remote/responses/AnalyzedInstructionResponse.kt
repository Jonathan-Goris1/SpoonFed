package com.googleplaystore.spoonfed.data.remote.responses

data class AnalyzedInstructionResponse(
    val name: String?,
    val steps: List<StepResponse>?
)