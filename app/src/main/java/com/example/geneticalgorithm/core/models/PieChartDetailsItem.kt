package com.example.geneticalgorithm.core.models

import android.health.connect.datatypes.units.Percentage
import androidx.compose.ui.graphics.Color

data class PieChartDetailsItem(
    val color: Color,
    val name:String,
    val percentage:Int,
)
