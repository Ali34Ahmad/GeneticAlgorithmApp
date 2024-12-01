package com.example.geneticalgorithm.core.component.check_box

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.geneticalgorithm.core.models.FilterItemData
import com.example.geneticalgorithm.presentation.ui.theme.GeneticAlgorithmTheme
import com.example.geneticalgorithm.presentation.ui.theme.sizing

@Composable
fun FilterItem(
    filterItemData: FilterItemData,
    onCheckedChanged: (FilterItemData) -> Unit,
    modifier: Modifier = Modifier,
    innerPadding:PaddingValues= PaddingValues(0.dp),
) {
    val paddingModifier=Modifier.padding(paddingValues = innerPadding)
    Row(
        modifier = modifier
            .clickable{
                onCheckedChanged(filterItemData)
            }
            .then(paddingModifier),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    ) {
        Text(
            text = filterItemData.text,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(Modifier.weight(1f))
        Text(
            text = filterItemData.number.toString(),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Checkbox(
            modifier = Modifier.size(MaterialTheme.sizing.medium40),
            checked = filterItemData.isChecked,
            onCheckedChange = { onCheckedChanged(filterItemData) },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FilterItemPreview() {
    GeneticAlgorithmTheme {
        Surface {
            val item = remember {
                mutableStateOf(FilterItemData(
                    text = "Village",
                    number = 40,
                    isChecked = false
                ))
            }
            FilterItem(
                modifier = Modifier.padding(24.dp),
                filterItemData = item.value,
                onCheckedChanged = {
                    item.value = it.copy(isChecked = !it.isChecked)
                }
            )
        }
    }
}