package org.example.dataset

import com.example.geneticalgorithm.core.models.HouseFeature

/**
 * this is the initial dataset that represents the preferred customers houses.
 */
internal object InitialDataset {
    val houseTypes :Map<HouseFeature.HouseType, Int> = mapOf(
        HouseFeature.HouseType.DETACHED to 20,
        HouseFeature.HouseType.APARTMENT to 4,
        HouseFeature.HouseType.DELOX to 45,
        HouseFeature.HouseType.VILLA to 10,
    )
    val locations : Map<HouseFeature.Location, Int> = mapOf(
        HouseFeature.Location.CITY_EDGES to 43,
        HouseFeature.Location.CITY_CENTER to 45,
        HouseFeature.Location.SUBURB to 52,
        HouseFeature.Location.TOWN to 12,
        HouseFeature.Location.RURAL to 53,
    )
    val numberOfRooms: Map<HouseFeature.NumberOfRooms, Int> = mapOf(
        HouseFeature.NumberOfRooms.R1 to 20,
        HouseFeature.NumberOfRooms.R2 to 30,
        HouseFeature.NumberOfRooms.R3 to 50,
        HouseFeature.NumberOfRooms.R4 to 60,
        HouseFeature.NumberOfRooms.R5 to 20,
        HouseFeature.NumberOfRooms.R6 to 30,
        HouseFeature.NumberOfRooms.R7 to 10,
        HouseFeature.NumberOfRooms.R8 to 3,
    )
    val prohibitedNumberOfRooms = listOf(HouseFeature.NumberOfRooms.R7)
    val prohibitedLocations = listOf(HouseFeature.Location.RURAL)
    val prohibitedTypes = listOf(HouseFeature.HouseType.VILLA)
}