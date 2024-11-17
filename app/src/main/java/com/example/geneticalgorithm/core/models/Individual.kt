package com.example.geneticalgorithm.core.models

import org.example.genetic_algorithm.GeneticAlgorithm
import kotlin.random.Random

data class Individual(
    var house: House
) : Comparable<Individual> {

    val fitness = GeneticAlgorithm.calculateFitness(house)

    /*We can now compare two individuals by their fitness.
      we will use it for sorting.
    */
    override fun compareTo(other: Individual): Int {
        return this.fitness.compareTo(other.fitness)
    }

    /**
     * This function will mate two individuals and returns an individual
     * @author Ali Ahmad
     */
    fun mate(secondParent: Individual): Individual {
        val locationPoints = Random.nextInt(100) // Random locationPoints point (0 to 100)
        val typePoints = Random.nextInt(100) // Random typePoints point (0 to 100)
        val numberOfRoomsPoints = Random.nextInt(100) // Random numberOfRoomsPoints point (0 to 100)

        val location = getLocationPoints(locationPoints, secondParent)
        val type = getTypePoints(typePoints, secondParent)
        val numberOfRooms = getNumberOfRoomsPoints(numberOfRoomsPoints, secondParent)

        return Individual(House(location = location, type = type, numberOfRooms = numberOfRooms))
    }

    private fun getLocationPoints(
        locationPoints: Int,
        secondParent: Individual
    ): HouseFeature.Location {
        return if (locationPoints < 45) {
            this.house.location
        } else if (locationPoints < 90) {
            secondParent.house.location
        } else {
            GeneticAlgorithm.mutateGene(this.house.location) as HouseFeature.Location
        }
    }

    private fun getTypePoints(
        houseTypePoints: Int,
        secondParent: Individual
    ): HouseFeature.HouseType {
        return if (houseTypePoints < 45) {
            this.house.type
        } else if (houseTypePoints < 90) {
            secondParent.house.type
        } else {
            GeneticAlgorithm.mutateGene(this.house.type) as HouseFeature.HouseType
        }
    }

    private fun getNumberOfRoomsPoints(
        numberOfRoomsPoints: Int,
        secondParent: Individual
    ): HouseFeature.NumberOfRooms {
        return if (numberOfRoomsPoints < 45) {
            this.house.numberOfRooms
        } else if (numberOfRoomsPoints < 90) {
            secondParent.house.numberOfRooms
        } else {
            GeneticAlgorithm.mutateGene(this.house.numberOfRooms) as HouseFeature.NumberOfRooms
        }
    }
}