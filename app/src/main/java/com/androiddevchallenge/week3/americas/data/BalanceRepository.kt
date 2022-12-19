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

import com.androiddevchallenge.week3.americas.model.Balance
import java.math.BigDecimal

/**
 * Fake repository
 */
object BalanceRepositoy {
    fun getBalance(): Balance = Balance(
        total = BigDecimal(73_589.01),
        change = 412.35f
    )
}
