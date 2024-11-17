package com.example.geneticalgorithm.core.component.generation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.geneticalgorithm.core.ext.toOrdinal
import com.example.geneticalgorithm.presentation.main.CircleProgressBar
import com.example.geneticalgorithm.presentation.ui.helper.DarkAndLightModePreview
import com.example.geneticalgorithm.presentation.ui.theme.GeneticAlgorithmTheme
import com.example.geneticalgorithm.presentation.ui.theme.primaryTextColorDark
import com.example.geneticalgorithm.presentation.ui.theme.primaryTextColorLight
import com.example.geneticalgorithm.presentation.ui.theme.sizing
import com.example.geneticalgorithm.presentation.ui.theme.spacing

@Composable
fun GenerationHeader(
    generationNumber: Int,
    bestFitness: Int,
    isWorkingOnNewGeneration: Boolean,
    targetFitness: Int,
    modifier: Modifier = Modifier,
) {
    val color=if (!isSystemInDarkTheme())
        primaryTextColorLight
    else
        primaryTextColorDark
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium16),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "${generationNumber.toOrdinal()} generation",
            style = MaterialTheme.typography.labelMedium,
            color = color,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
        )
        if (isWorkingOnNewGeneration) {
            CircleProgressBar(
                size = MaterialTheme.sizing.small16,
                textId = null,
                strokeWidth = MaterialTheme.sizing.small2,
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "Fitness: $bestFitness/$targetFitness",
            style = MaterialTheme.typography.labelMedium,
            color = color,
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
                modifier = Modifier.fillMaxWidth(),
                isWorkingOnNewGeneration = true,
                targetFitness = 157,
            )
        }
    }
}