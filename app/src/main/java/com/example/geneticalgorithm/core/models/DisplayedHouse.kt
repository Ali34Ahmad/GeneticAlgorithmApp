package com.example.geneticalgorithm.core.models

import androidx.annotation.DrawableRes


data class DisplayedHouse(
    @DrawableRes val image: Int,
    val location: HouseFeature.Location,
    val type: HouseFeature.HouseType,
    val numberOfRooms: HouseFeature.NumberOfRooms,
){
    fun toHouse() = House(
        numberOfRooms = numberOfRooms,
        location = location,
        type = type
    )

}

