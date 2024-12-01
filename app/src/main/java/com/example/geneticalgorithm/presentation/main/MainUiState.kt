package com.example.geneticalgorithm.presentation.main

import com.example.geneticalgorithm.core.constants.statisticsColors
import com.example.geneticalgorithm.core.ext.toAppropriateFormat
import com.example.geneticalgorithm.core.ext.toEnumName
import com.example.geneticalgorithm.core.models.DisplayedHouse
import com.example.geneticalgorithm.core.models.FilterItemData
import com.example.geneticalgorithm.core.models.HouseFeature
import com.example.geneticalgorithm.core.models.Individual
import com.example.geneticalgorithm.core.models.PieChartDetailsItem
import com.example.geneticalgorithm.dataset.houses
import org.example.dataset.HouseFitness

data class MainUiState(
    val selectedOption: Int = 0,
    val pieChartLocationDetails: List<PieChartDetailsItem> = HouseFitness.locations.toList()
        .mapIndexed { index, it -> it.toPieChartLocationDetailsItem(index) },
    val pieChartTypeDetails: List<PieChartDetailsItem> = HouseFitness.types.toList()
        .mapIndexed { index, it -> it.toPieChartTypeDetailsItem(index) },
    val pieChartRoomsDetails: List<PieChartDetailsItem> = HouseFitness.numberOfRooms.toList()
        .mapIndexed { index, it -> it.toPieChartNumberOfRoomsDetailsItem(index) },
    val pieChartLocationDetailsDraft: List<PieChartDetailsItem> = emptyList(),
    val pieChartTypeDetailsDraft: List<PieChartDetailsItem> = emptyList(),
    val pieChartRoomsDetailsDraft: List<PieChartDetailsItem> = emptyList(),

    val isStatisticsLoading: Boolean = false,

    val availableHouses:List<DisplayedHouse> = houses,
    val isAvailableHousesLoading:Boolean =false,
    val showClearFiltersButton:Boolean=false,
    val showSearchFilterDialog:Boolean=false,
    val searchFiltersDialogSegmentedButtonIndex:Int=0,
    val selectAllSearchLocationFilters:Boolean=true,
    val selectAllSearchTypeFilters:Boolean=true,
    val selectAllSearchRoomsFilters:Boolean=true,

    val searchFiltersDialogLocationItems: List<FilterItemData> = HouseFitness.locations.toList()
        .map { it.toLocationFilterItemData() },
    val searchFiltersDialogTypeItems: List<FilterItemData> = HouseFitness.types.toList()
        .map { it.toHouseTypeFilterItemData() },
    val searchFiltersDialogNumberOfRoomsItems: List<FilterItemData> = HouseFitness.numberOfRooms.toList()
        .map { it.toNumberOfRoomsFilterItemData() },

    val generationNumber: Int = 1,
    val generation: List<Individual> = emptyList(),
    val isGenerationLoading: Boolean = false,
    val isWorkingOnNewGeneration: Boolean = false,
    val targetFitness: Int = 0,

    val showCustomizeAlgorithmFiltersDialog: Boolean = false,
    val selectAllAlgorithmLocationFilters: Boolean = true,
    val selectAllAlgorithmTypeFilters: Boolean = true,
    val selectAllAlgorithmRoomsFilters: Boolean = true,
    val algorithmFiltersDialogSegmentedButtonIndex: Int = 0,
    val algorithmFiltersDialogLocationItems: List<FilterItemData> = HouseFitness.locations.toList()
        .map { it.toLocationFilterItemData() },
    val algorithmFiltersDialogTypeItems: List<FilterItemData> = HouseFitness.types.toList()
        .map { it.toHouseTypeFilterItemData() },
    val algorithmFiltersDialogNumberOfRoomsItems: List<FilterItemData> = HouseFitness.numberOfRooms.toList()
        .map { it.toNumberOfRoomsFilterItemData() },
)

private fun Pair<HouseFeature.Location, Int>.toLocationFilterItemData(): FilterItemData {
    return FilterItemData(
        text = this.first.toAppropriateFormat(),
        isChecked = true,
        number = this.second,
    )
}

fun FilterItemData.toLocationHouseFeatureData(): HouseFeature.Location {
    return HouseFeature.Location.entries.find {
        this.text.toEnumName() == it.name
    }?:HouseFeature.Location.SUBURB
}

fun FilterItemData.toHouseTypeHouseFeatureData(): HouseFeature.HouseType {
    return HouseFeature.HouseType.entries.find {
        this.text.toEnumName() == it.name
    }?:HouseFeature.HouseType.VILLA
}

fun FilterItemData.toNumberOfRoomsHouseFeatureData(): HouseFeature.NumberOfRooms {
    return HouseFeature.NumberOfRooms.entries.find {
        this.text.toEnumName() == it.name
    }?:HouseFeature.NumberOfRooms.R1
}




private fun Pair<HouseFeature.HouseType, Int>.toHouseTypeFilterItemData(): FilterItemData {
    return FilterItemData(
        text = this.first.name.toAppropriateFormat(),
        isChecked = true,
        number = this.second,
    )
}

private fun Pair<HouseFeature.NumberOfRooms, Int>.toNumberOfRoomsFilterItemData(): FilterItemData {
    return FilterItemData(
        text = this.first.toAppropriateFormat(),
        isChecked = true,
        number = this.second,
    )
}





private fun Pair<HouseFeature.Location, Int>.toPieChartLocationDetailsItem(index: Int): PieChartDetailsItem {
    return PieChartDetailsItem(
        color = statisticsColors[index],
        name = this.first.toAppropriateFormat(),
        numberOfSales = this.second,
    )
}

private fun Pair<HouseFeature.HouseType, Int>.toPieChartTypeDetailsItem(index: Int): PieChartDetailsItem {
    return PieChartDetailsItem(
        color = statisticsColors[index],
        name = this.first.name.toAppropriateFormat(),
        numberOfSales = this.second,
    )
}

private fun Pair<HouseFeature.NumberOfRooms, Int>.toPieChartNumberOfRoomsDetailsItem(index: Int): PieChartDetailsItem {
    return PieChartDetailsItem(
        color = statisticsColors[index],
        name = this.first.toAppropriateFormat(),
        numberOfSales = this.second,
    )
}



