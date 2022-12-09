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

package tech.eliseo.timetracker.ui.trackedslot

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DonutLarge
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle.State.STARTED
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.datetime.*
import tech.eliseo.timetracker.ui.theme.MyApplicationTheme
import tech.eliseo.timetracker.domain.model.TrackedSlot
import tech.eliseo.timetracker.ui.coponents.MainButton
import tech.eliseo.timetracker.ui.coponents.NavigationButton
import tech.eliseo.timetracker.ui.coponents.TrackedSlotView
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Calendar
import java.util.Date

@Composable
fun TrackedSlotScreen(
    modifier: Modifier = Modifier,
    viewModel: TrackedSlotViewModel = hiltViewModel()
) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val items by produceState<TrackedSlotUiState>(
        initialValue = TrackedSlotUiState.Loading,
        key1 = lifecycle,
        key2 = viewModel
    ) {
        lifecycle.repeatOnLifecycle(state = STARTED) {
            viewModel.uiState.collect { value = it }
        }
    }
    if (items is TrackedSlotUiState.Success) {
        TrackedSlotScreen(
            lastTrackedSlot = (items as TrackedSlotUiState.Success).lastTrackedSlot,
            onSave = viewModel::addTrackedSlot,
            modifier = modifier
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TrackedSlotScreen(
    lastTrackedSlot: TrackedSlot?,
    onSave: (name: String) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(16.dp)
        ) {
            MainButton(
                modifier = Modifier.padding(16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            if (lastTrackedSlot != null) {
                Text(
                    text = "Última actividad",
                    style = MaterialTheme.typography.labelMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                TrackedSlotView(trackedSlot = lastTrackedSlot)
            }
            Spacer(modifier = Modifier.height(16.dp))
            NavigationButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Histórico",
                icon = Icons.Outlined.DonutLarge
            )
        }
    }
}

// Previews

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    MyApplicationTheme {
        TrackedSlotScreen(
            TrackedSlot(
                startDate = Clock.System.now().plus(90, DateTimeUnit.MINUTE).toLocalDateTime(TimeZone.currentSystemDefault()),
                endDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                category = "Prueba"
            ), onSave = {})
    }
}

@Preview(showBackground = true, widthDp = 480)
@Composable
private fun PortraitPreview() {
    MyApplicationTheme {
        TrackedSlotScreen(TrackedSlot(
            startDate = Clock.System.now().plus(90, DateTimeUnit.MINUTE).toLocalDateTime(TimeZone.currentSystemDefault()),
            endDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            category = "Prueba"
        ), onSave = {})
    }
}
