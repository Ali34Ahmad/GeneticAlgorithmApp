package com.example.geneticalgorithm.presentation.main.statistics

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.geneticalgorithm.core.component.CustomSegmentedButton
import com.example.geneticalgorithm.core.component.pie_chart.PieChartWithDetails
import com.example.geneticalgorithm.core.constants.SegmentedButtonOptions
import com.example.geneticalgorithm.presentation.ui.helper.DarkAndLightModePreview
import com.example.geneticalgorithm.presentation.ui.helper.pieChartDetailsItems
import com.example.geneticalgorithm.presentation.ui.theme.GeneticAlgorithmTheme
import com.example.geneticalgorithm.presentation.ui.theme.spacing

@Composable
fun StatisticsSection(modifier: Modifier = Modifier) {
    Card(
        modifier=modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
        )
    ) {
        Column {
            CustomSegmentedButton(
                options = SegmentedButtonOptions.statisticsOption,
                selectedOption = 0,
                onOptionClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.medium16,
                        vertical = MaterialTheme.spacing.medium24
                        ),
            )
            PieChartWithDetails(
                pieChartDetails = pieChartDetailsItems,
                modifier = Modifier.fillMaxWidth()
                    .padding(
                        start = MaterialTheme.spacing.medium16,
                        end = MaterialTheme.spacing.medium16,
                        bottom=MaterialTheme.spacing.medium24),
            )
        }
    }
}

@DarkAndLightModePreview
@Composable
fun StatisticsSectionPreview() {
    GeneticAlgorithmTheme {
        Surface(tonalElevation = 1.dp) {
            StatisticsSection(modifier = Modifier.padding(MaterialTheme.spacing.medium16))
        }
    }
}