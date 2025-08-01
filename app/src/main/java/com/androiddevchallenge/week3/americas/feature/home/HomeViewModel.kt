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
package com.androiddevchallenge.week3.americas.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.androiddevchallenge.week3.americas.data.repository.BalanceRepository
import com.androiddevchallenge.week3.americas.data.repository.FakeBalanceRepository
import com.androiddevchallenge.week3.americas.data.repository.FakePositionRepository
import com.androiddevchallenge.week3.americas.data.repository.PositionRepository
import com.androiddevchallenge.week3.americas.feature.home.model.AccountUiState
import com.androiddevchallenge.week3.americas.feature.home.model.HomeScreenUiState
import com.androiddevchallenge.week3.americas.feature.home.model.toBalanceUiState
import com.androiddevchallenge.week3.americas.feature.home.model.toPositionUiState
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel(
    private val balanceRepository: BalanceRepository,
    private val positionRepository: PositionRepository,
) : ViewModel() {
    val uiState: StateFlow<HomeScreenUiState> = MutableStateFlow(
        value = HomeScreenUiState(
            accountUiState = AccountUiState(
                balanceUiState = balanceRepository.getBalance().toBalanceUiState(selectedFilter = null),
                positions = positionRepository.getPositions().map { it.toPositionUiState() }.toImmutableList(),
            ),
        ),
    )

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T = HomeViewModel(
                balanceRepository = FakeBalanceRepository(),
                positionRepository = FakePositionRepository(),
            ) as T
        }
    }
}
