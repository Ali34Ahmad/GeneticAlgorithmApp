package com.example.geneticalgorithm.presentation.main.available_homes

import com.example.geneticalgorithm.core.models.DisplayedHouse
import com.example.geneticalgorithm.presentation.main.MainUiState

data class AvailableHousesUiState(
    val availableHouses:List<DisplayedHouse> ,
    val isAvailableHousesLoading:Boolean,
    val showClearFilters:Boolean,
    val showFilterSearchDialog:Boolean,
)

fun MainUiState.toAvailableHousesUiState()= AvailableHousesUiState(
    availableHouses = this.availableHouses,
    isAvailableHousesLoading = this.isAvailableHousesLoading,
    showClearFilters = this.showClearFiltersButton,
    showFilterSearchDialog = this.showSearchFilterDialog,
)
