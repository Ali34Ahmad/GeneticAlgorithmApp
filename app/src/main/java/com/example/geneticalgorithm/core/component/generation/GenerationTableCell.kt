package com.example.geneticalgorithm.core.component.generation

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import com.example.geneticalgorithm.presentation.ui.theme.sizing
import com.example.geneticalgorithm.presentation.ui.theme.spacing
import kotlin.math.max

@Composable
fun GenerationTableCell(
    text: String,
    modifier: Modifier = Modifier,
    textStyle: TextStyle=MaterialTheme.typography.bodyMedium,
) {
    Box(
        modifier = modifier
            .border(
                width = MaterialTheme.sizing.small11,
                color = MaterialTheme.colorScheme.outlineVariant,
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