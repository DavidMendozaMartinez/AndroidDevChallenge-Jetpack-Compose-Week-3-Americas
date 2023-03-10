/*
 * Copyright 2022 The Android Open Source Project
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
package com.androiddevchallenge.week3.americas.data

import com.androiddevchallenge.week3.americas.R
import com.androiddevchallenge.week3.americas.model.Position
import java.math.BigDecimal

/**
 * Fake repository
 */
object PositionRepositoy {
    fun getPositions(): List<Position> = listOf(
        Position(
            tickerSymbol = "ALK",
            name = "Alaska Air Group, Inc.",
            sparklineResId = R.drawable.home_alk,
            currentPrice = BigDecimal(7.918),
            percentageChange = -0.54f
        ),
        Position(
            tickerSymbol = "BA",
            name = "Boeing Co.",
            sparklineResId = R.drawable.home_ba,
            currentPrice = BigDecimal(1.293),
            percentageChange = 4.18f
        ),
        Position(
            tickerSymbol = "DAL",
            name = "Delta Airlines Inc.",
            sparklineResId = R.drawable.home_dal,
            currentPrice = BigDecimal(893.50),
            percentageChange = -0.54f
        ),
        Position(
            tickerSymbol = "EXPE",
            name = "Expedia Group Inc.",
            sparklineResId = R.drawable.home_exp,
            currentPrice = BigDecimal(12.301),
            percentageChange = 2.51f
        ),
        Position(
            tickerSymbol = "EADSY",
            name = "Airbus SE",
            sparklineResId = R.drawable.home_eadsy,
            currentPrice = BigDecimal(12.301),
            percentageChange = 1.38f
        ),
        Position(
            tickerSymbol = "JBLU",
            name = "Jetblue Airways Corp.",
            sparklineResId = R.drawable.home_jblu,
            currentPrice = BigDecimal(8.521),
            percentageChange = 1.56f
        )
    )
}
