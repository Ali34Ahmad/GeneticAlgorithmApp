package com.example.geneticalgorithm.core.component.button

import androidx.annotation.StringRes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.geneticalgorithm.R
import com.example.geneticalgorithm.presentation.ui.helper.DarkAndLightModePreview
import com.example.geneticalgorithm.presentation.ui.theme.GeneticAlgorithmTheme

@Composable
fun OutlinedButton(
    onClick: () -> Unit,
    @StringRes textId: Int,
    modifier: Modifier = Modifier
) {
    androidx.compose.material3.OutlinedButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Text(text = stringResource(id = textId))
    }
}

@DarkAndLightModePreview
@Composable
fun OutlinedButtonPreview() {
    GeneticAlgorithmTheme {
        Surface {
            OutlinedButton(
                onClick = {},
                textId = R.string.advanced_search,
            )
        }
    }
}