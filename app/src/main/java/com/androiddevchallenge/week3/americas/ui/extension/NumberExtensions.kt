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
package com.androiddevchallenge.week3.americas.ui.extension

import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

fun Float.format(
    pattern: String = "#,##0.##",
    explicitSign: Boolean = false,
    locale: Locale = Locale.getDefault(),
): String {
    val symbols = DecimalFormatSymbols(locale)
    val effectivePattern = if (explicitSign) "+$pattern;-#$pattern" else pattern
    return DecimalFormat(effectivePattern, symbols).format(this)
}

fun BigDecimal.format(
    pattern: String = "#,##0.##",
    locale: Locale = Locale.getDefault(),
): String {
    val symbols = DecimalFormatSymbols(locale)
    return DecimalFormat(pattern, symbols).format(this)
}
