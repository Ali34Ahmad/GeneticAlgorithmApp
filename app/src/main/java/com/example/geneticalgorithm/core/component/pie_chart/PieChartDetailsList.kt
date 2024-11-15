package com.example.geneticalgorithm.core.component.pie_chart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.geneticalgorithm.core.models.PieChartDetailsItem
import com.example.geneticalgorithm.presentation.ui.helper.DarkAndLightModePreview
import com.example.geneticalgorithm.presentation.ui.helper.pieChartDetailsItems
import com.example.geneticalgorithm.presentation.ui.theme.GeneticAlgorithmTheme
import com.example.geneticalgorithm.presentation.ui.theme.spacing

@Composable
fun PieChartDetailsList(
    pieChartDetailsItems: List<PieChartDetailsItem>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium16),
    ) {
        pieChartDetailsItems.forEach {
            PieChartDetailsItem(pieChartDetailsItem = it)
        }
    }
}

@DarkAndLightModePreview
@Composable
fun PieChartDetailsListPreview() {
    GeneticAlgorithmTheme {
        Surface {
            PieChartDetailsList(pieChartDetailsItems = pieChartDetailsItems)
        }
    }
}