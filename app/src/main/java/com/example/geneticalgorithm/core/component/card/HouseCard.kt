package com.example.geneticalgorithm.core.component.card

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.geneticalgorithm.R
import com.example.geneticalgorithm.core.models.DisplayedHouse
import com.example.geneticalgorithm.core.models.HouseFeature
import com.example.geneticalgorithm.presentation.ui.theme.GeneticAlgorithmTheme
import com.example.geneticalgorithm.presentation.ui.theme.primaryTextColorLight
import com.example.geneticalgorithm.presentation.ui.theme.sizing
import com.example.geneticalgorithm.presentation.ui.theme.spacing

@Composable
fun HouseCard(
    house: DisplayedHouse,
    onBuyButtonClick: (DisplayedHouse) -> Unit,
    modifier: Modifier = Modifier
) {
    var disabled = remember{
        mutableStateOf(false)
    }
    val cardAlpha = animateFloatAsState(
            targetValue = if (disabled.value) 0.7f else 1f,
            label = ""
    )
    val cardElevation = animateDpAsState(
        targetValue = if(disabled.value) 0.dp else MaterialTheme.spacing.small2, label = ""
    )
    ElevatedCard(
        modifier = modifier
            .width(MaterialTheme.sizing.large170)
            .height(MaterialTheme.sizing.large238)
            .graphicsLayer {
                alpha = cardAlpha.value
            }
        ,
        shape = RoundedCornerShape(MaterialTheme.spacing.small8),
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color(0xFBFBFFFF),
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = cardElevation.value
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.55f),
                painter = painterResource(house.image),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = MaterialTheme.spacing.small4,
                        horizontal = MaterialTheme.spacing.small8
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val numberOfRooms = house.numberOfRooms.toString().substringAfter("R")
                val numberOfRoomsText = if(house.numberOfRooms == HouseFeature.NumberOfRooms.R1)
                    numberOfRooms + stringResource(R.string.room)
                else numberOfRooms + stringResource(R.string.rooms)

                Text(
                    house.location.toString(),
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.labelSmall.fontSize,
                        fontWeight = FontWeight.W500,
                        color = primaryTextColorLight
                    ),

                )
                Text(
                    text = numberOfRoomsText,
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.bodySmall.fontSize,
                        fontWeight = FontWeight.W400
                    ),
                    color = Color(0xB2B2B2FF),
                )
            }
            Text(
                house.type.toString(),
                modifier = Modifier.padding(start = MaterialTheme.spacing.small8),
                style = TextStyle(
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    fontWeight = FontWeight.W400,
                    color = Color(0x2F2E30FF)
                ),
            )
            Spacer(Modifier.height(MaterialTheme.spacing.medium16))
            Button(
                enabled = !disabled.value,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(MaterialTheme.sizing.medium40)
                    .padding(horizontal = MaterialTheme.spacing.small8),
                onClick = {
                        onBuyButtonClick(house)
                    disabled.value = true
                          },
            ) {
                Text(
                    text = stringResource(R.string.buy_now),
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.labelLarge.fontSize,
                        fontWeight = FontWeight.W500
                    )
                )
            }

        }

    }
}

@Preview(showBackground = true)
@Composable
fun HouseCardPreview() {
    GeneticAlgorithmTheme {
        Surface {
            HouseCard(
                modifier = Modifier.padding(24.dp),
                house = DisplayedHouse(
                    image = R.drawable.house,
                    location = HouseFeature.Location.CITY_CENTER,
                    type = HouseFeature.HouseType.VILLA,
                    numberOfRooms = HouseFeature.NumberOfRooms.R6,
                ),
                onBuyButtonClick = { }
            )
        }
    }
}