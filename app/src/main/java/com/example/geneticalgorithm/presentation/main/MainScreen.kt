package com.example.geneticalgorithm.presentation.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.geneticalgorithm.R
import com.example.geneticalgorithm.core.component.BottomBar
import com.example.geneticalgorithm.core.component.dialog.FilterDialog
import com.example.geneticalgorithm.core.constants.SegmentedButtonOptions
import com.example.geneticalgorithm.core.constants.generationTableTitles
import com.example.geneticalgorithm.presentation.main.available_homes.AvailableHousesSection
import com.example.geneticalgorithm.presentation.main.available_homes.toAvailableHousesUiState
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
    onAlgorithmFiltersSelectAllLocationCheckChange: (Boolean) -> Unit,
    onAlgorithmFiltersSelectAllTypeCheckChange: (Boolean) -> Unit,
    onAlgorithmFiltersSelectAllRoomsCheckChange: (Boolean) -> Unit,
    onAlgorithmFiltersCancelButtonClick: () -> Unit,
    onAlgorithmFiltersConfirmButtonClick: () -> Unit,
    onAlgorithmFiltersDialogSegmentedButtonOptionSelected: (index: Int) -> Unit,
    onAlgorithmFiltersLocationItemCheckChange: (index: Int) -> Unit,
    onAlgorithmFiltersTypeItemCheckChange: (index: Int) -> Unit,
    onAlgorithmFiltersRoomsItemCheckChange: (index: Int) -> Unit,
    onBuyHouseButtonClick: (index: Int) -> Unit,
    onCustomizeSearchFilterButtonClick: () -> Unit,
    onClearSearchFilterButtonClick: () -> Unit,
    onSearchFiltersSelectAllLocationCheckChange: (Boolean) -> Unit,
    onSearchFiltersSelectAllTypeCheckChange: (Boolean) -> Unit,
    onSearchFiltersSelectAllRoomsCheckChange: (Boolean) -> Unit,
    onSearchFiltersCancelButtonClick: () -> Unit,
    onSearchFiltersConfirmButtonClick: () -> Unit,
    onSearchFiltersLocationItemCheckChange: (index:Int) -> Unit,
    onSearchFiltersTypeItemCheckChange: (index:Int) -> Unit,
    onSearchFiltersRoomsItemCheckChange: (index:Int) -> Unit,
    onSearchFiltersDialogSegmentedButtonOptionSelected: (index:Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()
    Scaffold(
        bottomBar = {
            BottomBar(
                onAdvancedSearchClick = onAdvancedSearchClick,
                onRunAlgorithmClick = onRunAlgorithmClick,
                isDisable = uiState.isGenerationLoading || uiState.isWorkingOnNewGeneration
            )
        },
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
                AvailableHousesSection(
                    uiState = uiState.toAvailableHousesUiState(),
                    onCustomizeFiltersButtonClick = onCustomizeSearchFilterButtonClick,
                    onClearFiltersButtonClick = onClearSearchFilterButtonClick,
                    onBuyButtonClick = onBuyHouseButtonClick,
                    modifier=Modifier.fillMaxWidth()
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


    if (uiState.showCustomizeAlgorithmFiltersDialog) {
        FilterDialog(
            title = R.string.customize_filters,
            subtitle = R.string.customize_filters_subtitle,
            buttonText = R.string.apply,
            onSelectAllLocationCheckChange = onAlgorithmFiltersSelectAllLocationCheckChange,
            onSelectAllTypeCheckChange = onAlgorithmFiltersSelectAllTypeCheckChange,
            onSelectAllRoomsCheckChange = onAlgorithmFiltersSelectAllRoomsCheckChange,
            onCancelButtonClick = onAlgorithmFiltersCancelButtonClick,
            onConfirmButtonClick = onAlgorithmFiltersConfirmButtonClick,
            onLocationItemSelected = onAlgorithmFiltersLocationItemCheckChange,
            onTypeItemSelected = onAlgorithmFiltersTypeItemCheckChange,
            onRoomsItemSelected = onAlgorithmFiltersRoomsItemCheckChange,
            locationItems = uiState.algorithmFiltersDialogLocationItems,
            typeItems = uiState.algorithmFiltersDialogTypeItems,
            roomsItems = uiState.algorithmFiltersDialogNumberOfRoomsItems,
            segmentedButtons = SegmentedButtonOptions.HouseFeatures,
            segmentedButtonSelectedOption = uiState.algorithmFiltersDialogSegmentedButtonIndex,
            onSegmentedButtonOptionSelected = onAlgorithmFiltersDialogSegmentedButtonOptionSelected,
            selectAllLocationFilters = uiState.selectAllAlgorithmLocationFilters,
            selectAllTypeFilters = uiState.selectAllAlgorithmTypeFilters,
            selectAllRoomsFilters = uiState.selectAllAlgorithmRoomsFilters,
        )
    }
    if (uiState.showSearchFilterDialog) {
        FilterDialog(
            title = R.string.customize_search,
            subtitle = R.string.customize_filters_subtitle,
            buttonText = R.string.apply,
            onSelectAllLocationCheckChange = onSearchFiltersSelectAllLocationCheckChange,
            onSelectAllTypeCheckChange = onSearchFiltersSelectAllTypeCheckChange,
            onSelectAllRoomsCheckChange = onSearchFiltersSelectAllRoomsCheckChange,
            onCancelButtonClick = onSearchFiltersCancelButtonClick,
            onConfirmButtonClick = onSearchFiltersConfirmButtonClick,
            onLocationItemSelected = onSearchFiltersLocationItemCheckChange,
            onTypeItemSelected = onSearchFiltersTypeItemCheckChange,
            onRoomsItemSelected = onSearchFiltersRoomsItemCheckChange,
            locationItems = uiState.searchFiltersDialogLocationItems,
            typeItems = uiState.searchFiltersDialogTypeItems,
            roomsItems = uiState.searchFiltersDialogNumberOfRoomsItems,
            segmentedButtons = SegmentedButtonOptions.HouseFeatures,
            segmentedButtonSelectedOption = uiState.searchFiltersDialogSegmentedButtonIndex,
            onSegmentedButtonOptionSelected = onSearchFiltersDialogSegmentedButtonOptionSelected,
            selectAllLocationFilters = uiState.selectAllSearchLocationFilters,
            selectAllTypeFilters = uiState.selectAllSearchTypeFilters,
            selectAllRoomsFilters = uiState.selectAllSearchRoomsFilters,
        )
    }
}