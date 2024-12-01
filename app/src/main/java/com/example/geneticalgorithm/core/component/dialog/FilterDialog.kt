package com.example.geneticalgorithm.core.component.dialog

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.geneticalgorithm.R
import com.example.geneticalgorithm.core.component.CustomSegmentedButton
import com.example.geneticalgorithm.core.component.check_box.FilterItem
import com.example.geneticalgorithm.core.models.FilterItemData
import com.example.geneticalgorithm.presentation.ui.helper.getAllData
import com.example.geneticalgorithm.presentation.ui.theme.GeneticAlgorithmTheme
import com.example.geneticalgorithm.presentation.ui.theme.sizing
import com.example.geneticalgorithm.presentation.ui.theme.spacing


@Composable
fun FilterDialog(
    @StringRes title: Int,
    @StringRes subtitle: Int,
    @StringRes buttonText: Int,
    onSelectAllLocationCheckChange: (Boolean) -> Unit,
    onSelectAllTypeCheckChange: (Boolean) -> Unit,
    onSelectAllRoomsCheckChange: (Boolean) -> Unit,
    onCancelButtonClick: () -> Unit,
    onConfirmButtonClick: () -> Unit,
    onLocationItemSelected: (index:Int) -> Unit,
    onTypeItemSelected: (index:Int) -> Unit,
    onRoomsItemSelected: (index:Int) -> Unit,
    locationItems: List<FilterItemData>,
    typeItems: List<FilterItemData>,
    roomsItems: List<FilterItemData>,
    segmentedButtons: List<String>,
    segmentedButtonSelectedOption: Int,
    onSegmentedButtonOptionSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    selectAllLocationFilters: Boolean,
    selectAllTypeFilters: Boolean,
    selectAllRoomsFilters: Boolean,
) {
    val selectAllFilter= when (segmentedButtonSelectedOption) {
        0 -> selectAllLocationFilters
        1 -> selectAllTypeFilters
        2 -> selectAllRoomsFilters
        else -> selectAllLocationFilters
    }
    val onSelectAllCheckChange=when(segmentedButtonSelectedOption){
        0 -> onSelectAllLocationCheckChange
        1 -> onSelectAllTypeCheckChange
        2 -> onSelectAllRoomsCheckChange
        else -> onSelectAllLocationCheckChange
    }

    val onItemCheckChange=when(segmentedButtonSelectedOption){
        0 -> onLocationItemSelected
        1 -> onTypeItemSelected
        2 -> onRoomsItemSelected
        else -> onLocationItemSelected
    }

    val items=when(segmentedButtonSelectedOption){
        0 -> locationItems
        1 -> typeItems
        2 -> roomsItems
        else -> locationItems
    }




    Dialog(onDismissRequest = onCancelButtonClick) {
        Card(
            modifier = modifier,
            shape = RoundedCornerShape(MaterialTheme.sizing.medium24),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHigh)
        ) {
            Column {
                Column(
                    modifier = Modifier
                        .padding(
                            start = MaterialTheme.spacing.medium16,
                            end = MaterialTheme.spacing.medium16,
                            top = MaterialTheme.spacing.medium16,
                        )
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = title),
                        style = MaterialTheme.typography.headlineSmall,
                    )
                    Spacer(Modifier.height(MaterialTheme.spacing.medium16))
                    Text(
                        text = stringResource(subtitle),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(Modifier.height(MaterialTheme.spacing.medium24))
                    //segmented button row
                    CustomSegmentedButton(
                        modifier = Modifier.fillMaxWidth(),
                        options = segmentedButtons,
                        selectedOption = segmentedButtonSelectedOption,
                        onOptionClick = onSegmentedButtonOptionSelected
                    )
                    Spacer(Modifier.height(MaterialTheme.spacing.small8))
                }

                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onSelectAllCheckChange(!selectAllLocationFilters)
                            }
                            .padding(horizontal = MaterialTheme.spacing.medium16),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            text = stringResource(R.string.select_all),
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.W700
                        )
                        Spacer(Modifier.weight(1f))
                        Checkbox(checked = selectAllFilter, onCheckedChange = { onSelectAllCheckChange(it) })
                    }
                    // filters section
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(MaterialTheme.sizing.large146),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        items(
                            items.size
                        ) {index->

                            HorizontalDivider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        horizontal = MaterialTheme.spacing.medium16
                                    ),
                                color = MaterialTheme.colorScheme.outlineVariant
                            )
                            FilterItem(
                                filterItemData = items[index],
                                onCheckedChanged = { onItemCheckChange(index) },
                                innerPadding = PaddingValues(
                                    horizontal = MaterialTheme.spacing.medium16,
                                    vertical = MaterialTheme.spacing.small8
                                ),
                            )
                        }
                    }
                    // buttons
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(MaterialTheme.spacing.medium16),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        TextButton(onClick = onCancelButtonClick) {
                            Text(
                                text = stringResource(R.string.cancel),
                                style = MaterialTheme.typography.labelLarge
                            )
                        }
                        Spacer(Modifier.width(MaterialTheme.spacing.small8))
                        TextButton(onClick = onConfirmButtonClick) {
                            Text(
                                text = stringResource(buttonText),
                                style = MaterialTheme.typography.labelLarge
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FilterDialogPreview() {
    val data = getAllData()
    GeneticAlgorithmTheme {
        var selectedAll by remember {
            mutableStateOf(false)
        }
        val items = remember {
            data.toMutableStateList()
        }
        val segmentedButtons = listOf("Location", "Type", "Rooms")
        var selectedOption by remember {
            mutableIntStateOf(0)
        }
        Surface {
            FilterDialog(
                buttonText = R.string.apply,
                onSegmentedButtonOptionSelected = {
                    selectedOption = it
                },
                segmentedButtonSelectedOption = selectedOption,
                segmentedButtons = segmentedButtons,
                modifier = Modifier.padding(16.dp),
                title = R.string.customize_filters,
                subtitle = R.string.customize_filters_subtitle,
                selectAllLocationFilters = selectedAll,
                locationItems = items,
                onLocationItemSelected = {
//                    val index = items.indexOf(it)
//                    items[index] = it.copy(isChecked = !it.isChecked)
//                    if (items.all { it.isChecked }) {
//                        selectedAll = true
//                    }
//                    if (items.any { !it.isChecked } && selectedAll) {
//                        selectedAll = false
//                    }
                },
                onCancelButtonClick = {
                    items.forEach {
                        it.isChecked = false
                    }
                },
                onConfirmButtonClick = {},
                selectAllTypeFilters = true,
                selectAllRoomsFilters = true,
                onSelectAllLocationCheckChange={
                    selectedAll = it
                    for (i in 0 until items.size)
                        items[i] = items[i].copy(isChecked = it)
                },
                onSelectAllTypeCheckChange={},
                onSelectAllRoomsCheckChange={},
                onRoomsItemSelected = {},
                onTypeItemSelected = {},
                roomsItems = data,
                typeItems = data,
            )
        }

    }
}