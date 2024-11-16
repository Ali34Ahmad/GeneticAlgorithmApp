package com.example.geneticalgorithm.core.ext

import com.example.geneticalgorithm.algorithm.HouseFeature

fun HouseFeature.NumberOfRooms.toAppropriateFormat(): String{
    return when{
        this==HouseFeature.NumberOfRooms.R1->"1 room"
        this==HouseFeature.NumberOfRooms.R2->"2 rooms"
        this==HouseFeature.NumberOfRooms.R3->"3 rooms"
        this==HouseFeature.NumberOfRooms.R4->"4 rooms"
        this==HouseFeature.NumberOfRooms.R5->"5 rooms"
        this==HouseFeature.NumberOfRooms.R6->"6 rooms"
        this==HouseFeature.NumberOfRooms.R7->"7 rooms"
        this==HouseFeature.NumberOfRooms.R8->"8 rooms"
        else -> {"None"}
    }
}