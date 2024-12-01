package com.example.geneticalgorithm.core.component.button

import androidx.annotation.StringRes
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.geneticalgorithm.R
import com.example.geneticalgorithm.presentation.ui.helper.DarkAndLightModePreview
import com.example.geneticalgorithm.presentation.ui.theme.GeneticAlgorithmTheme

@Composable
fun FilledButton(
    onClick: () -> Unit,
    @StringRes textId: Int,
    modifier: Modifier = Modifier,
    isDisable:Boolean=false,
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        enabled = !isDisable,
    ) {
        Text(text = stringResource(id = textId))
    }
}

@DarkAndLightModePreview
@Composable
fun FilledButtonPreview(){
    GeneticAlgorithmTheme{
        Surface{
            FilledButton(onClick = {},
                textId = R.string.run_algorithm)
        }
    }
}