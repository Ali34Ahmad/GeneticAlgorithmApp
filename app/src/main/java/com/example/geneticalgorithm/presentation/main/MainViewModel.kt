package com.example.geneticalgorithm.presentation.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.geneticalgorithm.core.models.Individual
import com.example.geneticalgorithm.presentation.main.statistics.StatisticsUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
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
            updateIsGenerationLoading(true)
            delay(10000L)
            _uiState.update {
                it.copy(
                    generation = createInitialGeneration(),
                    targetFitness = getTheTargetFitness(),
                )
            }
            updateIsGenerationLoading(false)
        }
    }

    fun onSegmentedButtonOptionClick(optionIndex: Int) {
        _uiState.update {
            it.copy(
                selectedOption = optionIndex,
            )
        }
    }

    fun showAdvancedSearchDialog() {
        _uiState.update { it.copy(showAdvancedSearchDialog = true) }
    }

    fun runAlgorithm() {
        // Get the initial generation
        val currentGeneration = uiState.value.generation.toMutableList()
        currentGeneration.sortDescending()
        val newGeneration = mutableListOf<Individual>()

        // Define termination condition (target fitness or maximum generations)
        _uiState.update {
            it.copy(
            )
        }
        Log.v("GA : Target fitness", uiState.value.targetFitness.toString())
        var generationCount = 0
        var bestFitness = currentGeneration.first().fitness

        Log.v(
            "GA :,",
            "---------------------------initial generation ------------------------------"
        )

        // Run the algorithm loop
        viewModelScope.launch {
            while (bestFitness < uiState.value.targetFitness) {
                updateIsWorkingOnNextGeneration(true)
                delay(10000L)
                //print the generation information
                Log.v("GA :GenerationNumber ", generationCount.toString())
                Log.v("GA : Best Fitness:", bestFitness.toString())
                currentGeneration.forEach {
                    Log.v("GA : Generation", "$it ${it.fitness}")
                }
                Log.v(
                    "GA :,",
                    "---------------------------new generation ------------------------------"
                )
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

                updateIsWorkingOnNextGeneration(false)
                updateIsGenerationLoading(true)
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

        Log.v("GA :GenerationNumber ", generationCount.toString())
        Log.v("GA : Best Fitness:", bestFitness.toString())
        currentGeneration.forEach {
            Log.v("GA : Generation", "$it ${it.fitness}")
        }
        println("---------------------------final generation ------------------------------")
    }

    private fun updateIsGenerationLoading(isLoading: Boolean) {
        _uiState.update { it.copy(isGenerationLoading = isLoading) }
    }

    private fun updateIsWorkingOnNextGeneration(isWorking: Boolean) {
        _uiState.update { it.copy(isWorkingOnNewGeneration = isWorking) }
    }

}