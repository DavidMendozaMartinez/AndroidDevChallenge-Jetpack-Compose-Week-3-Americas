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
package com.androiddevchallenge.week3.americas.feature.home.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.androiddevchallenge.week3.americas.R
import com.androiddevchallenge.week3.americas.data.model.Balance
import com.androiddevchallenge.week3.americas.ui.extension.asUsd
import com.androiddevchallenge.week3.americas.ui.extension.format
import com.androiddevchallenge.week3.americas.ui.theme.custom1
import com.androiddevchallenge.week3.americas.ui.theme.custom2
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import java.math.BigDecimal

data class BalanceUiState(
    val total: BigDecimal,
    val change: Float,
    val filters: ImmutableList<BalanceFilterUiState>,
    @DrawableRes val chartResId: Int,
) {
    val formattedTotal: String
        @Composable get() = total.format().asUsd()

    val formattedChange: String
        @Composable get() = change.format(explicitSign = true)

    val changeColor: Color
        @Composable get() = if (change >= 0) MaterialTheme.colors.custom1 else MaterialTheme.colors.custom2
}

enum class BalanceFilterUiState(
    @StringRes val displayNameResId: Int,
    val isExpandable: Boolean = false,
) {
    WEEK(displayNameResId = R.string.home_filter_week, isExpandable = true),
    ETFS(displayNameResId = R.string.home_filter_etfs),
    STOCKS(displayNameResId = R.string.home_filter_stocks),
    FUNDS(displayNameResId = R.string.home_filter_funds),
}

fun Balance.toBalanceUiState(): BalanceUiState = BalanceUiState(
    total = total,
    change = change,
    filters = BalanceFilterUiState.entries.toImmutableList(),
    chartResId = R.drawable.home_illos,
)
