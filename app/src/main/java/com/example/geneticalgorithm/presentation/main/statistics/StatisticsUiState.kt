package com.example.geneticalgorithm.presentation.main.statistics

import com.example.geneticalgorithm.core.models.PieChartDetailsItem
import com.example.geneticalgorithm.presentation.main.MainUiState
import com.example.geneticalgorithm.presentation.main.generation.GenerationUiState

data class StatisticsUiState(
    val selectedOption:Int,
    val pieChartLocationDetails: List<PieChartDetailsItem>,
    val pieChartTypeDetails: List<PieChartDetailsItem>,
    val pieChartRoomsDetails: List<PieChartDetailsItem>,
)
fun MainUiState.toStatisticsUiState()= StatisticsUiState(
    selectedOption = this.selectedOption,
    pieChartLocationDetails = this.pieChartLocationDetails,
    pieChartTypeDetails = this.pieChartTypeDetails,
    pieChartRoomsDetails = this.pieChartRoomsDetails
)