package org.example.genetic_algorithm

import com.example.geneticalgorithm.algorithm.House
import com.example.geneticalgorithm.algorithm.HouseFeature
import com.example.geneticalgorithm.algorithm.Individual
import org.example.dataset.HouseFitness
import org.example.dataset.ProhibitedFeatures
import kotlin.random.Random

class GeneticAlgorithm {
    companion object {

        // The number of individuals in the generation
        const val POPULATION_SIZE = 100


        /**
         * This function return the fitness of a given house
         * according to the total fitness of its attributes.
         * @author Ali Mansoura.
         */
        fun calculateFitness(house: House): Int {
            //If the house contains prohibited features it will be undesirable.
            if(house.containsProhibitedFeatures())
                return 0

            val numberOfRoomsFitness = HouseFitness.numberOfRooms[house.numberOfRooms] ?: 0

            val houseTypeFitness = HouseFitness.types[house.type] ?: 0

            val houseLocationFitness = HouseFitness.locations[house.location] ?: 0


            return numberOfRoomsFitness + houseLocationFitness + houseTypeFitness
        }

        /**
         * This function will return the sum of the maximum fitness of each house's feature.
         *  It is considered as the goal fitness that will stop the Algorithm from working.
         * @author Ali Mansoura.
         */
        private fun getTheTargetFitness(): Int {
            //first we remove the unacceptable features
            val types = HouseFitness.types - ProhibitedFeatures.prohibitedTypes.toSet()
            val locations = HouseFitness.locations - ProhibitedFeatures.prohibitedLocations.toSet()
            val numberOfRooms = HouseFitness.numberOfRooms - ProhibitedFeatures.prohibitedNumberOfRooms.toSet()

            //then we got the maximum fitness of each feature
            val maxTypesFitness = types.maxOf { it.value }
            val maxLocationFitness = locations.maxOf { it.value }
            val maxNumberOfRoomsFitness = numberOfRooms.maxOf { it.value }

            return maxTypesFitness + maxLocationFitness + maxNumberOfRoomsFitness
        }

        /**
         * This function accepts a house feature like and mutate it
         * like location = TOWN then the function will return any random value of another location.
         * @author Ali Mansoura
         */
        fun mutateGene(feature: HouseFeature): HouseFeature {
            when (feature) {
                is HouseFeature.NumberOfRooms -> {
                    val selectedFeature = HouseFeature.NumberOfRooms.entries.random()
                    return selectedFeature
                }

                is HouseFeature.Location -> {
                    val selectedFeature = HouseFeature.Location.entries.random()
                    return selectedFeature
                }

                is HouseFeature.HouseType -> {
                    val selectedFeature = HouseFeature.HouseType.entries.random()
                    return selectedFeature
                }
            }

        }

        /**
         * This function will create one genome randomly.
         * @author Ali Ahmad
         */
        private fun createGenome(): Individual {
            val location = HouseFeature.Location.entries.random()
            val type = HouseFeature.HouseType.entries.random()
            val numberOfRooms = HouseFeature.NumberOfRooms.entries.random()

            return Individual(House(location = location, type = type, numberOfRooms = numberOfRooms))
        }

        /**
         * This function will create the initial generation of  individuals.
         * @author Ali Ahmad
         */
        private fun createInitialGeneration(): List<Individual> {
            val population = mutableListOf<Individual>()
            repeat(POPULATION_SIZE) {
                val individual = createGenome()
                population.add(individual)
            }
            return population
        }

        /**
         * This function will select the best individuals by their fitness.
         * @param generation this is the ordered list of individuals by their fitness.
         * @param percentage this is a  float value: 0.1 => 10%.
         * @author Ali Mansoura
         */
        private fun selection(generation: List<Individual>, percentage: Float = 0.1f): List<Individual> {
            val size: Int = (generation.size * percentage).toInt()
            return generation.subList(0, size)
        }

        fun runAlgorithm(): List<Individual> {
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
            return currentGeneration
        }
    }
}