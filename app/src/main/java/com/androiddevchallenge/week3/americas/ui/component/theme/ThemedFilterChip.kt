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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FilterChip
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.androiddevchallenge.week3.americas.ui.theme.WeTradeTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ThemedFilterChip(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null,
    leadingIconPainter: Painter? = null,
    selectedIconPainter: Painter? = null,
    trailingIconPainter: Painter? = null,
) {
    FilterChip(
        selected = selected,
        onClick = onClick,
        modifier = modifier.defaultMinSize(minWidth = MinWidth, minHeight = MinHeight),
        enabled = enabled,
        interactionSource = interactionSource,
        shape = MaterialTheme.shapes.large,
        border = BorderStroke(
            width = ChipDefaults.OutlinedBorderSize,
            color = MaterialTheme.colors.onBackground,
        ),
        colors = ChipDefaults.filterChipColors(
            backgroundColor = Color.Transparent,
            contentColor = MaterialTheme.colors.onBackground,
            selectedBackgroundColor = Color.Transparent,
            selectedContentColor = MaterialTheme.colors.primary,
        ),
        leadingIcon = iconOrNull(painter = leadingIconPainter, size = IconSize),
        selectedIcon = iconOrNull(painter = selectedIconPainter, size = IconSize),
        trailingIcon = iconOrNull(painter = trailingIconPainter, size = IconSize),
    ) {
        Text(
            text = text,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    paddingValues = contentPadding(
                        selected = selected,
                        hasLeadingIcon = leadingIconPainter != null,
                        hasSelectedIcon = selectedIconPainter != null,
                        hasTrailingIcon = trailingIconPainter != null,
                    ),
                ),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body1,
        )
    }
}

@Composable
private fun iconOrNull(
    painter: Painter?,
    size: Dp,
    contentDescription: String? = null,
): @Composable (() -> Unit)? = if (painter != null) {
    { Icon(painter = painter, contentDescription = contentDescription, modifier = Modifier.size(size = size)) }
} else {
    null
}

private fun contentPadding(
    selected: Boolean,
    hasLeadingIcon: Boolean,
    hasSelectedIcon: Boolean,
    hasTrailingIcon: Boolean,
): PaddingValues = PaddingValues(
    start = if (hasLeadingIcon || (selected && hasSelectedIcon)) 0.dp else 8.dp,
    end = if (hasTrailingIcon) 0.dp else 8.dp,
)

@Preview
@Composable
private fun ThemedFilterChipPreview() {
    WeTradeTheme {
        var selected: Boolean by remember { mutableStateOf(value = false) }

        ThemedFilterChip(
            text = LoremIpsum(words = 2).values.first(),
            selected = selected,
            onClick = { selected = !selected },
        )
    }
}

private val MinWidth: Dp = 72.dp
private val MinHeight: Dp = 40.dp
private val IconSize: Dp = 16.dp
