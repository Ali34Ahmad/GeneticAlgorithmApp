package com.example.geneticalgorithm.core.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.geneticalgorithm.core.models.PieChartDetailsItem
import com.example.geneticalgorithm.presentation.ui.helper.DarkAndLightModePreview
import com.example.geneticalgorithm.presentation.ui.helper.pieChartDetailsItem
import com.example.geneticalgorithm.presentation.ui.theme.GeneticAlgorithmTheme
import com.example.geneticalgorithm.presentation.ui.theme.sizing
import com.example.geneticalgorithm.presentation.ui.theme.spacing

@Composable
fun PieChartDetailsItem(
    pieChartDetailsItem: PieChartDetailsItem,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
    ) {
        Spacer(
            modifier = Modifier
                .size(MaterialTheme.sizing.small16)
                .background(pieChartDetailsItem.color, CircleShape)
        )
        Text(text = pieChartDetailsItem.name)
        Text(text = "${pieChartDetailsItem.percentage}%")
    }
}

@DarkAndLightModePreview
@Composable
fun PieChartDetailsItemPreview() {
    GeneticAlgorithmTheme {
        Surface {
            PieChartDetailsItem(
                pieChartDetailsItem = pieChartDetailsItem[0]
            )
        }
    }
}