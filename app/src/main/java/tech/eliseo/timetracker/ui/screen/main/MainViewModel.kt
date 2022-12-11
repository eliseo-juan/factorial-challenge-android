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

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import tech.eliseo.timetracker.domain.model.CurrentTracking
import tech.eliseo.timetracker.domain.model.TrackedSlot
import tech.eliseo.timetracker.domain.usecase.GetCurrentTrackingUseCase
import tech.eliseo.timetracker.domain.usecase.GetTodayTrackedSlotListUseCase
import tech.eliseo.timetracker.domain.usecase.GetTrackedSlotListUseCase
import tech.eliseo.timetracker.domain.usecase.OnToggleTrackerUseCase
import tech.eliseo.timetracker.ui.screen.main.TrackedSlotUiState.Loading
import tech.eliseo.timetracker.ui.screen.main.TrackedSlotUiState.Success
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getTrackedSlotListUseCase: GetTrackedSlotListUseCase,
    getTodayTrackedSlotListUseCase: GetTodayTrackedSlotListUseCase,
    getCurrentTrackingUseCase: GetCurrentTrackingUseCase,
    private val onToggleTrackerUseCase: OnToggleTrackerUseCase,
) : ViewModel() {

    val uiState: StateFlow<TrackedSlotUiState> = getTrackedSlotListUseCase()
        .combine(getCurrentTrackingUseCase()) { trackedSlots, currentTracking ->
            Success(
                trackStartDate = currentTracking,
                todayTrackedSlot = trackedSlots
            )
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Loading)

    fun onUiEvent(event: TrackedSlotUiEvent) {
        when (event) {
            TrackedSlotUiEvent.OnTrackedClicked -> onTrackedClicked()
        }
    }

    private fun onTrackedClicked() {
        viewModelScope.launch {
            onToggleTrackerUseCase()
        }
    }
}

sealed class TrackedSlotUiState {
    object Loading : TrackedSlotUiState()
    data class Success(
        val trackStartDate: CurrentTracking,
        val todayTrackedSlot: List<TrackedSlot>
    ) : TrackedSlotUiState()
}

sealed class TrackedSlotUiEvent {
    object OnTrackedClicked : TrackedSlotUiEvent()
}
