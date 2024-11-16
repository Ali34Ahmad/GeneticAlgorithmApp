package com.example.geneticalgorithm.core.component.pie_chart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import com.example.geneticalgorithm.core.models.PieChartDetailsItem
import com.example.geneticalgorithm.presentation.ui.helper.DarkAndLightModePreview
import com.example.geneticalgorithm.presentation.ui.helper.pieChartDetailsItemsFake
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
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    ) {
        Spacer(
            modifier = Modifier
                .size(MaterialTheme.sizing.small16)
                .background(pieChartDetailsItem.color, CircleShape)
        )
        Text(
            text = pieChartDetailsItem.name,
            modifier = Modifier
                .padding(start = MaterialTheme.spacing.small12)
                .weight(1f),
            style = MaterialTheme.typography.labelMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Text(
            text = "${pieChartDetailsItem.numberOfSales}%",
            style = MaterialTheme.typography.labelMedium,
            fontFamily = FontFamily.Monospace,
        )
    }
}

@DarkAndLightModePreview
@Composable
fun PieChartDetailsItemPreview() {
    GeneticAlgorithmTheme {
        Surface {
            PieChartDetailsItem(
                pieChartDetailsItem = pieChartDetailsItemsFake[0]
            )
        }
    }
}