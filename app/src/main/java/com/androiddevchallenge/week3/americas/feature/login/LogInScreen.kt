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
package com.androiddevchallenge.week3.americas.feature.login

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Password
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.androiddevchallenge.week3.americas.R
import com.androiddevchallenge.week3.americas.ui.component.theme.ThemedButton
import com.androiddevchallenge.week3.americas.ui.component.theme.ThemedOutlinedTextField
import com.androiddevchallenge.week3.americas.ui.theme.WeTradeTheme

@Composable
internal fun LogInScreen(
    onLogInClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var email by remember { mutableStateOf(value = "") }
    var password by remember { mutableStateOf(value = "") }

    Scaffold(
        contentWindowInsets = WindowInsets.safeDrawing
            .exclude(insets = WindowInsets.statusBars)
            .exclude(insets = WindowInsets.ime),
        backgroundColor = if (isSystemInDarkTheme()) MaterialTheme.colors.background else MaterialTheme.colors.surface,
        modifier = modifier,
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(state = rememberScrollState()),
        ) {
            Header()

            Column(
                modifier = Modifier
                    .padding(paddingValues = contentPadding)
                    .padding(top = 40.dp, bottom = 16.dp)
                    .padding(horizontal = 16.dp),
            ) {
                EmailTextField(
                    value = email,
                    onValueChange = { email = it },
                )

                PasswordTextField(
                    value = password,
                    onValueChange = { password = it },
                    modifier = Modifier.padding(top = 8.dp),
                )

                ThemedButton(
                    text = stringResource(id = R.string.log_in_button_log_in),
                    onClick = onLogInClick,
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .imePadding()
                        .fillMaxWidth(),
                )
            }
        }
    }
}

@Composable
private fun Header(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.login_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillBounds,
        )

        Text(
            text = stringResource(id = R.string.log_in_title),
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .paddingFromBaseline(top = 152.dp),
            color = MaterialTheme.colors.onBackground,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h2,
        )
    }
}

@Composable
private fun EmailTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    ThemedOutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        placeholder = stringResource(id = R.string.log_in_placeholder_email),
        leadingIconPainter = rememberVectorPainter(image = Icons.Default.MailOutline),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
    )
}

@Composable
private fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    ThemedOutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        placeholder = stringResource(id = R.string.log_in_placeholder_password),
        leadingIconPainter = rememberVectorPainter(image = Icons.Default.Password),
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
    )
}

@Preview(name = "Light Theme", uiMode = UI_MODE_NIGHT_NO)
@Preview(name = "Dark Theme", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun LogInScreenPreview() {
    WeTradeTheme {
        LogInScreen(
            onLogInClick = {},
        )
    }
}
