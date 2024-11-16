package com.example.geneticalgorithm.presentation.main

import androidx.lifecycle.ViewModel
import com.example.geneticalgorithm.core.models.Individual
import com.example.geneticalgorithm.presentation.main.statistics.StatisticsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.example.genetic_algorithm.GeneticAlgorithm.Companion.POPULATION_SIZE
import org.example.genetic_algorithm.GeneticAlgorithm.Companion.createInitialGeneration
import org.example.genetic_algorithm.GeneticAlgorithm.Companion.getTheTargetFitness
import org.example.genetic_algorithm.GeneticAlgorithm.Companion.selection
import kotlin.random.Random

class MainViewModel:ViewModel(){
    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

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
        // Create the initial generation
        val currentGeneration = createInitialGeneration().toMutableList()
        currentGeneration.sortDescending()
        val newGeneration = mutableListOf<Individual>()

        // Define termination condition (target fitness or maximum generations)
        val targetFitness = getTheTargetFitness()
        println("the target fitness : $targetFitness")
        var generationCount = 0
        var bestFitness = currentGeneration.first().fitness

        println("---------------------------initial generation ------------------------------")

        // Run the algorithm loop
        while (bestFitness < targetFitness) {
            //print the generation information
            println("we are in the # $generationCount generation.")
            println("the best fitness: $bestFitness")
            currentGeneration.forEach{
                println("$it ${it.fitness}")
            }
            println("---------------------------new generation ------------------------------")
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

        }
        println("we are in the # $generationCount generation.")
        println("the best fitness: $bestFitness")
        currentGeneration.forEach{
            println("$it ${it.fitness}")
        }
        println("---------------------------final generation ------------------------------")
    }
}