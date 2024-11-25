package com.example.geneticalgorithm.core.component.dialog

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.window.DialogProperties
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
    @StringRes buttonText: Int ,
    onSelectedAll: (Boolean) -> Unit,
    onCancel: () -> Unit,
    apply: () -> Unit,
    onItemSelected: (FilterItemData) -> Unit,
    items: List<FilterItemData>,
    segmentedButtons: List<String>,
    selectedOption: Int,
    onOptionSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    isSelectedAll: Boolean = false,
) {
    Dialog(onDismissRequest = onCancel) {
        Card(
            modifier = modifier,
            shape = RoundedCornerShape(MaterialTheme.sizing.medium24),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHigh)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.medium24)
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
                    selectedOption = selectedOption,
                    onOptionClick = onOptionSelected
                )
                Spacer(Modifier.height(MaterialTheme.spacing.medium16))
                //selected all
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .clickable {
                            onSelectedAll(!isSelectedAll)
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = stringResource(R.string.select_all),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.W700
                    )
                    Spacer(Modifier.weight(1f))
                    Checkbox(checked = isSelectedAll, onCheckedChange = { onSelectedAll(it) })
                }
                Spacer(Modifier.height(MaterialTheme.spacing.medium16))
                // filters section
                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                        .height(MaterialTheme.sizing.large146),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(
                        items.filter { it.type == selectedOption }
                    ) {
                        HorizontalDivider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = MaterialTheme.spacing.small8),
                            color = MaterialTheme.colorScheme.outlineVariant
                        )
                        FilterItem(
                            filterItemData = it,
                            onCheckedChanged = onItemSelected
                        )
                    }
                }
                // buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    TextButton(onClick = onCancel) {
                        Text(
                            text = stringResource(R.string.cancel),
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                    Spacer(Modifier.width(MaterialTheme.spacing.small8))
                    TextButton(onClick = apply) {
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
        val segmentedButtons = listOf("Location","Type","Rooms")
        var selectedOption by remember{
            mutableIntStateOf(0)
        }
        Surface {
            FilterDialog(
                buttonText =  R.string.apply,
                onOptionSelected = {
                    selectedOption = it
                },
                selectedOption = selectedOption,
                segmentedButtons = segmentedButtons,
                modifier = Modifier.padding(16.dp),
                title = R.string.customize_filters,
                subtitle = R.string.customize_filters_subtitle,
                isSelectedAll = selectedAll,
                onSelectedAll = {
                    selectedAll = it
                    for (i in 0 until items.size)
                        items[i] = items[i].copy(isChecked = it)
                },
                items = items,
                onItemSelected = {
                    val index = items.indexOf(it)
                    items[index] = it.copy(isChecked = !it.isChecked)
                    if(items.all{it.isChecked}){
                        selectedAll = true
                    }
                    if(items.any{!it.isChecked} && selectedAll){
                        selectedAll = false
                    }
                },
                onCancel = {
                    items.forEach{
                        it.isChecked = false
                    }
                },
                apply = {},
            )
        }

    }
}