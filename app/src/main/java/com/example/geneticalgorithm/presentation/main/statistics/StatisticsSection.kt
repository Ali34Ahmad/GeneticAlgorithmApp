package com.example.geneticalgorithm.presentation.main.statistics

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.geneticalgorithm.R
import com.example.geneticalgorithm.core.component.CustomSegmentedButton
import com.example.geneticalgorithm.core.component.pie_chart.PieChartWithDetails
import com.example.geneticalgorithm.core.constants.SegmentedButtonOptions
import com.example.geneticalgorithm.presentation.main.NoDataAvailable
import com.example.geneticalgorithm.presentation.ui.helper.DarkAndLightModePreview
import com.example.geneticalgorithm.presentation.ui.helper.pieChartDetailsItemsFake
import com.example.geneticalgorithm.presentation.ui.theme.GeneticAlgorithmTheme
import com.example.geneticalgorithm.presentation.ui.theme.spacing
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StatisticsSection(
    modifier: Modifier = Modifier,
    uiState: StatisticsUiState,
    onSegmentedButtonOptionClick: (Int) -> Unit,
) {
    val pieChartDetails = when (uiState.selectedOption) {
        0 -> uiState.pieChartLocationDetails
        1 -> uiState.pieChartTypeDetails
        else -> uiState.pieChartRoomsDetails
    }


    val pagerState = rememberPagerState(pageCount = { 3 })
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(pagerState.currentPage) {
        onSegmentedButtonOptionClick(pagerState.currentPage)
    }
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = R.string.statistics),
            style = MaterialTheme.typography.titleMedium,
        )
        Card(
            modifier = Modifier.padding(top = MaterialTheme.spacing.small8),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
            )
        ) {
            if (pieChartDetails.isEmpty()) {
                NoDataAvailable(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(MaterialTheme.spacing.medium24)
                )
            } else {
                Column {
                    CustomSegmentedButton(
                        options = SegmentedButtonOptions.statisticsOption,
                        selectedOption = uiState.selectedOption,
                        onOptionClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(it)
                                onSegmentedButtonOptionClick(pagerState.currentPage)
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = MaterialTheme.spacing.medium16,
                                vertical = MaterialTheme.spacing.medium24
                            ),
                    )
                    HorizontalPager(
                        state = pagerState,
                    ) {
                        PieChartWithDetails(
                            pieChartDetails = pieChartDetails,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    start = MaterialTheme.spacing.medium16,
                                    end = MaterialTheme.spacing.medium16,
                                    bottom = MaterialTheme.spacing.medium24
                                ),
                        )
                    }
                }
            }
        }
    }
}

@DarkAndLightModePreview
@Composable
fun StatisticsSectionPreview() {
    var selectedOption by remember { mutableIntStateOf(0) }
    GeneticAlgorithmTheme {
        Surface(tonalElevation = 1.dp) {
            StatisticsSection(
                uiState = StatisticsUiState(
                    selectedOption = selectedOption,
                    pieChartLocationDetails = pieChartDetailsItemsFake,
                    pieChartRoomsDetails = pieChartDetailsItemsFake,
                    pieChartTypeDetails = pieChartDetailsItemsFake,
                ),
                onSegmentedButtonOptionClick = { selectedOption = it },
                modifier = Modifier.padding(MaterialTheme.spacing.medium16),
            )
        }
    }
}