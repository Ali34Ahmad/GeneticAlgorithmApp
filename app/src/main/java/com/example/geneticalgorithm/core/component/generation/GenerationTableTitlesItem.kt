package com.example.geneticalgorithm.core.component.generation

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.geneticalgorithm.algorithm.House
import com.example.geneticalgorithm.algorithm.HouseFeature
import com.example.geneticalgorithm.algorithm.Individual
import com.example.geneticalgorithm.core.constants.generationTableTitles
import com.example.geneticalgorithm.core.ext.toAppropriateFormat
import com.example.geneticalgorithm.presentation.ui.helper.DarkAndLightModePreview
import com.example.geneticalgorithm.presentation.ui.theme.GeneticAlgorithmTheme

@Composable
fun GenerationTableTitleItem(
    titles: List<String>,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier) {
        titles.forEach{
            GenerationTableCell(
                text = it,
                textStyle = MaterialTheme.typography.labelLarge,
                modifier = Modifier.weight(1f),
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun GenerationTableTitleItemPreview() {
    GeneticAlgorithmTheme {
        Surface {
            GenerationTableTitleItem(
                titles = generationTableTitles,
            )
        }
    }
}