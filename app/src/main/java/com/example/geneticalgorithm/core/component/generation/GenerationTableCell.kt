package com.example.geneticalgorithm.core.component.generation

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import com.example.geneticalgorithm.presentation.ui.theme.sizing
import com.example.geneticalgorithm.presentation.ui.theme.spacing

@Composable
fun GenerationTableCell(
    text: String,
    modifier: Modifier = Modifier,
    borderShape:Shape= RectangleShape,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
) {
    Box(
        modifier = modifier
            .border(
                width = MaterialTheme.sizing.small11,
                color = MaterialTheme.colorScheme.outlineVariant,
                shape = borderShape,
            )
    ) {
        Text(
            text = text,
            style = textStyle,
            modifier = Modifier.padding(MaterialTheme.spacing.small12),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
        )
    }
}