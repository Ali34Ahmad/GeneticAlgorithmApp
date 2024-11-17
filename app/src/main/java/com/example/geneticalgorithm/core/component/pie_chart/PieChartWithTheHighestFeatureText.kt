package com.example.geneticalgorithm.core.component.pie_chart

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.geneticalgorithm.core.models.PieChartDetailsItem
import com.example.geneticalgorithm.presentation.ui.helper.DarkAndLightModePreview
import com.example.geneticalgorithm.presentation.ui.helper.pieChartDetailsItemsFake
import com.example.geneticalgorithm.presentation.ui.theme.GeneticAlgorithmTheme

@Composable
fun PieChartWithTheHighestFeatureText(
    pieChartDetails: List<PieChartDetailsItem>,
    modifier: Modifier = Modifier,
) {
    val percentages = getPercentages(pieChartDetails)
    val maxPercentage=percentages.maxBy { it }

    val mostPopularFeature = pieChartDetails.maxBy { it.numberOfSales }

    Box(modifier = modifier) {
        PieChart(
            pieChartDetails = pieChartDetails,
            modifier = modifier,
        )
        Column(
            modifier = Modifier
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = mostPopularFeature.name,
                modifier = Modifier
                    .padding(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelLarge,
            )
            Text(
                text = "${maxPercentage}%",
                modifier = Modifier
                    .padding(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelLarge,
                fontFamily = FontFamily.Monospace,
            )
        }
    }
}

fun getPercentages(
    pieChartDetails: List<PieChartDetailsItem>,
): List<Int> {
    val data = pieChartDetails.associate { it.name to it.numberOfSales }
    val totalSum = data.values.sum()
    val percentages = mutableListOf<Int>()
    data.forEach { percentages.add(it.value * 100 / totalSum) }
    return percentages
}

@DarkAndLightModePreview
@Composable
fun PieChartWithTheHighestFeatureTextPreview() {
    GeneticAlgorithmTheme {
        Surface {
            PieChartWithTheHighestFeatureText(
                pieChartDetails = pieChartDetailsItemsFake,
                modifier = Modifier.padding(40.dp)
            )
        }
    }
}