package com.example.geneticalgorithm.core.component

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.geneticalgorithm.presentation.ui.helper.DarkAndLightModePreview
import com.example.geneticalgorithm.presentation.ui.theme.GeneticAlgorithmTheme

@Composable
fun PieChart(
    data: Map<String, Int>,
    modifier: Modifier = Modifier,
    radiusOuter: Dp = 90.dp,
    chartBarWidth: Dp = 20.dp,
    animationDuration: Int = 1000,
) {
    val totalSum = data.values.sum()
    val floatValue = mutableListOf<Float>()
    data.values.forEachIndexed { index, value ->
        floatValue.add(index, 360 * value.toFloat() / totalSum)
    }

    val colors = listOf(
        Color.Red,
        Color.Blue,
        Color.Yellow,
        Color.Green,
        Color.Magenta,
        Color.Cyan,
        Color.Gray,
    )

    var animationPlayed by rememberSaveable {
        mutableStateOf(false)
    }

    var lastValue = 0f

    val animatedSize by animateFloatAsState(
        targetValue = if (animationPlayed) radiusOuter.value * 2f else 0f,
        animationSpec = tween(
            durationMillis = animationDuration,
            delayMillis = 0,
            easing = LinearOutSlowInEasing
        ), label = ""
    )

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
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier.size(animatedSize.dp),
            contentAlignment = Alignment.Center
        ) {
            Canvas(
                modifier = Modifier
                    .size(radiusOuter * 2f)
                    .rotate(animateRotation)
            ) {
                floatValue.forEachIndexed { index, value ->
                    drawArc(
                        color = colors[index],
                        startAngle = lastValue,
                        sweepAngle = value,
                        useCenter = false,
                        style = Stroke(
                            width = chartBarWidth.toPx(),
                            cap = StrokeCap.Round,
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
                data = mapOf(
                    "A" to 10,
                    "B" to 20,
                    "C" to 30,
                    "D" to 40,
                    "E" to 50,
                ),
                modifier = Modifier.padding(40.dp)
            )
        }
    }
}