package com.example.geneticalgorithm.core.component.generation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.example.geneticalgorithm.core.models.House
import com.example.geneticalgorithm.core.models.HouseFeature
import com.example.geneticalgorithm.core.models.Individual
import com.example.geneticalgorithm.core.ext.toAppropriateFormat
import com.example.geneticalgorithm.presentation.ui.helper.DarkAndLightModePreview
import com.example.geneticalgorithm.presentation.ui.theme.GeneticAlgorithmTheme

@Composable
fun GenerationTableRow(
    individual: Individual,
    index: Int,
    lastIndex: Int,
    modifier: Modifier = Modifier,
) {
    val startCellBorderShape = if (index == lastIndex)
        RoundedCornerShape(
            topStart = 0.dp,
            topEnd = 0.dp,
            bottomEnd = 0.dp,
            bottomStart = 12.dp
        )
    else
        RectangleShape

    val endCellBorderShape = if (index == lastIndex)
        RoundedCornerShape(
            topStart = 0.dp,
            topEnd = 0.dp,
            bottomEnd = 12.dp,
            bottomStart = 0.dp
        )
    else
        RectangleShape

    Row(modifier = modifier) {
        GenerationTableCell(
            text = individual.house.type.name.toAppropriateFormat(),
            modifier = Modifier.weight(0.7f),
            borderShape = startCellBorderShape,
        )
        GenerationTableCell(
            text = individual.house.location.toAppropriateFormat(),
            modifier = Modifier.weight(1f),
        )
        GenerationTableCell(
            text = individual.house.numberOfRooms.toAppropriateFormat(),
            modifier = Modifier.weight(0.7f),
        )
        GenerationTableCell(
            text = individual.fitness.toString(),
            modifier = Modifier.weight(0.5f),
            borderShape = endCellBorderShape,
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
                    ),
                ),
                index = 0,
                lastIndex = 10
            )
        }
    }
}