package com.example.geneticalgorithm.presentation.ui.helper

import com.example.geneticalgorithm.core.models.House
import com.example.geneticalgorithm.core.models.HouseFeature
import com.example.geneticalgorithm.core.models.Individual
import com.example.geneticalgorithm.core.models.PieChartDetailsItem
import com.example.geneticalgorithm.presentation.ui.theme.blue
import com.example.geneticalgorithm.presentation.ui.theme.cyan
import com.example.geneticalgorithm.presentation.ui.theme.green
import com.example.geneticalgorithm.presentation.ui.theme.red
import com.example.geneticalgorithm.presentation.ui.theme.yellow

val pieChartDetailsItemsFake = listOf(
    PieChartDetailsItem(
        color = blue,
        name = "City Center",
        numberOfSales = 32
    ),
    PieChartDetailsItem(
        color = red,
        name = "City Edge",
        numberOfSales = 50
    ),
    PieChartDetailsItem(
        color = cyan,
        name = "Town",
        numberOfSales = 10
    ),
    PieChartDetailsItem(
        color = green,
        name = "Village",
        numberOfSales = 100
    ),
    PieChartDetailsItem(
        color = yellow,
        name = "Suburb",
        numberOfSales = 104
    ),
)

val generationFake = listOf(
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