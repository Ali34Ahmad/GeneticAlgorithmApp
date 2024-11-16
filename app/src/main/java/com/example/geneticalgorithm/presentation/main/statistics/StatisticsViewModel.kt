package com.example.geneticalgorithm.presentation.main.statistics

import androidx.lifecycle.ViewModel
import com.example.geneticalgorithm.core.models.PieChartDetailsItem
import com.example.geneticalgorithm.presentation.ui.helper.pieChartDetailsItems
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class StatisticsViewModel() : ViewModel() {
    private val _uiState = MutableStateFlow(StatisticsUiState())
    val uiState: StateFlow<StatisticsUiState> = _uiState.asStateFlow()

    fun onSegmentedButtonOptionClick(optionIndex: Int) {
        _uiState.update {
            it.copy(
                selectedOption = optionIndex,
            )
        }
    }
}