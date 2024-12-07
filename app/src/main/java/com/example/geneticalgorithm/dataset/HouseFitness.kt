package org.example.dataset

import com.example.geneticalgorithm.core.models.House
import com.example.geneticalgorithm.core.models.HouseFeature

/**
 * This class will show the current statistics about house types,
 * locations and number of rooms by house purchase.
 * @author Ali Mansoura
 */
object HouseFitness {
    val types : MutableMap<HouseFeature.HouseType, Int> = InitialDataset.houseTypes
            as MutableMap<HouseFeature.HouseType, Int>

    val locations : MutableMap<HouseFeature.Location, Int> = InitialDataset.locations
            as MutableMap<HouseFeature.Location, Int>

    val numberOfRooms: MutableMap<HouseFeature.NumberOfRooms, Int> = InitialDataset.numberOfRooms
            as MutableMap<HouseFeature.NumberOfRooms, Int>

    fun updateFitness(house: House){
        val numberOfHouseRooms = house.numberOfRooms
        val houseLocation = house.location
        val houseType = house.type

        types[houseType] = types[houseType]?.plus(1)?:1
        numberOfRooms[numberOfHouseRooms] = numberOfRooms[numberOfHouseRooms]?.plus(1)?:1
        locations[houseLocation] = locations[houseLocation]?.plus(1)?:1
    }
}

fun main() {
    val house = House(HouseFeature.NumberOfRooms.R6, HouseFeature.Location.CITY_EDGES, HouseFeature.HouseType.APARTMENT)
    HouseFitness.updateFitness(house)
    println(HouseFitness.types[HouseFeature.HouseType.APARTMENT])//5
    println(HouseFitness.numberOfRooms[HouseFeature.NumberOfRooms.R6])//31
    println(HouseFitness.locations[HouseFeature.Location.CITY_EDGES])//4
}



