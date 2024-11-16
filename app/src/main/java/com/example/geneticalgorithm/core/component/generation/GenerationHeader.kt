package com.example.geneticalgorithm.core.component.generation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.example.geneticalgorithm.core.ext.toOrdinal
import com.example.geneticalgorithm.presentation.ui.helper.DarkAndLightModePreview
import com.example.geneticalgorithm.presentation.ui.theme.GeneticAlgorithmTheme
import com.example.geneticalgorithm.presentation.ui.theme.primaryTextColorDark
import com.example.geneticalgorithm.presentation.ui.theme.primaryTextColorLight

@Composable
fun GenerationHeader(
    generationNumber: Int,
    bestFitness: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = generationNumber.toOrdinal(),
            style = MaterialTheme.typography.labelMedium,
            color = if (!isSystemInDarkTheme())
                primaryTextColorLight
            else
                primaryTextColorDark,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
        )
        Text(
            text = "Best Fitness: $bestFitness",
            style = MaterialTheme.typography.labelMedium,
            color = if (!isSystemInDarkTheme())
                primaryTextColorLight
            else
                primaryTextColorDark,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
        )

    }
}

@DarkAndLightModePreview
@Composable
fun GenerationHeaderPreview() {
    GeneticAlgorithmTheme {
        Surface {
            GenerationHeader(
                generationNumber = 1,
                bestFitness = 120,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}