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

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.androiddevchallenge.week3.americas.ui.theme.WeTradeTheme

@Composable
fun ThemedOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    placeholder: String? = null,
    leadingIconPainter: Painter? = null,
    trailingIconPainter: Painter? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource? = null,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = enabled,
        readOnly = readOnly,
        textStyle = MaterialTheme.typography.body1,
        label = null,
        placeholder = textOrNull(value = placeholder, style = MaterialTheme.typography.body1),
        leadingIcon = iconOrNull(painter = leadingIconPainter, size = LeadingIconSize),
        trailingIcon = iconOrNull(painter = trailingIconPainter, size = TrailingIconSize),
        isError = isError,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        interactionSource = interactionSource,
        shape = MaterialTheme.shapes.small,
        colors = TextFieldDefaults.outlinedTextFieldColors(textColor = MaterialTheme.colors.onSurface),
    )
}

@Composable
private fun textOrNull(
    value: String?,
    style: TextStyle,
): @Composable (() -> Unit)? = if (value != null) {
    { Text(text = value, style = style) }
} else {
    null
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

@Preview(showBackground = true)
@Composable
private fun ThemedOutlinedTextFieldPreview() {
    WeTradeTheme {
        var value: String by remember { mutableStateOf(value = "") }

        ThemedOutlinedTextField(
            value = value,
            onValueChange = { value = it },
            placeholder = LoremIpsum(words = 3).values.first(),
        )
    }
}

private val LeadingIconSize: Dp = 24.dp
private val TrailingIconSize: Dp = 24.dp
