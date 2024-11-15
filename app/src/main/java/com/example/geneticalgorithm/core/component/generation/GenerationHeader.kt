package com.example.geneticalgorithm.core.component.generation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.geneticalgorithm.presentation.ui.helper.DarkAndLightModePreview
import com.example.geneticalgorithm.presentation.ui.theme.GeneticAlgorithmTheme

@Composable
fun GenerationHeader(
    generationNumber: Int,
    bestFitness: Int,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween) {
        Text(
            text = generationNumber.toString(),
            style = MaterialTheme.typography.labelMedium,
        )
        Text(
            text = bestFitness.toString(),
            style = MaterialTheme.typography.labelMedium,
        )

    }
}

@DarkAndLightModePreview
@Composable
fun GenerationHeaderPreview(){
    GeneticAlgorithmTheme{
        Surface{
            GenerationHeader(
                generationNumber = 1,
                bestFitness = 120,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}