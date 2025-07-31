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
package com.androiddevchallenge.week3.americas.feature.welcome

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.androiddevchallenge.week3.americas.R
import com.androiddevchallenge.week3.americas.ui.component.custom.Logotype
import com.androiddevchallenge.week3.americas.ui.component.theme.ThemedButton
import com.androiddevchallenge.week3.americas.ui.component.theme.ThemedOutlinedButton
import com.androiddevchallenge.week3.americas.ui.theme.Gray900
import com.androiddevchallenge.week3.americas.ui.theme.WeTradeTheme

@Composable
internal fun WelcomeScreen(
    onSignUpClick: () -> Unit,
    onLogInClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        contentWindowInsets = WindowInsets.systemBars,
        modifier = modifier,
        backgroundColor = Gray900, // Not specified but it is the same for Light and Dark mode
        contentColor = Color.Unspecified,
    ) { contentPadding ->
        Background()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = contentPadding)
                .padding(all = 16.dp),
        ) {
            Logotype(modifier = Modifier.align(alignment = Alignment.Center))

            Row(
                modifier = Modifier
                    .align(alignment = Alignment.BottomCenter)
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(space = 8.dp),
            ) {
                ThemedButton(
                    text = stringResource(id = R.string.welcome_button_sign_up),
                    onClick = onSignUpClick,
                    modifier = Modifier.weight(weight = 1f),
                )

                ThemedOutlinedButton(
                    text = stringResource(id = R.string.welcome_button_log_in),
                    onClick = onLogInClick,
                    modifier = Modifier.weight(weight = 1f),
                )
            }
        }
    }
}

@Composable
private fun Background(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.welcome_bg),
        contentDescription = null,
        modifier = modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds,
    )
}

@Preview(name = "Light Theme", uiMode = UI_MODE_NIGHT_NO)
@Preview(name = "Dark Theme", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun WelcomeScreenPreview() {
    WeTradeTheme {
        WelcomeScreen(
            onSignUpClick = {},
            onLogInClick = {},
        )
    }
}
