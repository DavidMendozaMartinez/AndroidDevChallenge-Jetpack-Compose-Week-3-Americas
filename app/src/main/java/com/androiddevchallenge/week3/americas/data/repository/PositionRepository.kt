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
package com.androiddevchallenge.week3.americas.data.repository

import com.androiddevchallenge.week3.americas.data.model.Position
import java.math.BigDecimal

interface PositionRepository {
    fun getPositions(): List<Position>
}

class FakePositionRepository : PositionRepository {
    override fun getPositions(): List<Position> = listOf(
        Position(
            ticker = "ALK",
            name = "Alaska Air Group, Inc.",
            price = BigDecimal(7_918),
            changePercentage = -0.54f,
        ),
        Position(
            ticker = "BA",
            name = "Boeing Co.",
            price = BigDecimal(1_293),
            changePercentage = 4.18f,
        ),
        Position(
            ticker = "DAL",
            name = "Delta Airlines Inc.",
            price = BigDecimal(893.50),
            changePercentage = -0.54f,
        ),
        Position(
            ticker = "EXPE",
            name = "Expedia Group Inc.",
            price = BigDecimal(12_301),
            changePercentage = 2.51f,
        ),
        Position(
            ticker = "EADSY",
            name = "Airbus SE",
            price = BigDecimal(12_301),
            changePercentage = 1.38f,
        ),
        Position(
            ticker = "JBLU",
            name = "Jetblue Airways Corp.",
            price = BigDecimal(8_521),
            changePercentage = 1.56f,
        ),
        Position(
            ticker = "MAR",
            name = "Marriott International Inc.",
            price = BigDecimal(521),
            changePercentage = 2.75f,
        ),
        Position(
            ticker = "CCL",
            name = "Carnival Corp",
            price = BigDecimal(5_481),
            changePercentage = 0.14f,
        ),
        Position(
            ticker = "RCL",
            name = "Royal Caribbean Cruises",
            price = BigDecimal(9_184),
            changePercentage = 1.69f,
        ),
        Position(
            ticker = "TRVL",
            name = "Travelocity Inc.",
            price = BigDecimal(654),
            changePercentage = 3.23f,
        ),
    )
}
