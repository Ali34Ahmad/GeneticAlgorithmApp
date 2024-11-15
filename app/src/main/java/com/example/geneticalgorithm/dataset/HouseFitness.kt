package org.example.dataset

import com.example.geneticalgorithm.algorithm.HouseFeature

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
}



