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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.androiddevchallenge.week3.americas.feature.home.model.AccountUiState
import com.androiddevchallenge.week3.americas.feature.home.model.BalanceFilterUiState
import com.androiddevchallenge.week3.americas.feature.home.model.preview.PreviewBalanceUiStateFactory
import com.androiddevchallenge.week3.americas.ui.theme.WeTradeTheme
import kotlinx.collections.immutable.persistentListOf

@Composable
fun Account(
    uiState: AccountUiState,
    onTransactClick: () -> Unit,
    onFilterClick: (BalanceFilterUiState) -> Unit,
    modifier: Modifier = Modifier,
) {
    Balance(
        uiState = uiState.balanceUiState,
        onTransactClick = onTransactClick,
        onFilterClick = onFilterClick,
        modifier = modifier.verticalScroll(state = rememberScrollState()),
    )
}

@Preview(name = "Light Theme", uiMode = UI_MODE_NIGHT_NO)
@Preview(name = "Dark Theme", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun AccountPreview(
    @PreviewParameter(provider = AccountPreviewParameterProvider::class) uiState: AccountUiState,
) {
    WeTradeTheme {
        Account(
            uiState = uiState,
            onTransactClick = {},
            onFilterClick = {},
        )
    }
}

private class AccountPreviewParameterProvider : PreviewParameterProvider<AccountUiState> {
    override val values: Sequence<AccountUiState>
        get() = sequenceOf(
            AccountUiState(
                balanceUiState = PreviewBalanceUiStateFactory.positive(),
                positions = persistentListOf(),
            ),
        )
}
