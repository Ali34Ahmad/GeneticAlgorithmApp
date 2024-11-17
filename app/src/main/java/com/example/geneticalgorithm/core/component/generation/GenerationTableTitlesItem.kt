package com.example.geneticalgorithm.core.component.generation

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.geneticalgorithm.core.constants.generationTableTitles
import com.example.geneticalgorithm.presentation.ui.helper.DarkAndLightModePreview
import com.example.geneticalgorithm.presentation.ui.theme.GeneticAlgorithmTheme

@Composable
fun GenerationTableTitleItem(
    titles: List<String>,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier) {
        titles.forEachIndexed { index, titles ->
            val weight = when {
                index == 0 -> 0.7f
                index == 1 -> 1f
                index == 2 -> 0.7f
                else -> 0.5f
            }
            GenerationTableCell(
                text = titles,
                textStyle = MaterialTheme.typography.labelLarge,
                modifier = Modifier.weight(weight),
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