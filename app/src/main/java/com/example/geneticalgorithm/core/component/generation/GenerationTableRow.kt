package com.example.geneticalgorithm.core.component.generation

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.geneticalgorithm.algorithm.House
import com.example.geneticalgorithm.algorithm.HouseFeature
import com.example.geneticalgorithm.algorithm.Individual
import com.example.geneticalgorithm.core.ext.toAppropriateFormat
import com.example.geneticalgorithm.presentation.ui.helper.DarkAndLightModePreview
import com.example.geneticalgorithm.presentation.ui.theme.GeneticAlgorithmTheme

@Composable
fun GenerationTableRow(
    individual: Individual,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier) {
        GenerationTableCell(
            text = individual.house.type.name.toAppropriateFormat(),
            modifier = Modifier.weight(1f),
        )
        GenerationTableCell(
            text = individual.house.location.name.toAppropriateFormat(),
            modifier = Modifier.weight(1f),
        )
        GenerationTableCell(
            text = individual.house.numberOfRooms.name.toAppropriateFormat(),
            modifier = Modifier.weight(1f),
        )
        GenerationTableCell(
            text = individual.fitness.toString(),
            modifier = Modifier.weight(1f)
        )

    }
}

@DarkAndLightModePreview
@Composable
fun IndividualItemPreview() {
    GeneticAlgorithmTheme {
        Surface {
            GenerationTableRow(
                individual = Individual(
                    house = House(
                        type = HouseFeature.HouseType.DELOX,
                        location = HouseFeature.Location.SUBURB,
                        numberOfRooms = HouseFeature.NumberOfRooms.R1,
                    )
                )
            )
        }
    }
}