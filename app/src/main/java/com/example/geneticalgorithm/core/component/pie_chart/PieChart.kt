package com.example.geneticalgorithm.core.component.pie_chart

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.geneticalgorithm.core.models.PieChartDetailsItem
import com.example.geneticalgorithm.presentation.ui.helper.DarkAndLightModePreview
import com.example.geneticalgorithm.presentation.ui.helper.pieChartDetailsItems
import com.example.geneticalgorithm.presentation.ui.theme.GeneticAlgorithmTheme
import com.example.geneticalgorithm.presentation.ui.theme.sizing

@Composable
fun PieChart(
    pieChartDetails: List<PieChartDetailsItem>,
    modifier: Modifier = Modifier,
    radiusOuter: Dp = MaterialTheme.sizing.large146,
    chartBarWidth: Dp = MaterialTheme.sizing.small16,
    animationDuration: Int = 1000,
) {
    val data= pieChartDetails.associate { it.name to it.numberOfSales }
    val totalSum = data.values.sum()
    val floatValue = mutableListOf<Float>()
    data.values.forEachIndexed { index, value ->
        floatValue.add(index, 360 * value.toFloat() / totalSum)
    }

    var animationPlayed by rememberSaveable {
        mutableStateOf(false)
    }

    var lastValue = 0f

    val animateRotation by animateFloatAsState(
        targetValue = if (animationPlayed) 360f else 0f,
        animationSpec = tween(
            durationMillis = animationDuration,
            delayMillis = 0,
            easing = LinearOutSlowInEasing
        ), label = ""
    )

    LaunchedEffect(true) {
        animationPlayed = true
    }

    Column(
        modifier = modifier.padding(chartBarWidth/2),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier,
            contentAlignment = Alignment.Center
        ) {
            Canvas(
                modifier = Modifier
                    .size(radiusOuter)
                    .rotate(animateRotation)
            ) {
                floatValue.forEachIndexed { index, value ->
                    drawArc(
                        color = pieChartDetails[index].color,
                        startAngle = lastValue,
                        sweepAngle = value,
                        useCenter = false,
                        style = Stroke(
                            width = chartBarWidth.toPx(),
                            cap = StrokeCap.Butt,
                        ),
                    )
                    lastValue+=value
                }
            }
        }
    }
}

@DarkAndLightModePreview
@Composable
fun PieChartPreview() {
    GeneticAlgorithmTheme {
        Surface {
            PieChart(
                pieChartDetails = pieChartDetailsItems,
                modifier = Modifier.padding(40.dp)
            )
        }
    }
}