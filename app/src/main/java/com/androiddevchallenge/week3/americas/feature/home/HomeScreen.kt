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

import android.app.Activity
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.activity.compose.LocalActivity
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.safeGestures
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.core.view.WindowInsetsControllerCompat
import com.androiddevchallenge.week3.americas.R
import com.androiddevchallenge.week3.americas.feature.home.component.Account
import com.androiddevchallenge.week3.americas.feature.home.component.PositionList
import com.androiddevchallenge.week3.americas.feature.home.model.AccountUiState
import com.androiddevchallenge.week3.americas.feature.home.model.BalanceFilterUiState
import com.androiddevchallenge.week3.americas.feature.home.model.HomeScreenUiState
import com.androiddevchallenge.week3.americas.feature.home.model.HomeTabUiState
import com.androiddevchallenge.week3.americas.feature.home.model.preview.PreviewBalanceUiStateFactory
import com.androiddevchallenge.week3.americas.feature.home.model.preview.PreviewPositionUiStateFactory
import com.androiddevchallenge.week3.americas.ui.theme.WeTradeTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
internal fun HomeScreen(
    uiState: HomeScreenUiState,
    onTransactClick: () -> Unit,
    onFilterClick: (BalanceFilterUiState) -> Unit,
    modifier: Modifier = Modifier,
) {
    val scope: CoroutineScope = rememberCoroutineScope()

    val tabs: ImmutableList<HomeTabUiState> = remember { HomeTabUiState.entries.toImmutableList() }
    val pagerState: PagerState = rememberPagerState(pageCount = { tabs.size })
    val isBottomSheetVisible: Boolean = tabs[pagerState.currentPage] == HomeTabUiState.ACCOUNT

    val scaffoldState: BottomSheetScaffoldState = rememberBottomSheetScaffoldState()
    val bottomSheetState: BottomSheetState = scaffoldState.bottomSheetState
    val bottomSheetProgress: Float = bottomSheetState.progress(from = BottomSheetValue.Collapsed, to = BottomSheetValue.Expanded)

    val windowInsets: WindowInsets = WindowInsets.safeDrawing.union(
        insets = WindowInsets.safeGestures.only(sides = WindowInsetsSides.Vertical),
    )
    val topInset: Dp = with(LocalDensity.current) { windowInsets.getTop(density = this).toDp() }
    val bottomInset: Dp = with(LocalDensity.current) { windowInsets.getBottom(density = this).toDp() }

    val sheetPeekHeight: Dp by animateDpAsState(
        targetValue = if (isBottomSheetVisible) SheetPeekBaseHeight + bottomInset else 0.dp,
    )

    if (MaterialTheme.colors.isLight) {
        SetStatusBarAppearanceEffect(isBottomSheetExpanded = bottomSheetState.isExpanded)
        ResetStatusBarAppearanceDisposeEffect()
    }

    BottomSheetScaffold(
        sheetContent = {
            if (isBottomSheetVisible) {
                Column {
                    BottomSheetPeek(progress = bottomSheetProgress, topPadding = topInset, bottomPadding = bottomInset)

                    PositionList(
                        items = uiState.accountUiState.positions,
                        modifier = Modifier.windowInsetsPadding(
                            insets = windowInsets.only(sides = WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom),
                        ),
                    )
                }
            }
        },
        modifier = modifier,
        scaffoldState = scaffoldState,
        sheetShape = RectangleShape,
        sheetElevation = 0.dp,
        sheetPeekHeight = sheetPeekHeight,
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(insets = windowInsets)
                .padding(paddingValues = contentPadding),
        ) {
            HomeTabRow(
                tabs = tabs,
                selectedTabIndex = pagerState.currentPage,
                onTabClick = { scope.launch { pagerState.animateScrollToPage(page = it) } },
            )

            HorizontalPager(
                state = pagerState,
                userScrollEnabled = false,
            ) { page ->
                when (tabs[page]) {
                    HomeTabUiState.ACCOUNT -> Account(
                        uiState = uiState.accountUiState,
                        onTransactClick = onTransactClick,
                        onFilterClick = onFilterClick,
                    )

                    HomeTabUiState.WATCHLIST -> Unit
                    HomeTabUiState.PROFILE -> Unit
                }
            }
        }
    }
}

@Composable
private fun BottomSheetPeek(
    progress: Float,
    topPadding: Dp,
    bottomPadding: Dp,
    modifier: Modifier = Modifier,
) {
    val expandedHeight: Dp = SheetPeekBaseHeight + topPadding
    val collapsedHeight: Dp = SheetPeekBaseHeight + bottomPadding
    val interpolatedHeight: Dp = lerp(start = collapsedHeight, stop = expandedHeight, fraction = progress)

    val alignmentVerticalBias: Float = -1f + 2f * progress
    val alignment = BiasAlignment(horizontalBias = 0f, verticalBias = alignmentVerticalBias)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height = interpolatedHeight),
        contentAlignment = alignment,
    ) {
        Text(
            text = stringResource(id = R.string.home_positions_title),
            modifier = Modifier.paddingFromBaseline(top = SheetPeekBaselinePaddingTop, bottom = SheetPeekBaselinePaddingBottom),
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.subtitle1,
        )
    }
}

@Composable
private fun HomeTabRow(
    tabs: ImmutableList<HomeTabUiState>,
    selectedTabIndex: Int,
    onTabClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val statusBarHeight: Dp = with(LocalDensity.current) { WindowInsets.statusBars.getTop(density = this).toDp() }

    Row(modifier = modifier) {
        tabs.forEachIndexed { index, uiState ->
            Tab(
                selected = index == selectedTabIndex,
                onClick = { onTabClick(index) },
                modifier = Modifier.weight(weight = 1f),
                selectedContentColor = MaterialTheme.colors.onBackground,
                unselectedContentColor = MaterialTheme.colors.onBackground.copy(alpha = 0.636f),
            ) {
                Text(
                    text = stringResource(id = uiState.displayNameResId).uppercase(),
                    modifier = Modifier.paddingFromBaseline(
                        top = (64.dp - statusBarHeight).coerceAtLeast(minimumValue = 24.dp),
                        bottom = 8.dp,
                    ),
                    style = MaterialTheme.typography.button,
                )
            }
        }
    }
}

@Composable
private fun SetStatusBarAppearanceEffect(isBottomSheetExpanded: Boolean) {
    val activity: Activity = LocalActivity.current ?: return
    val controller = WindowInsetsControllerCompat(activity.window, activity.window.decorView)

    LaunchedEffect(key1 = isBottomSheetExpanded) { controller.isAppearanceLightStatusBars = isBottomSheetExpanded }
}

@Composable
private fun ResetStatusBarAppearanceDisposeEffect() {
    val activity: Activity = LocalActivity.current ?: return
    val controller = WindowInsetsControllerCompat(activity.window, activity.window.decorView)

    val initialValue: Boolean = remember { controller.isAppearanceLightStatusBars }
    DisposableEffect(key1 = Unit) { onDispose { controller.isAppearanceLightStatusBars = initialValue } }
}

@Preview(name = "Light Theme", widthDp = 360, heightDp = 640, uiMode = UI_MODE_NIGHT_NO)
@Preview(name = "Dark Theme", widthDp = 360, heightDp = 640, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun HomeScreenPreview(
    @PreviewParameter(provider = HomeScreenPreviewParameterProvider::class) uiState: HomeScreenUiState,
) {
    WeTradeTheme {
        HomeScreen(
            uiState = uiState,
            onTransactClick = {},
            onFilterClick = {},
        )
    }
}

private class HomeScreenPreviewParameterProvider : PreviewParameterProvider<HomeScreenUiState> {
    override val values: Sequence<HomeScreenUiState>
        get() = sequenceOf(
            HomeScreenUiState(
                accountUiState = AccountUiState(
                    balanceUiState = PreviewBalanceUiStateFactory.positive(),
                    positions = List(size = 10) {
                        if (it % 2 == 0) PreviewPositionUiStateFactory.positive() else PreviewPositionUiStateFactory.negative()
                    }.toImmutableList(),
                ),
            ),
        )
}

private val SheetPeekBaselinePaddingTop: Dp = 40.dp
private val SheetPeekBaselinePaddingBottom: Dp = 24.dp
private val SheetPeekBaseHeight: Dp = SheetPeekBaselinePaddingTop + SheetPeekBaselinePaddingBottom
