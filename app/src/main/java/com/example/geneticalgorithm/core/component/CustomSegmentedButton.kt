package com.example.geneticalgorithm.core.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextOverflow
import com.example.geneticalgorithm.core.constants.SegmentedButtonOptions
import com.example.geneticalgorithm.presentation.ui.helper.DarkAndLightModePreview
import com.example.geneticalgorithm.presentation.ui.theme.GeneticAlgorithmTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSegmentedButton(
    options: List<String>,
    selectedOption: Int,
    onOptionClick:(index:Int)->Unit,
    modifier: Modifier = Modifier,
) {
    SingleChoiceSegmentedButtonRow(
        modifier=modifier
    ) {
        options.forEachIndexed { index, option ->
            SegmentedButton(
                onClick = { onOptionClick(index) },
                modifier = Modifier.fillMaxWidth(),
                selected = selectedOption == index,
                shape = getShapeByIndex(index, options.size),
            ) {
                Text(
                    text = option,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

fun getShapeByIndex(index: Int, numberOfOptions: Int): Shape {
    if (numberOfOptions == 1) return RoundedCornerShape(100f)
    if (index == 0) return RoundedCornerShape(
        topStart = 100f,
        bottomStart = 100f,
        topEnd = 0f,
        bottomEnd = 0f
    )
    if (index == numberOfOptions - 1) return RoundedCornerShape(
        topStart = 0f,
        bottomStart = 0f,
        topEnd = 100f,
        bottomEnd = 100f
    )
    return RectangleShape
}

@DarkAndLightModePreview
@Composable
fun CustomSegmentedButtonPreview() {
    var selectedOption by rememberSaveable { mutableStateOf(0) }
    GeneticAlgorithmTheme {
        Surface {
            CustomSegmentedButton(
                options = SegmentedButtonOptions.HouseFeatures,
                selectedOption = selectedOption,
                onOptionClick = { selectedOption = it },
            )
        }
    }
}