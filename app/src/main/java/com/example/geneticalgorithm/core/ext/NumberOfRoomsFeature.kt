package com.example.geneticalgorithm.core.ext

import androidx.compose.ui.res.stringResource
import com.example.geneticalgorithm.R
import com.example.geneticalgorithm.core.models.HouseFeature

fun HouseFeature.NumberOfRooms.toAppropriateFormat(): String{
    val numberOfRooms = this.toString().substringAfter("R")
    val numberOfRoomsText = if(this == HouseFeature.NumberOfRooms.R1)
        "$numberOfRooms Room"
    else "$numberOfRooms Rooms"
    return numberOfRoomsText
}


