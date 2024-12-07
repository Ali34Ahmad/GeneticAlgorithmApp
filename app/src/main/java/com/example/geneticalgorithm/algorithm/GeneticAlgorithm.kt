package org.example.genetic_algorithm

import com.example.geneticalgorithm.core.models.House
import com.example.geneticalgorithm.core.models.HouseFeature
import com.example.geneticalgorithm.core.models.Individual
import org.example.dataset.HouseFitness
import org.example.dataset.ProhibitedFeatures

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
            if (house.containsProhibitedFeatures())
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
        fun getTheTargetFitness(

        ): Int {
            //first we remove the unacceptable features
            val types = HouseFitness.types - ProhibitedFeatures.prohibitedTypes.toSet()
            val locations = HouseFitness.locations - ProhibitedFeatures.prohibitedLocations.toSet()
            val numberOfRooms = HouseFitness.numberOfRooms - ProhibitedFeatures.prohibitedNumberOfRooms.toSet()

            //then we got the maximum fitness of each feature
            val maxTypesFitness = types.maxOfOrNull { it.value }?:0
            val maxLocationFitness = locations.maxOfOrNull { it.value }?:0
            val maxNumberOfRoomsFitness = numberOfRooms.maxOfOrNull { it.value }?:0

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

            return Individual(
                House(
                    location = location,
                    type = type,
                    numberOfRooms = numberOfRooms
                )
            )
        }

        /**
         * This function will create the initial generation of  individuals.
         * @author Ali Ahmad
         */
        fun createInitialGeneration(): List<Individual> {
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
        fun selection(generation: List<Individual>, percentage: Float = 0.1f): List<Individual> {
            val size: Int = (generation.size * percentage).toInt()
            return generation.subList(0, size)
        }
    }
}