package com.example.geneticalgorithm.presentation.main.generation

import com.example.geneticalgorithm.algorithm.Individual

data class GenerationUiState(
    val generationNumber: Int=1,
    val generation: List<Individual> = emptyList(),
    )