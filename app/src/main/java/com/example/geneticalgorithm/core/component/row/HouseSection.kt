package com.example.geneticalgorithm.core.component.row

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.geneticalgorithm.core.component.card.HouseCard
import com.example.geneticalgorithm.core.models.DisplayedHouse
import com.example.geneticalgorithm.presentation.ui.theme.spacing
import com.example.geneticalgorithm.R
import com.example.geneticalgorithm.algorithm.HouseFeature
import com.example.geneticalgorithm.presentation.ui.theme.GeneticAlgorithmTheme

@Composable
fun HouseSection(
    houses: List<DisplayedHouse>,
    onFilterButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(start = MaterialTheme.spacing.medium16)
    ) {
        Text(
            text = stringResource(R.string.houses),
            style = MaterialTheme.typography.titleMedium,
        )
        LazyRow(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium16)
        ) {
            items(houses){
                HouseCard(
                    house = it,
                    onBuyButtonClick = {},
                )
            }
        }
        Spacer(Modifier.height(MaterialTheme.spacing.medium24))
        OutlinedButton(
            modifier = Modifier.fillMaxWidth(0.5f).align(Alignment.CenterHorizontally),
            onClick = onFilterButtonClick
        ) {
            Text(
                text = stringResource(R.string.customize_filters)
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun HouseSectionPreview() {
    GeneticAlgorithmTheme {
        Surface {
            HouseSection(
                modifier = Modifier.padding(MaterialTheme.spacing.medium16),
                houses = listOf(
                    DisplayedHouse(
                        image = R.drawable.house,
                        location = HouseFeature.Location.CITY_CENTER,
                        type = HouseFeature.HouseType.VILLA,
                        numberOfRooms = HouseFeature.NumberOfRooms.R6,
                    ),DisplayedHouse(
                        image = R.drawable.house,
                        location = HouseFeature.Location.CITY_CENTER,
                        type = HouseFeature.HouseType.VILLA,
                        numberOfRooms = HouseFeature.NumberOfRooms.R6,
                    ),DisplayedHouse(
                        image = R.drawable.house,
                        location = HouseFeature.Location.CITY_CENTER,
                        type = HouseFeature.HouseType.VILLA,
                        numberOfRooms = HouseFeature.NumberOfRooms.R6,
                    ),DisplayedHouse(
                        image = R.drawable.house,
                        location = HouseFeature.Location.CITY_CENTER,
                        type = HouseFeature.HouseType.VILLA,
                        numberOfRooms = HouseFeature.NumberOfRooms.R6,
                    ),DisplayedHouse(
                        image = R.drawable.house,
                        location = HouseFeature.Location.CITY_CENTER,
                        type = HouseFeature.HouseType.VILLA,
                        numberOfRooms = HouseFeature.NumberOfRooms.R6,
                    )
                ),
                onFilterButtonClick = {}
            )
        }
    }
}