package com.example.geneticalgorithm.presentation.main

import com.example.geneticalgorithm.core.models.Individual
import com.example.geneticalgorithm.core.models.PieChartDetailsItem
import com.example.geneticalgorithm.presentation.ui.helper.generationFake
import com.example.geneticalgorithm.presentation.ui.helper.pieChartDetailsItemsFake

data class MainUiState(
    val selectedOption:Int=0,
    val pieChartLocationDetails: List<PieChartDetailsItem> =pieChartDetailsItemsFake,
    val pieChartTypeDetails: List<PieChartDetailsItem> = pieChartDetailsItemsFake,
    val pieChartRoomsDetails: List<PieChartDetailsItem> = pieChartDetailsItemsFake,

    val generationNumber: Int=1,
    val generation: List<Individual> = generationFake,

    val showAdvancedSearchDialog:Boolean=false,
    val showFilterSearchDialog:Boolean=false,
)