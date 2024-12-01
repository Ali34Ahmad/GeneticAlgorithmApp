package com.example.geneticalgorithm.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.geneticalgorithm.core.ext.toEnumName
import com.example.geneticalgorithm.core.ext.toNumberOfRoomsEnumName
import com.example.geneticalgorithm.core.models.FilterItemData
import com.example.geneticalgorithm.core.models.Individual
import com.example.geneticalgorithm.core.models.PieChartDetailsItem
import com.example.geneticalgorithm.dataset.houses
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.genetic_algorithm.GeneticAlgorithm
import org.example.genetic_algorithm.GeneticAlgorithm.Companion
import org.example.genetic_algorithm.GeneticAlgorithm.Companion.POPULATION_SIZE
import org.example.genetic_algorithm.GeneticAlgorithm.Companion.createInitialGeneration
import org.example.genetic_algorithm.GeneticAlgorithm.Companion.getTheTargetFitness
import org.example.genetic_algorithm.GeneticAlgorithm.Companion.selection
import kotlin.random.Random

class MainViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            createInitGeneration()
        }
    }

    private suspend fun createInitGeneration() {
        val prohibitedLocation = uiState.value.algorithmFiltersDialogLocationItems
            .filter { !it.isChecked }
            .map { it.toLocationHouseFeatureData() }
        val prohibitedTypes = uiState.value.algorithmFiltersDialogTypeItems
            .filter { !it.isChecked }
            .map { it.toHouseTypeHouseFeatureData() }
        val prohibitedNumberOfRooms = uiState.value.algorithmFiltersDialogNumberOfRoomsItems
            .filter { !it.isChecked }
            .map { it.toNumberOfRoomsHouseFeatureData() }


        updateIsGenerationLoading(true)
        delay(5000L)
        _uiState.update {
            it.copy(
                generationNumber = 1,
                generation = createInitialGeneration(),
                targetFitness = getTheTargetFitness(
                    prohibitedLocations = prohibitedLocation,
                    prohibitedTypes = prohibitedTypes,
                    prohibitedNumberOfRooms = prohibitedNumberOfRooms
                ),
            )
        }
        updateIsGenerationLoading(false)
    }

    //Statistics Section
    fun onSegmentedButtonOptionClick(optionIndex: Int) {
        _uiState.update {
            it.copy(
                selectedOption = optionIndex,
            )
        }
    }

    private suspend fun refreshStatistics() {
        updateIsStatisticsLoading(true)
        _uiState.update {
            it.copy(
                pieChartLocationDetails = uiState.value.pieChartLocationDetailsDraft,
                pieChartTypeDetails = uiState.value.pieChartTypeDetailsDraft,
                pieChartRoomsDetails = uiState.value.pieChartRoomsDetailsDraft,
            )
        }
        clearDrafts()
        createInitGeneration()
        updateIsStatisticsLoading(false)
    }

    private fun shouldRefreshStatistics(): Boolean {
        return uiState.value.pieChartLocationDetailsDraft.isNotEmpty() ||
                uiState.value.pieChartTypeDetailsDraft.isNotEmpty() ||
                uiState.value.pieChartRoomsDetailsDraft.isNotEmpty()
    }

    private fun updateIsStatisticsLoading(isLoading: Boolean) {
        _uiState.update { it.copy(isStatisticsLoading = isLoading) }
    }

    //Available Houses
    private fun updateClearFilterButtonVisibility(isVisible: Boolean) {
        _uiState.update { it.copy(showClearFiltersButton = isVisible) }
    }

    fun buyHouse(index: Int) {
        updateLocationStatisticsDrafts(index)
        updateTypeStatisticsDrafts(index)
        updateNumberOfRoomsStatisticsDrafts(index)
    }

    private fun updateLocationStatisticsDrafts(index: Int) {
        val locationItems: List<PieChartDetailsItem> =
            uiState.value.pieChartLocationDetails.map { pieChartDetailsItem ->
                if (pieChartDetailsItem.name.toEnumName() == uiState.value.availableHouses[index].location.name) {
                    pieChartDetailsItem.copy(numberOfSales = pieChartDetailsItem.numberOfSales + 10)
                } else {
                    pieChartDetailsItem
                }
            }
        _uiState.update { it.copy(pieChartLocationDetailsDraft = locationItems) }
    }

    private fun updateTypeStatisticsDrafts(index: Int) {
        val typeItems: List<PieChartDetailsItem> =
            uiState.value.pieChartTypeDetails.map { pieChartDetailsItem ->
                if (pieChartDetailsItem.name.toEnumName() == uiState.value.availableHouses[index].type.name) {
                    pieChartDetailsItem.copy(numberOfSales = pieChartDetailsItem.numberOfSales + 10)
                } else {
                    pieChartDetailsItem
                }
            }
        _uiState.update { it.copy(pieChartTypeDetailsDraft = typeItems) }
    }

    private fun updateNumberOfRoomsStatisticsDrafts(index: Int) {
        val numberOfRoomItems: List<PieChartDetailsItem> =
            uiState.value.pieChartRoomsDetails.map { pieChartDetailsItem ->
                if (pieChartDetailsItem.name.toNumberOfRoomsEnumName() == uiState.value.availableHouses[index].numberOfRooms.name) {
                    pieChartDetailsItem.copy(numberOfSales = pieChartDetailsItem.numberOfSales + 10)
                } else {
                    pieChartDetailsItem
                }
            }

        _uiState.update { it.copy(pieChartRoomsDetailsDraft = numberOfRoomItems) }
    }

    private fun clearDrafts() {
        _uiState.update {
            it.copy(
                pieChartLocationDetailsDraft = emptyList(),
                pieChartTypeDetailsDraft = emptyList(),
                pieChartRoomsDetailsDraft = emptyList(),
            )
        }
    }

    fun updateSearchFiltersDialogVisibility(isVisible: Boolean) {
        _uiState.update { it.copy(showSearchFilterDialog = isVisible) }
    }

    fun clearSearchFilters() {
        viewModelScope.launch {
            updateIsAvailableHousesLoading(true)
            delay(5000)
            updateSelectAllLocationSearchFilters(true)
            updateSelectAllTypeSearchFilters(true)
            updateSelectAllRoomsSearchFilters(true)

            _uiState.update {
                it.copy(
                    availableHouses = houses,
                )
            }
            updateIsAvailableHousesLoading(false)
            updateClearFilterButtonVisibility(false)
        }
    }


    fun cancelSearchFiltersDialog() {
        updateSearchFiltersDialogVisibility(false)
        clearDrafts()
    }

    fun updateSelectAllLocationSearchFilters(value: Boolean) {
        _uiState.update { it.copy(selectAllSearchLocationFilters = value) }
        updateAllSearchFiltersLocationItemCheck(value)
    }

    fun updateSelectAllTypeSearchFilters(value: Boolean) {
        _uiState.update { it.copy(selectAllSearchTypeFilters = value) }
        updateAllSearchFiltersTypeItemCheck(value)
    }

    fun updateSelectAllRoomsSearchFilters(value: Boolean) {
        _uiState.update { it.copy(selectAllSearchRoomsFilters = value) }
        updateAllSearchFiltersRoomsItemCheck(value)
    }

    fun searchFiltersConfirmButtonClick() {
        val prohibitedLocation = uiState.value.searchFiltersDialogLocationItems
            .filter { !it.isChecked }
            .map { it.toLocationHouseFeatureData() }
        val prohibitedTypes = uiState.value.searchFiltersDialogTypeItems
            .filter { !it.isChecked }
            .map { it.toHouseTypeHouseFeatureData() }
        val prohibitedNumberOfRooms = uiState.value.searchFiltersDialogNumberOfRoomsItems
            .filter { !it.isChecked }
            .map { it.toNumberOfRoomsHouseFeatureData() }
        val filteredHouses = uiState.value.availableHouses.filter {
            !prohibitedLocation.contains(it.location) &&
                    !prohibitedTypes.contains(it.type) &&
                    !prohibitedNumberOfRooms.contains(it.numberOfRooms)
        }

        updateClearFilterButtonVisibility(
            prohibitedLocation.isNotEmpty() ||
                    prohibitedTypes.isNotEmpty() ||
                    prohibitedNumberOfRooms.isNotEmpty()
        )

        viewModelScope.launch {
            updateIsAvailableHousesLoading(true)
            delay(5000L)
            _uiState.update {
                it.copy(
                    availableHouses = filteredHouses
                )
            }
            updateIsAvailableHousesLoading(false)
        }
        updateSearchFiltersDialogVisibility(false)
    }

    private fun updateIsAvailableHousesLoading(isLoading: Boolean) {
        _uiState.update { it.copy(isAvailableHousesLoading = isLoading) }
    }

    fun updateSearchFiltersLocationItemCheck(index: Int) {
        val newLocationItems =
            uiState.value.searchFiltersDialogLocationItems.mapIndexed { currentIndex, filterItemData ->
                if (index == currentIndex) {
                    filterItemData.copy(isChecked = !filterItemData.isChecked)
                } else {
                    filterItemData
                }
            }

        _uiState.update {
            it.copy(
                searchFiltersDialogLocationItems = newLocationItems,
            )
        }
        _uiState.update {
            it.copy(
                selectAllSearchLocationFilters = allFiltersAreChecked(uiState.value.searchFiltersDialogLocationItems),
            )
        }
    }

    private fun allFiltersAreChecked(items: List<FilterItemData>): Boolean {
        items.forEach {
            if (!it.isChecked) return false
        }
        return true
    }

    fun updateSearchFiltersTypeItemCheck(index: Int) {
        val newTypeItems =
            uiState.value.searchFiltersDialogTypeItems.mapIndexed { currentIndex, filterItemData ->
                if (index == currentIndex) {
                    filterItemData.copy(isChecked = !filterItemData.isChecked)
                } else {
                    filterItemData
                }
            }

        _uiState.update {
            it.copy(
                searchFiltersDialogTypeItems = newTypeItems,
            )
        }
        _uiState.update {
            it.copy(
                selectAllSearchTypeFilters = allFiltersAreChecked(uiState.value.searchFiltersDialogTypeItems),
            )
        }
    }

    fun updateSearchFiltersRoomsItemCheck(index: Int) {
        val newNumberOfRoomsItems =
            uiState.value.searchFiltersDialogNumberOfRoomsItems.mapIndexed { currentIndex, filterItemData ->
                if (index == currentIndex) {
                    filterItemData.copy(isChecked = !filterItemData.isChecked)
                } else {
                    filterItemData
                }
            }

        _uiState.update {
            it.copy(
                searchFiltersDialogNumberOfRoomsItems = newNumberOfRoomsItems,
            )
        }
        _uiState.update {
            it.copy(
                selectAllSearchRoomsFilters = allFiltersAreChecked(uiState.value.searchFiltersDialogNumberOfRoomsItems),
            )
        }
    }

    fun updateSearchFiltersDialogSegmentedButtonOptionSelectedIndex(index: Int) {
        _uiState.update { it.copy(searchFiltersDialogSegmentedButtonIndex = index) }
    }


    private fun updateAllSearchFiltersLocationItemCheck(value: Boolean) {
        val newLocationItems =
            uiState.value.searchFiltersDialogLocationItems.map { filterItemData ->
                filterItemData.copy(isChecked = value)
            }
        _uiState.update {
            it.copy(searchFiltersDialogLocationItems = newLocationItems)
        }
    }

    private fun updateAllSearchFiltersTypeItemCheck(value: Boolean) {
        val newTypeItems =
            uiState.value.searchFiltersDialogTypeItems.map { filterItemData ->
                filterItemData.copy(isChecked = value)
            }
        _uiState.update {
            it.copy(searchFiltersDialogTypeItems = newTypeItems)
        }
    }

    private fun updateAllSearchFiltersRoomsItemCheck(value: Boolean) {
        val newTypeItems =
            uiState.value.searchFiltersDialogNumberOfRoomsItems.map { filterItemData ->
                filterItemData.copy(isChecked = value)
            }
        _uiState.update {
            it.copy(searchFiltersDialogNumberOfRoomsItems = newTypeItems)
        }
    }


    //Generation Section
    private fun updateIsGenerationLoading(isLoading: Boolean) {
        _uiState.update { it.copy(isGenerationLoading = isLoading) }
    }

    private fun updateIsWorkingOnNextGeneration(isWorking: Boolean) {
        _uiState.update { it.copy(isWorkingOnNewGeneration = isWorking) }
    }

    fun updateAlgorithmFiltersDialogVisibility(isVisible: Boolean) {
        _uiState.update { it.copy(showCustomizeAlgorithmFiltersDialog = isVisible) }
    }

    fun updateSelectAllLocationAlgorithmFilters(value: Boolean) {
        _uiState.update { it.copy(selectAllAlgorithmLocationFilters = value) }
        updateAllAlgorithmFiltersLocationItemCheck(value)
    }

    fun updateSelectAllTypeAlgorithmFilters(value: Boolean) {
        _uiState.update { it.copy(selectAllAlgorithmTypeFilters = value) }
        updateAllAlgorithmFiltersTypeItemCheck(value)
    }

    fun updateSelectAllRoomsAlgorithmFilters(value: Boolean) {
        _uiState.update { it.copy(selectAllAlgorithmRoomsFilters = value) }
        updateAllAlgorithmFiltersRoomsItemCheck(value)
    }

    private fun updateAllAlgorithmFiltersLocationItemCheck(value: Boolean) {
        val newLocationItems =
            uiState.value.algorithmFiltersDialogLocationItems.map { filterItemData ->
                filterItemData.copy(isChecked = value)
            }
        _uiState.update {
            it.copy(algorithmFiltersDialogLocationItems = newLocationItems)
        }
    }

    private fun updateAllAlgorithmFiltersTypeItemCheck(value: Boolean) {
        val newTypeItems =
            uiState.value.algorithmFiltersDialogTypeItems.map { filterItemData ->
                filterItemData.copy(isChecked = value)
            }
        _uiState.update {
            it.copy(algorithmFiltersDialogTypeItems = newTypeItems)
        }
    }

    private fun updateAllAlgorithmFiltersRoomsItemCheck(value: Boolean) {
        val newTypeItems =
            uiState.value.algorithmFiltersDialogNumberOfRoomsItems.map { filterItemData ->
                filterItemData.copy(isChecked = value)
            }
        _uiState.update {
            it.copy(algorithmFiltersDialogNumberOfRoomsItems = newTypeItems)
        }
    }

    fun cancelAlgorithmFiltersDialog() {
        updateAlgorithmFiltersDialogVisibility(false)
    }

    fun algorithmFiltersConfirmButtonClick() {
        val prohibitedLocation = uiState.value.algorithmFiltersDialogLocationItems
            .filter { !it.isChecked }
            .map { it.toLocationHouseFeatureData() }
        val prohibitedTypes = uiState.value.algorithmFiltersDialogTypeItems
            .filter { !it.isChecked }
            .map { it.toHouseTypeHouseFeatureData() }
        val prohibitedNumberOfRooms = uiState.value.algorithmFiltersDialogNumberOfRoomsItems
            .filter { !it.isChecked }
            .map { it.toNumberOfRoomsHouseFeatureData() }

        viewModelScope.launch {
            updateIsGenerationLoading(true)
            delay(5000L)
            _uiState.update {
                it.copy(
                    generation = createInitialGeneration(),
                    generationNumber = 1,
                    targetFitness = getTheTargetFitness(
                        prohibitedLocations = prohibitedLocation,
                        prohibitedTypes = prohibitedTypes,
                        prohibitedNumberOfRooms = prohibitedNumberOfRooms
                    ),
                )
            }
            updateIsGenerationLoading(false)
            runAlgorithm()
        }
        updateAlgorithmFiltersDialogVisibility(false)
    }

    fun updateAlgorithmFiltersDialogSegmentedButtonOptionSelectedIndex(index: Int) {
        _uiState.update { it.copy(algorithmFiltersDialogSegmentedButtonIndex = index) }
    }

    fun updateAlgorithmFiltersLocationItemCheck(index: Int) {
        val newLocationItems =
            uiState.value.algorithmFiltersDialogLocationItems.mapIndexed { currentIndex, filterItemData ->
                if (index == currentIndex) {
                    filterItemData.copy(isChecked = !filterItemData.isChecked)
                } else {
                    filterItemData
                }
            }

        _uiState.update {
            it.copy(
                algorithmFiltersDialogLocationItems = newLocationItems,
            )
        }
        _uiState.update {
            it.copy(
                selectAllAlgorithmLocationFilters = allLocationFiltersAreChecked(),
            )
        }

    }

    private fun allLocationFiltersAreChecked(): Boolean {
        uiState.value.algorithmFiltersDialogLocationItems.forEach {
            if (!it.isChecked) return false
        }
        return true
    }

    fun updateAlgorithmFiltersTypeItemCheck(index: Int) {
        val newLocationItems =
            uiState.value.algorithmFiltersDialogTypeItems.mapIndexed { currentIndex, filterItemData ->
                if (index == currentIndex) {
                    filterItemData.copy(isChecked = !filterItemData.isChecked)
                } else {
                    filterItemData
                }
            }
        _uiState.update {
            it.copy(algorithmFiltersDialogTypeItems = newLocationItems)
        }
        _uiState.update {
            it.copy(
                selectAllAlgorithmTypeFilters = allHouseTypeFiltersAreChecked(),
            )
        }

    }

    private fun allHouseTypeFiltersAreChecked(): Boolean {
        uiState.value.algorithmFiltersDialogTypeItems.forEach {
            if (!it.isChecked) return false
        }
        return true
    }

    fun updateAlgorithmFiltersRoomsItemCheck(index: Int) {
        val newLocationItems =
            uiState.value.algorithmFiltersDialogNumberOfRoomsItems.mapIndexed { currentIndex, filterItemData ->
                if (index == currentIndex) {
                    filterItemData.copy(isChecked = !filterItemData.isChecked)
                } else {
                    filterItemData
                }
            }
        _uiState.update {
            it.copy(algorithmFiltersDialogNumberOfRoomsItems = newLocationItems)
        }
        _uiState.update {
            it.copy(
                selectAllAlgorithmRoomsFilters = allNumberOfRoomsFiltersAreChecked(),
            )
        }

    }

    private fun allNumberOfRoomsFiltersAreChecked(): Boolean {
        uiState.value.algorithmFiltersDialogNumberOfRoomsItems.forEach {
            if (!it.isChecked) return false
        }
        return true
    }


    fun runAlgorithm() {
        viewModelScope.launch {
            if (shouldRefreshStatistics()) {
                refreshStatistics()
            }
            // Get the initial generation
            val currentGeneration = uiState.value.generation.toMutableList()
            currentGeneration.sortDescending()
            val newGeneration = mutableListOf<Individual>()

            // Define termination condition (target fitness or maximum generations)
            var generationCount = 0
            var bestFitness = currentGeneration.first().fitness

            // Run the algorithm loop
            while (bestFitness < uiState.value.targetFitness && uiState.value.generationNumber < 5) {
                updateIsWorkingOnNextGeneration(true)
                delay(5000L)

                generationCount++
                // Update best fitness

                val selectedParents = selection(currentGeneration)

                //Add the fittest 10% to the newGeneration
                newGeneration.addAll(selectedParents)

                // Crossover: Create new individuals by combining parents' genes
                val s = (90 * POPULATION_SIZE) / 100
                val offspring = mutableListOf<Individual>()
                repeat(s) {
                    //select the parent from individuals that have the maximum 50% fitness.
                    val randomParent1Index = Random.nextInt(currentGeneration.size / 2)
                    val randomParent2Index = Random.nextInt(currentGeneration.size / 2)
                    val parent1 = currentGeneration[randomParent1Index]
                    val parent2 = currentGeneration[randomParent2Index]
                    // Mating both parents
                    offspring.add(parent1.mate(parent2))
                }

                // Update the new generation
                newGeneration.addAll(offspring)

                newGeneration.sortDescending()

                // need this codes
                currentGeneration.clear()
                currentGeneration.addAll(newGeneration)
                newGeneration.clear()

                bestFitness = currentGeneration.first().fitness

                updateIsGenerationLoading(true)
                updateIsWorkingOnNextGeneration(false)
                delay(5000L)
                _uiState.update {
                    it.copy(
                        generation = currentGeneration,
                        generationNumber = generationCount + 1,
                    )
                }
                updateIsGenerationLoading(false)
            }
        }
    }

}