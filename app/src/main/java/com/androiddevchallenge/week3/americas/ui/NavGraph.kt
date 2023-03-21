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
package com.androiddevchallenge.week3.americas.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.androiddevchallenge.week3.americas.ui.screen.home.Home
import com.androiddevchallenge.week3.americas.ui.screen.login.LogIn
import com.androiddevchallenge.week3.americas.ui.screen.welcome.Welcome

/**
 * Destinations used in the [WeTradeApp].
 */
enum class Destinations(val route: String) {
    WELCOME("welcome"),
    LOG_IN("logIn"),
    HOME("home")
}

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Destinations.WELCOME.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Destinations.WELCOME.route) {
            Welcome(
                onSignUpClicked = {},
                onLogInClicked = { navController.navigate(Destinations.LOG_IN.route) }
            )
        }
        composable(Destinations.LOG_IN.route) {
            LogIn(
                onLogInClicked = { _, _ -> navController.navigate(Destinations.HOME.route) }
            )
        }
        composable(Destinations.HOME.route) { Home() }
        
        composable(Destinations.SETTINGS.route) { }
    }
}
