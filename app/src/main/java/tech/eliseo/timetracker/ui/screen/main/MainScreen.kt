/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tech.eliseo.timetracker.ui.screen.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DonutLarge
import androidx.compose.material.icons.filled.LocalOffer
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.enterAlwaysScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle.State.STARTED
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import tech.eliseo.timetracker.R
import tech.eliseo.timetracker.domain.model.Category
import tech.eliseo.timetracker.domain.model.CurrentTracking
import tech.eliseo.timetracker.domain.model.TrackedSlot
import tech.eliseo.timetracker.ui.coponents.*
import tech.eliseo.timetracker.ui.formatter.toContentRowHolder
import tech.eliseo.timetracker.ui.preview.FakePreviewData
import tech.eliseo.timetracker.ui.theme.MyApplicationTheme
import java.time.LocalDate
import java.util.*

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val state by produceState<TrackedSlotUiState>(
        initialValue = TrackedSlotUiState.Loading,
        key1 = lifecycle,
        key2 = viewModel
    ) {
        lifecycle.repeatOnLifecycle(state = STARTED) {
            viewModel.uiState.collect { value = it }
        }
    }
    if (state is TrackedSlotUiState.Success) {
        MainScreen(
            modifier = modifier,
            trackedSlots = (state as TrackedSlotUiState.Success).todayTrackedSlot,
            currentTracking = (state as TrackedSlotUiState.Success).trackStartDate,
            categoryList = (state as TrackedSlotUiState.Success).categoryList,
            onTapTracker = { viewModel.onUiEvent(TrackedSlotUiEvent.OnTrackedClicked) },
            onCategoryAssigned = { trackedSlot, category ->
                viewModel.onUiEvent(TrackedSlotUiEvent.OnCategoryAssigned(trackedSlot, category))
            },
            onCategoryButtonClicked = {
                navController.navigate("category_list")
            },
            onHistoryButtonClicked = {
                navController.navigate("analytics")
            },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MainScreen(
    modifier: Modifier = Modifier,
    currentTracking: CurrentTracking = CurrentTracking.Stopped,
    trackedSlots: List<TrackedSlot> = emptyList(),
    categoryList: List<Category> = emptyList(),
    onTapTracker: () -> Unit = {},
    onCategoryAssigned: (TrackedSlot, Category) -> Unit = { _, _ -> },
    onCategoryButtonClicked: () -> Unit = {},
    onHistoryButtonClicked: () -> Unit = {},
) {

    val scroll = rememberScrollState()

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        stringResource(id = R.string.app_name),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                scrollBehavior = enterAlwaysScrollBehavior(),
                actions = {
                    IconButton(onClick = onCategoryButtonClicked) {
                        Icon(
                            imageVector = Icons.Filled.LocalOffer,
                            contentDescription = "Localized description"
                        )
                    }
                    IconButton(onClick = onHistoryButtonClicked) {
                        Icon(
                            imageVector = Icons.Filled.DonutLarge,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
        },
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .verticalScroll(scroll)
                .padding(16.dp)
        ) {
            MainButton(
                modifier = Modifier.padding(64.dp),
                buttonState = when (currentTracking) {
                    is CurrentTracking.Started -> MainButtonState.Started
                    CurrentTracking.Stopped -> MainButtonState.Idle
                },
                onClick = onTapTracker
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(id = R.string.main_today_activity),
                style = MaterialTheme.typography.labelMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            if (trackedSlots.isEmpty()) {
                Card(
                    Modifier
                        .padding(vertical = 4.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .padding(32.dp)
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally),
                        text = stringResource(id = R.string.main_today_activity_empty),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            } else {
                trackedSlots.forEach { trackedSlot ->
                    if (trackedSlot.category == null) {
                        TrackedSlotWithoutCategoryView(
                            modifier = Modifier.padding(vertical = 4.dp),
                            trackedSlot = trackedSlot,
                            categoryList = categoryList,
                            onCategorySelected = { category ->
                                onCategoryAssigned(trackedSlot, category)
                            }
                        )
                    } else {
                        CardContentRow(
                            modifier = Modifier.padding(vertical = 4.dp),
                            holder = trackedSlot.toContentRowHolder(LocalContext.current)
                        )
                    }
                }
            }
        }
    }
}

// Previews

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    MyApplicationTheme {
        MainScreen(
            trackedSlots = FakePreviewData.getDayListOfTrackedSlot(LocalDate.now()),
        )
    }
}
