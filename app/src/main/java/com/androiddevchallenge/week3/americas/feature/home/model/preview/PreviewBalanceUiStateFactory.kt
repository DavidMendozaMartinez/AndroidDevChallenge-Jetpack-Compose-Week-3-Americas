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
package com.androiddevchallenge.week3.americas.feature.home.model.preview

import com.androiddevchallenge.week3.americas.R
import com.androiddevchallenge.week3.americas.feature.home.model.BalanceFilterUiState
import com.androiddevchallenge.week3.americas.feature.home.model.BalanceUiState
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import java.math.BigDecimal

object PreviewBalanceUiStateFactory {
    fun positive(
        total: BigDecimal = BigDecimal(73_589.01),
        change: Float = 412.35f,
        filters: ImmutableList<BalanceFilterUiState> = BalanceFilterUiState.entries.toImmutableList(),
        selectedFilter: BalanceFilterUiState? = null,
        chartResId: Int = R.drawable.home_illos,
    ): BalanceUiState = BalanceUiState(
        total = total,
        change = change,
        filters = filters,
        selectedFilter = selectedFilter,
        chartResId = chartResId,
    )
}
