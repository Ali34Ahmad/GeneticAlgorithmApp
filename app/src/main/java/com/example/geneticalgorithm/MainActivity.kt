package com.example.geneticalgorithm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.geneticalgorithm.presentation.main.MainScreen
import com.example.geneticalgorithm.presentation.main.MainViewModel
import com.example.geneticalgorithm.presentation.ui.theme.GeneticAlgorithmTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()

        val viewModel = MainViewModel()

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
                            onAdvancedSearchClick = { viewModel.updateAlgorithmFiltersDialogVisibility(true) },
                            onRunAlgorithmClick = viewModel::runAlgorithm,
                            onAlgorithmFiltersSelectAllLocationCheckChange = viewModel::updateSelectAllLocationAlgorithmFilters,
                            onAlgorithmFiltersSelectAllTypeCheckChange = viewModel::updateSelectAllTypeAlgorithmFilters,
                            onAlgorithmFiltersSelectAllRoomsCheckChange = viewModel::updateSelectAllRoomsAlgorithmFilters,
                            onAlgorithmFiltersCancelButtonClick = viewModel::cancelAlgorithmFiltersDialog,
                            onAlgorithmFiltersConfirmButtonClick = viewModel::algorithmFiltersConfirmButtonClick,
                            onAlgorithmFiltersDialogSegmentedButtonOptionSelected=viewModel::updateAlgorithmFiltersDialogSegmentedButtonOptionSelectedIndex,
                            onAlgorithmFiltersLocationItemCheckChange = viewModel::updateAlgorithmFiltersLocationItemCheck,
                            onAlgorithmFiltersTypeItemCheckChange = viewModel::updateAlgorithmFiltersTypeItemCheck,
                            onAlgorithmFiltersRoomsItemCheckChange = viewModel::updateAlgorithmFiltersRoomsItemCheck,
                            onBuyHouseButtonClick = viewModel::buyHouse,
                            onCustomizeSearchFilterButtonClick = { viewModel.updateSearchFiltersDialogVisibility(true) },
                            onClearSearchFilterButtonClick = viewModel::clearSearchFilters,
                            onSearchFiltersSelectAllLocationCheckChange=viewModel::updateSelectAllLocationSearchFilters,
                            onSearchFiltersSelectAllTypeCheckChange=viewModel::updateSelectAllTypeSearchFilters,
                            onSearchFiltersSelectAllRoomsCheckChange=viewModel::updateSelectAllRoomsSearchFilters,
                            onSearchFiltersCancelButtonClick= viewModel::cancelSearchFiltersDialog,
                            onSearchFiltersConfirmButtonClick=viewModel::searchFiltersConfirmButtonClick,
                            onSearchFiltersLocationItemCheckChange=viewModel::updateSearchFiltersLocationItemCheck,
                            onSearchFiltersTypeItemCheckChange=viewModel::updateSearchFiltersTypeItemCheck,
                            onSearchFiltersRoomsItemCheckChange=viewModel::updateSearchFiltersRoomsItemCheck,
                            onSearchFiltersDialogSegmentedButtonOptionSelected=viewModel::updateSearchFiltersDialogSegmentedButtonOptionSelectedIndex,
                        )
                    }
                }
            }
        }
    }
}