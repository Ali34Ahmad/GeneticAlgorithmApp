package com.example.geneticalgorithm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.geneticalgorithm.presentation.main.MainScreen
import com.example.geneticalgorithm.presentation.main.MainUiState
import com.example.geneticalgorithm.presentation.main.MainViewModel
import com.example.geneticalgorithm.presentation.ui.theme.GeneticAlgorithmTheme
import kotlinx.coroutines.flow.StateFlow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()

        val viewModel: MainViewModel = MainViewModel()

        setContent {
            GeneticAlgorithmTheme {
                val uiState= viewModel.uiState.collectAsStateWithLifecycle()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(
                        modifier = Modifier.padding(innerPadding),
                        tonalElevation = 1.dp
                    ) {
                        MainScreen(
                            uiState = uiState.value,
                            onSegmentedButtonOptionClick = viewModel::onSegmentedButtonOptionClick,
                            onAdvancedSearchClick = viewModel::showAdvancedSearchDialog,
                            onRunAlgorithmClick = viewModel::runAlgorithm,
                        )
                    }
                }
            }
        }
    }
}