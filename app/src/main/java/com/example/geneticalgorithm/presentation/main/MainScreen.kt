package com.example.geneticalgorithm.presentation.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.geneticalgorithm.core.component.BottomBar
import com.example.geneticalgorithm.core.constants.generationTableTitles
import com.example.geneticalgorithm.presentation.main.generation.GenerationSection
import com.example.geneticalgorithm.presentation.main.generation.toGenerationUiState
import com.example.geneticalgorithm.presentation.main.statistics.StatisticsSection
import com.example.geneticalgorithm.presentation.main.statistics.toStatisticsUiState
import com.example.geneticalgorithm.presentation.ui.theme.spacing

@Composable
fun MainScreen(
    uiState: MainUiState,
    onSegmentedButtonOptionClick: (index: Int) -> Unit,
    onAdvancedSearchClick: () -> Unit,
    onRunAlgorithmClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()
    Scaffold(
        bottomBar = {
            BottomBar(
                onAdvancedSearchClick = onAdvancedSearchClick,
                onRunAlgorithmClick = onRunAlgorithmClick
            )
        }
    ) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {

                StatisticsSection(
                    uiState = uiState.toStatisticsUiState(),
                    onSegmentedButtonOptionClick = onSegmentedButtonOptionClick,
                    modifier = Modifier.padding(MaterialTheme.spacing.medium16),
                )

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium24))

                GenerationSection(
                    uiState = uiState.toGenerationUiState(),
                    generationTableTitles = generationTableTitles,
                    modifier = Modifier.padding(MaterialTheme.spacing.medium16)
                )
            }
        }
    }
}