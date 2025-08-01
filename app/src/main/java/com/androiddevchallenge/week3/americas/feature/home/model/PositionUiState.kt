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

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.androiddevchallenge.week3.americas.R
import com.androiddevchallenge.week3.americas.data.model.Position
import com.androiddevchallenge.week3.americas.ui.extension.asPercentage
import com.androiddevchallenge.week3.americas.ui.extension.asUsd
import com.androiddevchallenge.week3.americas.ui.extension.format
import com.androiddevchallenge.week3.americas.ui.theme.custom1
import com.androiddevchallenge.week3.americas.ui.theme.custom2
import java.math.BigDecimal

data class PositionUiState(
    val ticker: String,
    val name: String,
    val price: BigDecimal,
    val changePercentage: Float,
    @DrawableRes val sparklineResId: Int,
) {
    private val formattedPrice: String = price.format()
    private val formattedChangePercentage: String = changePercentage.format(explicitSign = true)

    val displayedPrice: String
        @Composable get() = formattedPrice.asUsd()

    val displayedChangePercentage: String
        @Composable get() = formattedChangePercentage.asPercentage()

    val changePercentageColor: Color
        @Composable get() = if (changePercentage >= 0) MaterialTheme.colors.custom1 else MaterialTheme.colors.custom2

    // Non-Composable alternatives for use outside Compose (e.g. text measurement)
    fun getDisplayedPrice(context: Context) = formattedPrice.asUsd(context = context)

    fun getDisplayedChangePercentage(context: Context) = formattedChangePercentage.asPercentage(context = context)
}

fun Position.toPositionUiState(): PositionUiState = PositionUiState(
    ticker = ticker,
    name = name,
    price = price,
    changePercentage = changePercentage,
    sparklineResId = resolveSparklineResId(ticker = ticker),
)

@DrawableRes
private fun resolveSparklineResId(ticker: String): Int = when (ticker) {
    "ALK" -> R.drawable.home_alk
    "BA" -> R.drawable.home_ba
    "CCL" -> R.drawable.home_ccl
    "DAL" -> R.drawable.home_dal
    "EADSY" -> R.drawable.home_eadsy
    "EXPE" -> R.drawable.home_expe
    "JBLU" -> R.drawable.home_jblu
    "MAR" -> R.drawable.home_mar
    "RCL" -> R.drawable.home_rcl
    "TRVL" -> R.drawable.home_trvl
    else -> R.drawable.home_alk
}
