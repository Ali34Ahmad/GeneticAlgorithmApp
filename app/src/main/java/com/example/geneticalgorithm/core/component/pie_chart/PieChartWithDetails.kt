package com.example.geneticalgorithm.core.component.pie_chart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.geneticalgorithm.core.models.PieChartDetailsItem
import com.example.geneticalgorithm.presentation.ui.helper.DarkAndLightModePreview
import com.example.geneticalgorithm.presentation.ui.helper.pieChartDetailsItems
import com.example.geneticalgorithm.presentation.ui.theme.GeneticAlgorithmTheme
import com.example.geneticalgorithm.presentation.ui.theme.sizing
import com.example.geneticalgorithm.presentation.ui.theme.spacing

@Composable
fun PieChartWithDetails(
    pieChartDetails: List<PieChartDetailsItem>,
    modifier: Modifier = Modifier,
) {
    val percentages = getPercentages(pieChartDetails).sortedDescending()

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        PieChartWithTheHighestFeatureText(
            pieChartDetails = pieChartDetails,
            modifier = Modifier.size(MaterialTheme.sizing.large146),
        )
        PieChartDetailsList(
            pieChartDetailsItems = pieChartDetails.sortedByDescending { it.numberOfSales }
                .mapIndexed { index, it ->
                    PieChartDetailsItem(
                        color = it.color,
                        name = it.name,
                        numberOfSales = percentages[index]
                    )
                },
            modifier = Modifier.padding(start = MaterialTheme.spacing.medium24),
        )
    }
}

@DarkAndLightModePreview
@Composable
fun PieChartWithDetailsPreview() {
    GeneticAlgorithmTheme {
        Surface {
            PieChartWithDetails(
                pieChartDetails = pieChartDetailsItems,
            )
        }
    }
}