package com.example.geneticalgorithm.presentation.main.statistics

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.geneticalgorithm.presentation.ui.helper.DarkAndLightModePreview
import com.example.geneticalgorithm.presentation.ui.theme.GeneticAlgorithmTheme

@Composable
fun StatisticsSection(modifier: Modifier = Modifier) {

}

@DarkAndLightModePreview
@Composable
fun StatisticsSectionPreview(){
    GeneticAlgorithmTheme{
        Surface{
            StatisticsSection()
        }
    }
}