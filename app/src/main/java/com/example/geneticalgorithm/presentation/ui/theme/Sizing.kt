package com.example.geneticalgorithm.presentation.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


data class Sizing(
    val default: Dp = 0.dp,
    val small11: Dp=1.dp,
    val small16: Dp = 16.dp,
    val medium40: Dp = 40.dp,
    val large146: Dp = 146.dp,
    val large170: Dp = 170.dp,
    val large238: Dp = 238.dp,
)
val LocalSizing = staticCompositionLocalOf { Sizing() }

val MaterialTheme.sizing
    @Composable
    @ReadOnlyComposable
    get()= LocalSizing.current