package com.example.geneticalgorithm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.geneticalgorithm.core.constants.generationTableTitles
import com.example.geneticalgorithm.presentation.main.generation.GenerationSection
import com.example.geneticalgorithm.presentation.main.generation.GenerationUiState
import com.example.geneticalgorithm.presentation.main.statistics.StatisticsSection
import com.example.geneticalgorithm.presentation.main.statistics.StatisticsUiState
import com.example.geneticalgorithm.presentation.ui.helper.generation
import com.example.geneticalgorithm.presentation.ui.helper.pieChartDetailsItems
import com.example.geneticalgorithm.presentation.ui.theme.GeneticAlgorithmTheme
import com.example.geneticalgorithm.presentation.ui.theme.spacing

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var selectedOption by remember { mutableIntStateOf(0) }

            GeneticAlgorithmTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(
                        modifier = Modifier.padding(innerPadding),
                        tonalElevation = 1.dp
                    ) {
                        GenerationSection(
                            uiState = GenerationUiState(
                                generation = generation,
                            ),
                            generationTableTitles = generationTableTitles,
                            modifier = Modifier.padding(MaterialTheme.spacing.medium16)
                        )
                    }
                }
            }
        }
    }
}