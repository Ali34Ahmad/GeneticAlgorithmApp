package org.example.dataset

import com.example.geneticalgorithm.core.models.HouseFeature


/**
 * This is where you can get the prohibited features.
 */
object ProhibitedFeatures{
    var prohibitedNumberOfRooms = InitialDataset.prohibitedNumberOfRooms
        private set
    var prohibitedLocations = InitialDataset.prohibitedLocations
        private set
    var prohibitedTypes = InitialDataset.prohibitedTypes
        private set

    fun updateProhibitedNumberOfRooms(list: List<HouseFeature.NumberOfRooms>){
        prohibitedNumberOfRooms = list
    }
    fun updateProhibitedLocations(list: List<HouseFeature.Location>){
        prohibitedLocations = list
    }
    fun updateProhibitedTypes(list: List<HouseFeature.HouseType>){
        prohibitedTypes = list
    }
}

















