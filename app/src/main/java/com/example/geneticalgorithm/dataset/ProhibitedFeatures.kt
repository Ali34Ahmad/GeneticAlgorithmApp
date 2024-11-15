package org.example.dataset


/**
 * This is where you can get the prohibited features.
 */
object ProhibitedFeatures{
    val prohibitedNumberOfRooms = InitialDataset.prohibitedNumberOfRooms.toMutableList()
    val prohibitedLocations = InitialDataset.prohibitedLocations.toMutableList()
    val prohibitedTypes = InitialDataset.prohibitedTypes.toMutableList()
}