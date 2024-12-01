package com.example.geneticalgorithm.dataset

import com.example.geneticalgorithm.R
import com.example.geneticalgorithm.core.models.DisplayedHouse
import com.example.geneticalgorithm.core.models.HouseFeature

val houses= listOf(
    DisplayedHouse(
        image = R.drawable.house,
        location = HouseFeature.Location.CITY_CENTER,
        type = HouseFeature.HouseType.VILLA,
        numberOfRooms = HouseFeature.NumberOfRooms.R6,
    ), DisplayedHouse(
        image = R.drawable.house,
        location = HouseFeature.Location.RURAL,
        type = HouseFeature.HouseType.DELOX,
        numberOfRooms = HouseFeature.NumberOfRooms.R3,
    ), DisplayedHouse(
        image = R.drawable.house,
        location = HouseFeature.Location.TOWN,
        type = HouseFeature.HouseType.DETACHED,
        numberOfRooms = HouseFeature.NumberOfRooms.R2,
    ), DisplayedHouse(
        image = R.drawable.house,
        location = HouseFeature.Location.CITY_CENTER,
        type = HouseFeature.HouseType.VILLA,
        numberOfRooms = HouseFeature.NumberOfRooms.R7,
    ), DisplayedHouse(
        image = R.drawable.house,
        location = HouseFeature.Location.CITY_EDGES,
        type = HouseFeature.HouseType.APARTMENT,
        numberOfRooms = HouseFeature.NumberOfRooms.R4,
    ), DisplayedHouse(
        image = R.drawable.house,
        location = HouseFeature.Location.SUBURB,
        type = HouseFeature.HouseType.APARTMENT,
        numberOfRooms = HouseFeature.NumberOfRooms.R3,
    ), DisplayedHouse(
        image = R.drawable.house,
        location = HouseFeature.Location.CITY_CENTER,
        type = HouseFeature.HouseType.DELOX,
        numberOfRooms = HouseFeature.NumberOfRooms.R4,
    ), DisplayedHouse(
        image = R.drawable.house,
        location = HouseFeature.Location.SUBURB,
        type = HouseFeature.HouseType.VILLA,
        numberOfRooms = HouseFeature.NumberOfRooms.R8,
    ),

)