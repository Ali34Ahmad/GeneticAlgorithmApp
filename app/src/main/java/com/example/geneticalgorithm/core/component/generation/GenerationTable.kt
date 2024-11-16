package com.example.geneticalgorithm.core.component.generation

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.geneticalgorithm.algorithm.Individual
import com.example.geneticalgorithm.core.constants.generationTableTitles
import com.example.geneticalgorithm.presentation.ui.helper.DarkAndLightModePreview
import com.example.geneticalgorithm.presentation.ui.helper.generation
import com.example.geneticalgorithm.presentation.ui.theme.GeneticAlgorithmTheme
import com.example.geneticalgorithm.presentation.ui.theme.tableTitleBackGroundDark
import com.example.geneticalgorithm.presentation.ui.theme.tableTitleBackGroundLight

@Composable
fun GenerationTable(
    generation: List<Individual>,
    titles: List<String>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        GenerationTableTitleItem(
            titles = titles,
            modifier = Modifier.background(
                color =
                if (!isSystemInDarkTheme()) tableTitleBackGroundLight
                else tableTitleBackGroundDark
            )
        )
        repeat(generation.size) {
            GenerationTableRow(
                individual = generation[it],
                index = it,
                lastIndex = generation.lastIndex
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun GenerationTablePreview() {
    GeneticAlgorithmTheme {
        Surface {
            GenerationTable(
                generation = generation,
                titles = generationTableTitles,
            )
        }
    }
}