/*
 * Copyright 2025 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.androiddevchallenge.week3.americas.ui.component.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.androiddevchallenge.week3.americas.ui.theme.WeTradeTheme

@Composable
fun ThemedOutlinedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.defaultMinSize(minHeight = MinHeight),
        enabled = enabled,
        interactionSource = interactionSource,
        elevation = null,
        shape = MaterialTheme.shapes.large,
        border = BorderStroke(
            width = ButtonDefaults.OutlinedBorderSize,
            color = MaterialTheme.colors.primary,
        ),
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = Color.Transparent,
            contentColor = MaterialTheme.colors.primary,
            disabledContentColor = Color.Unspecified,
        ),
        contentPadding = contentPadding,
    ) {
        Text(
            text = text.uppercase(),
            style = MaterialTheme.typography.button,
        )
    }
}

@Preview
@Composable
private fun ThemedOutlinedButtonPreview() {
    WeTradeTheme {
        ThemedOutlinedButton(
            text = LoremIpsum(words = 3).values.first(),
            onClick = {},
        )
    }
}

private val MinHeight: Dp = 48.dp
