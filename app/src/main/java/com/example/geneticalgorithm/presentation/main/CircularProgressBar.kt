package com.example.geneticalgorithm.presentation.main

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import com.example.geneticalgorithm.presentation.ui.theme.spacing

@Composable
fun CircleProgressBar(
    size: Dp,
    @StringRes textId: Int?,
    modifier: Modifier = Modifier,
    strokeWidth: Dp = ProgressIndicatorDefaults.CircularStrokeWidth,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(size = size),
            strokeWidth = strokeWidth,
        )
        textId?.let {
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium16))
            Text(
                text = stringResource(id = textId),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}