package com.googleplaystore.spoonfed.data.mapper

import com.googleplaystore.spoonfed.data.remote.responses.AnalyzedInstructionResponse
import com.googleplaystore.spoonfed.domain.models.AnalyzedInstruction

fun AnalyzedInstructionResponse.toDomainModel(): AnalyzedInstruction = AnalyzedInstruction(
    steps = steps?.map { it.toDomainModel() }
)