package com.example.geneticalgorithm.presentation.main.generation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.geneticalgorithm.algorithm.Individual
import com.example.geneticalgorithm.core.component.generation.GenerationHeader
import com.example.geneticalgorithm.core.component.generation.GenerationTable
import com.example.geneticalgorithm.core.constants.generationTableTitles
import com.example.geneticalgorithm.presentation.ui.helper.DarkAndLightModePreview
import com.example.geneticalgorithm.presentation.ui.helper.generation
import com.example.geneticalgorithm.presentation.ui.theme.GeneticAlgorithmTheme
import com.example.geneticalgorithm.presentation.ui.theme.spacing

@Composable
fun GenerationSection(
    uiState:GenerationUiState,
    generationTableTitles:List<String>,
    modifier: Modifier = Modifier,
    ) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
        )
    ) {
        Column {
            GenerationHeader(
                generationNumber = uiState.generationNumber,
                bestFitness = uiState.generation.maxBy { it.fitness }.fitness,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = MaterialTheme.spacing.small12,
                        vertical = MaterialTheme.spacing.small8,
                    ),
            )
            GenerationTable(
                generation = uiState.generation.filter { it.fitness!=0 }.sortedByDescending { it.fitness },
                titles = generationTableTitles,
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun GenerationSectionPreview(){
    GeneticAlgorithmTheme{
        Surface(tonalElevation = 1.dp){
            GenerationSection(
                uiState = GenerationUiState(
                    generation = generation,
                ),
                generationTableTitles = generationTableTitles,
                modifier = Modifier.padding(MaterialTheme.spacing.medium16)
            )
        }
    }
}