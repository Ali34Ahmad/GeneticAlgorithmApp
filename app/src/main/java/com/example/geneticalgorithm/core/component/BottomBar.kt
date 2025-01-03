package com.example.geneticalgorithm.core.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.geneticalgorithm.R
import com.example.geneticalgorithm.core.component.button.FilledButton
import com.example.geneticalgorithm.core.component.button.OutlinedButton
import com.example.geneticalgorithm.presentation.ui.theme.spacing

@Composable
fun BottomBar(
    onAdvancedSearchClick: () -> Unit,
    onRunAlgorithmClick: () -> Unit,
    modifier: Modifier = Modifier,
    isDisable:Boolean=false,
) {
    Box(modifier = modifier.background(MaterialTheme.colorScheme.surface)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = MaterialTheme.spacing.small8,
                    horizontal = MaterialTheme.spacing.medium16
                ),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium16)
        ) {
            OutlinedButton(
                onClick = onAdvancedSearchClick,
                textId = R.string.advanced_filter,
                modifier = Modifier.weight(1f),
                isDisable = isDisable
            )
            FilledButton(
                onClick = onRunAlgorithmClick,
                textId = R.string.run_algorithm,
                modifier = Modifier.weight(1f),
                isDisable=isDisable
            )

        }
    }
}