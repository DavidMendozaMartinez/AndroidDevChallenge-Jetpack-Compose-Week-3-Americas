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
package com.androiddevchallenge.week3.americas.feature.home.component

import android.content.Context
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.androiddevchallenge.week3.americas.feature.home.model.PositionUiState
import com.androiddevchallenge.week3.americas.feature.home.model.preview.PreviewPositionUiStateFactory
import com.androiddevchallenge.week3.americas.ui.theme.WeTradeTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
fun PositionList(
    items: ImmutableList<PositionUiState>,
    modifier: Modifier = Modifier,
) {
    val numbersRequiredMinWidth: Dp = calculateNumbersRequiredMinWidth(items = items)

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.surface),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 16.dp),
    ) {
        items(items = items) { uiState ->
            Column {
                Divider(color = MaterialTheme.colors.onSurface.copy(alpha = DividerAlpha))
                Position(uiState = uiState, numbersRequiredMinWidth = numbersRequiredMinWidth)
            }
        }
    }
}

@Composable
private fun Position(
    uiState: PositionUiState,
    numbersRequiredMinWidth: Dp,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(modifier = Modifier.defaultMinSize(minWidth = maxOf(NumbersDefaultMinWidth, numbersRequiredMinWidth))) {
            Text(
                text = uiState.displayedPrice,
                modifier = Modifier.paddingFromBaseline(top = 24.dp),
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.typography.body1,
            )

            Text(
                text = uiState.displayedChangePercentage,
                modifier = Modifier.paddingFromBaseline(top = 16.dp, bottom = 16.dp),
                color = uiState.changePercentageColor,
                style = MaterialTheme.typography.body1,
            )
        }

        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .weight(weight = 1f),
        ) {
            Text(
                text = uiState.ticker,
                modifier = Modifier.paddingFromBaseline(top = 24.dp),
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.typography.h3,
            )

            Text(
                text = uiState.name,
                modifier = Modifier.paddingFromBaseline(top = 16.dp, bottom = 16.dp),
                color = MaterialTheme.colors.onSurface,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = MaterialTheme.typography.body1,
            )
        }

        Image(
            painter = painterResource(id = uiState.sparklineResId),
            contentDescription = null,
            modifier = Modifier.padding(start = 8.dp),
        )
    }
}

@Composable
private fun calculateNumbersRequiredMinWidth(items: ImmutableList<PositionUiState>): Dp {
    val context: Context = LocalContext.current
    val density: Density = LocalDensity.current

    val textMeasurer: TextMeasurer = rememberTextMeasurer()
    val style: TextStyle = MaterialTheme.typography.body1

    val maxWidthPx: Int = remember(key1 = items) {
        val numbers: List<String> = items.flatMap {
            listOf(it.getDisplayedPrice(context = context), it.getDisplayedChangePercentage(context = context))
        }
        numbers.maxOf { textMeasurer.measure(text = it, style = style).size.width }
    }
    return with(density) { maxWidthPx.toDp() }
}

@Preview(name = "Light Theme", uiMode = UI_MODE_NIGHT_NO)
@Preview(name = "Dark Theme", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun PositionListPreview(
    @PreviewParameter(provider = PositionListPreviewParameterProvider::class) items: ImmutableList<PositionUiState>,
) {
    WeTradeTheme {
        PositionList(
            items = items,
        )
    }
}

private class PositionListPreviewParameterProvider : PreviewParameterProvider<ImmutableList<PositionUiState>> {
    override val values: Sequence<ImmutableList<PositionUiState>>
        get() = sequenceOf(
            List(size = 10) {
                if (it % 2 == 0) PreviewPositionUiStateFactory.positive() else PreviewPositionUiStateFactory.negative()
            }.toImmutableList(),
        )
}

private val NumbersDefaultMinWidth: Dp = 56.dp
private val DividerAlpha: Float
    @Composable get() = if (MaterialTheme.colors.isLight) 0.436f else 0.543f
