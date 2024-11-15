package com.example.geneticalgorithm.presentation.ui.helper

import androidx.compose.ui.graphics.Color
import com.example.geneticalgorithm.algorithm.House
import com.example.geneticalgorithm.algorithm.HouseFeature
import com.example.geneticalgorithm.algorithm.Individual
import com.example.geneticalgorithm.core.models.PieChartDetailsItem

val pieChartDetailsItems = listOf(
    PieChartDetailsItem(
        color = Color.Blue,
        name = "City Center",
        numberOfSales = 32
    ),
    PieChartDetailsItem(
        color = Color.Red,
        name = "City Edge",
        numberOfSales = 50
    ),
    PieChartDetailsItem(
        color = Color.Cyan,
        name = "Town",
        numberOfSales = 10
    ),
    PieChartDetailsItem(
        color = Color.Green,
        name = "Village",
        numberOfSales = 100
    ),
    PieChartDetailsItem(
        color = Color.Yellow,
        name = "Suburb",
        numberOfSales = 104
    ),
)

val generation = listOf(
    Individual(
        house = House(
            type = HouseFeature.HouseType.DELOX,
            location = HouseFeature.Location.SUBURB,
            numberOfRooms = HouseFeature.NumberOfRooms.R4,
        )
    ),
    Individual(
        house = House(
            type = HouseFeature.HouseType.VILLA,
            location = HouseFeature.Location.SUBURB,
            numberOfRooms = HouseFeature.NumberOfRooms.R6,
        )
    ),
    Individual(
        house = House(
            type = HouseFeature.HouseType.DELOX,
            location = HouseFeature.Location.CITY_CENTER,
            numberOfRooms = HouseFeature.NumberOfRooms.R3,
        )
    ),

    Individual(
        house = House(
            type = HouseFeature.HouseType.DELOX,
            location = HouseFeature.Location.CITY_CENTER,
            numberOfRooms = HouseFeature.NumberOfRooms.R3,
        )
    ),

    Individual(
        house = House(
            type = HouseFeature.HouseType.DELOX,
            location = HouseFeature.Location.CITY_CENTER,
            numberOfRooms = HouseFeature.NumberOfRooms.R3,
        )
    ),

    Individual(
        house = House(
            type = HouseFeature.HouseType.DELOX,
            location = HouseFeature.Location.CITY_CENTER,
            numberOfRooms = HouseFeature.NumberOfRooms.R3,
        )
    ),

    Individual(
        house = House(
            type = HouseFeature.HouseType.DELOX,
            location = HouseFeature.Location.CITY_CENTER,
            numberOfRooms = HouseFeature.NumberOfRooms.R3,
        )
    ),

    Individual(
        house = House(
            type = HouseFeature.HouseType.DELOX,
            location = HouseFeature.Location.CITY_CENTER,
            numberOfRooms = HouseFeature.NumberOfRooms.R3,
        )
    ),

    Individual(
        house = House(
            type = HouseFeature.HouseType.DELOX,
            location = HouseFeature.Location.CITY_CENTER,
            numberOfRooms = HouseFeature.NumberOfRooms.R3,
        )
    ),

)