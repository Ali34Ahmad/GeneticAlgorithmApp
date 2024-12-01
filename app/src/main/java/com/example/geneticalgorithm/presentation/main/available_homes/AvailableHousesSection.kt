package com.example.geneticalgorithm.presentation.main.available_homes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.geneticalgorithm.R
import com.example.geneticalgorithm.core.component.button.FilledButton
import com.example.geneticalgorithm.core.component.button.OutlinedButton
import com.example.geneticalgorithm.core.component.card.HouseCard
import com.example.geneticalgorithm.dataset.houses
import com.example.geneticalgorithm.presentation.main.CircleProgressBar
import com.example.geneticalgorithm.presentation.main.NoDataAvailable
import com.example.geneticalgorithm.presentation.ui.theme.GeneticAlgorithmTheme
import com.example.geneticalgorithm.presentation.ui.theme.sizing
import com.example.geneticalgorithm.presentation.ui.theme.spacing

@Composable
fun AvailableHousesSection(
    uiState: AvailableHousesUiState,
    onCustomizeFiltersButtonClick: () -> Unit,
    onClearFiltersButtonClick: () -> Unit,
    onBuyButtonClick: (index: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.houses),
            style = MaterialTheme.typography.titleMedium,
            modifier = modifier.padding(start = MaterialTheme.spacing.medium16),
        )
        if (uiState.isAvailableHousesLoading) {
            CircleProgressBar(
                size = MaterialTheme.sizing.large48,
                textId = R.string.loading_available_houses,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.medium24)
            )
        } else if (uiState.availableHouses.isEmpty()) {
            NoDataAvailable(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .align(Alignment.CenterHorizontally)
                    .padding(MaterialTheme.spacing.medium24),
                text = R.string.filter_blocking_all_results,
            )
        } else {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium16),
                contentPadding = PaddingValues(MaterialTheme.spacing.medium16),
            ) {
                items(uiState.availableHouses.size) {
                    HouseCard(
                        house = uiState.availableHouses[it],
                        onBuyButtonClick = { onBuyButtonClick(it) },
                    )
                }
            }
        }
        Spacer(Modifier.height(MaterialTheme.spacing.medium16))
        val horizontalArrangement =
            if (uiState.showClearFilters)
                Arrangement.spacedBy(MaterialTheme.spacing.medium16)
            else Arrangement.Center
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.medium16),
            horizontalArrangement =horizontalArrangement,
        ) {
            OutlinedButton(
                modifier = Modifier
                    .fillMaxWidth(0.5f),
                onClick = onCustomizeFiltersButtonClick,
                textId = R.string.customize_filters,
                isDisable = uiState.isAvailableHousesLoading,
            )
            if (uiState.showClearFilters) {
                FilledButton(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = onClearFiltersButtonClick,
                    textId = R.string.clear_filters,
                    isDisable = uiState.isAvailableHousesLoading,
                )
            }
        }


    }

}

@Preview(showBackground = true)
@Composable
fun HouseSectionPreview() {
    GeneticAlgorithmTheme {
        Surface {
            AvailableHousesSection(
                uiState = AvailableHousesUiState(
                    availableHouses = houses,
                    isAvailableHousesLoading = false,
                    showClearFilters = true,
                    showFilterSearchDialog = false,
                ),
                onCustomizeFiltersButtonClick = {},
                onBuyButtonClick = {},
                onClearFiltersButtonClick = {},
            )
        }
    }
}