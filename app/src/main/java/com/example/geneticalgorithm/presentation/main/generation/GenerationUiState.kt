package com.example.geneticalgorithm.presentation.main.generation

import com.example.geneticalgorithm.core.models.Individual
import com.example.geneticalgorithm.presentation.main.MainUiState

data class GenerationUiState(
    val generationNumber: Int,
    val generation: List<Individual>,
    )
fun MainUiState.toGenerationUiState()= GenerationUiState(
    generation = this.generation,
    generationNumber = this.generationNumber,
)