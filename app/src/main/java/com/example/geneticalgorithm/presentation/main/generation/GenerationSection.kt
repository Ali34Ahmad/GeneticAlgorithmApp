package com.example.geneticalgorithm.presentation.main.generation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.geneticalgorithm.R
import com.example.geneticalgorithm.core.component.generation.GenerationHeader
import com.example.geneticalgorithm.core.component.generation.GenerationTable
import com.example.geneticalgorithm.core.constants.generationTableTitles
import com.example.geneticalgorithm.presentation.main.CircleProgressBar
import com.example.geneticalgorithm.presentation.main.NoDataAvailable
import com.example.geneticalgorithm.presentation.ui.helper.DarkAndLightModePreview
import com.example.geneticalgorithm.presentation.ui.helper.generationFake
import com.example.geneticalgorithm.presentation.ui.theme.GeneticAlgorithmTheme
import com.example.geneticalgorithm.presentation.ui.theme.sizing
import com.example.geneticalgorithm.presentation.ui.theme.spacing

@Composable
fun GenerationSection(
    uiState: GenerationUiState,
    generationTableTitles: List<String>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = R.string.generation),
            style = MaterialTheme.typography.titleMedium,
        )

        Card(
            modifier = Modifier.padding(top = MaterialTheme.spacing.small8),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
            )
        ) {
            if (uiState.isGenerationLoading) {
                CircleProgressBar(
                    size = MaterialTheme.sizing.large48,
                    textId = R.string.loading_generation_data,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(MaterialTheme.spacing.medium24)
                )
            } else if (uiState.generation.none { it.fitness != 0 }) {
                NoDataAvailable(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(MaterialTheme.spacing.medium24),
                    text = R.string.filter_blocking_all_results,
                )
            } else {
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
                        isWorkingOnNewGeneration=uiState.isWorkingOnNewGeneration,
                        targetFitness=uiState.targetFitness,
                    )
                    GenerationTable(
                        generation = uiState.generation.filter { it.fitness != 0 }
                            .sortedByDescending { it.fitness },
                        titles = generationTableTitles,
                    )
                }
            }
        }
    }
}

@DarkAndLightModePreview
@Composable
fun GenerationSectionPreview() {
    GeneticAlgorithmTheme {
        Surface(tonalElevation = 1.dp) {
            GenerationSection(
                uiState = GenerationUiState(
                    generation = generationFake,
                    generationNumber = 1,
                    isGenerationLoading = false,
                    isWorkingOnNewGeneration = true,
                    targetFitness = 157,
                ),
                generationTableTitles = generationTableTitles,
                modifier = Modifier.padding(MaterialTheme.spacing.medium16)
            )
        }
    }
}