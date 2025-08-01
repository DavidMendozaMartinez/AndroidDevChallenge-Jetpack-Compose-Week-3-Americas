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

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.androiddevchallenge.week3.americas.R
import com.androiddevchallenge.week3.americas.feature.home.model.BalanceFilterUiState
import com.androiddevchallenge.week3.americas.feature.home.model.BalanceUiState
import com.androiddevchallenge.week3.americas.feature.home.model.preview.PreviewBalanceUiStateFactory
import com.androiddevchallenge.week3.americas.ui.component.theme.ThemedButton
import com.androiddevchallenge.week3.americas.ui.component.theme.ThemedFilterChip
import com.androiddevchallenge.week3.americas.ui.theme.WeTradeTheme
import kotlinx.collections.immutable.ImmutableList

@Composable
fun Balance(
    uiState: BalanceUiState,
    onTransactClick: () -> Unit,
    onFilterClick: (BalanceFilterUiState) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = R.string.home_balance_title),
            modifier = Modifier
                .paddingFromBaseline(top = 32.dp, bottom = 8.dp)
                .padding(horizontal = 16.dp),
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.subtitle1,
        )

        Text(
            text = uiState.displayedTotal,
            modifier = Modifier
                .paddingFromBaseline(top = 48.dp, bottom = 24.dp)
                .padding(horizontal = 16.dp),
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.h1,
        )

        Text(
            text = stringResource(id = R.string.home_balance_today, formatArgs = arrayOf(uiState.displayedChange)),
            modifier = Modifier
                .paddingFromBaseline(top = 16.dp, bottom = 32.dp)
                .padding(horizontal = 16.dp),
            color = uiState.changeColor,
            style = MaterialTheme.typography.subtitle1,
        )

        ThemedButton(
            text = stringResource(id = R.string.home_button_transact),
            onClick = onTransactClick,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .widthIn(max = ContentMaxWidth)
                .fillMaxWidth(),
        )

        FilterList(
            items = uiState.filters,
            selected = uiState.selectedFilter,
            onItemClick = onFilterClick,
            contentPadding = PaddingValues(horizontal = 16.dp),
            modifier = Modifier.padding(top = 16.dp),
        )

        Image(
            painter = painterResource(id = uiState.chartResId),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 16.dp)
                .padding(horizontal = 16.dp)
                .widthIn(max = ContentMaxWidth)
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth,
        )
    }
}

@Composable
private fun FilterList(
    items: ImmutableList<BalanceFilterUiState>,
    selected: BalanceFilterUiState?,
    onItemClick: (BalanceFilterUiState) -> Unit,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier,
        contentPadding = contentPadding,
        horizontalArrangement = Arrangement.spacedBy(space = 8.dp),
    ) {
        items(items = items) { uiState ->
            ThemedFilterChip(
                text = stringResource(id = uiState.displayNameResId),
                selected = uiState == selected,
                onClick = { onItemClick(uiState) },
                trailingIconPainter = if (uiState.isExpandable) {
                    rememberVectorPainter(image = Icons.Default.ExpandMore)
                } else {
                    null
                },
            )
        }
    }
}

@Preview(name = "Light Theme", uiMode = UI_MODE_NIGHT_NO)
@Preview(name = "Dark Theme", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun BalancePreview(
    @PreviewParameter(provider = BalancePreviewParameterProvider::class) uiState: BalanceUiState,
) {
    WeTradeTheme {
        Balance(
            uiState = uiState,
            onTransactClick = {},
            onFilterClick = {},
        )
    }
}

private class BalancePreviewParameterProvider : PreviewParameterProvider<BalanceUiState> {
    override val values: Sequence<BalanceUiState>
        get() = sequenceOf(
            PreviewBalanceUiStateFactory.positive(),
        )
}

private val ContentMaxWidth: Dp = 500.dp
