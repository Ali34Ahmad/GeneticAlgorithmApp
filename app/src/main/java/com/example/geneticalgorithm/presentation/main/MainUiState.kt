package com.example.geneticalgorithm.presentation.main

import com.example.geneticalgorithm.core.constants.statisticsColors
import com.example.geneticalgorithm.core.ext.toAppropriateFormat
import com.example.geneticalgorithm.core.models.HouseFeature
import com.example.geneticalgorithm.core.models.Individual
import com.example.geneticalgorithm.core.models.PieChartDetailsItem
import com.example.geneticalgorithm.presentation.ui.helper.pieChartDetailsItemsFake
import org.example.dataset.HouseFitness

data class MainUiState(
    val selectedOption: Int = 0,
    val pieChartLocationDetails: List<PieChartDetailsItem> = HouseFitness.locations.toList()
        .mapIndexed {index,it-> it.toPieChartLocationDetailsItem(index) },
    val pieChartTypeDetails: List<PieChartDetailsItem> = HouseFitness.types.toList()
        .mapIndexed {index,it-> it.toPieChartTypeDetailsItem(index) },
    val pieChartRoomsDetails: List<PieChartDetailsItem> = HouseFitness.types.toList()
        .mapIndexed {index,it-> it.toPieChartTypeDetailsItem(index) },
    val isStatisticsLoading: Boolean = false,

    val generationNumber: Int = 1,
    val generation: List<Individual> = emptyList(),
    val isGenerationLoading: Boolean = false,
    val isWorkingOnNewGeneration: Boolean = false,
    val targetFitness: Int =0,

    val showAdvancedSearchDialog: Boolean = false,
    val showFilterSearchDialog: Boolean = false,
)

private fun Pair<HouseFeature.Location, Int>.toPieChartLocationDetailsItem(index:Int): PieChartDetailsItem {
    return PieChartDetailsItem(
        color = statisticsColors[index],
        name = this.first.toAppropriateFormat(),
        numberOfSales = this.second,
    )
}

private fun Pair<HouseFeature.HouseType, Int>.toPieChartTypeDetailsItem(index:Int): PieChartDetailsItem {
    return PieChartDetailsItem(
        color = statisticsColors[index],
        name = this.first.name.toAppropriateFormat(),
        numberOfSales = this.second,
    )
}

private fun Pair<HouseFeature.NumberOfRooms, Int>.toPieChartNumberOfRoomsDetailsItem(index:Int): PieChartDetailsItem {
    return PieChartDetailsItem(
        color = statisticsColors[index],
        name = this.first.toAppropriateFormat(),
        numberOfSales = this.second,
    )
}



