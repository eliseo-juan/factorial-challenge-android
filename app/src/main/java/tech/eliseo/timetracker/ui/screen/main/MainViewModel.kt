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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import tech.eliseo.timetracker.domain.model.Category
import tech.eliseo.timetracker.domain.model.CurrentTracking
import tech.eliseo.timetracker.domain.model.TrackedSlot
import tech.eliseo.timetracker.domain.repository.TrackedSlotRepository
import tech.eliseo.timetracker.domain.usecase.*
import tech.eliseo.timetracker.ui.screen.main.TrackedSlotUiState.Loading
import tech.eliseo.timetracker.ui.screen.main.TrackedSlotUiState.Success
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getTodayTrackedSlotListUseCase: GetTodayTrackedSlotListUseCase,
    getCurrentTrackingUseCase: GetCurrentTrackingUseCase,
    getCategoryListUseCase: GetCategoryListUseCase,
    private val assignCategoryUseCase: AssignCategoryUseCase,
    private val onToggleTrackerUseCase: OnToggleTrackerUseCase,
    private val trackedSlotRepository: TrackedSlotRepository,
) : ViewModel() {

    val uiState: StateFlow<TrackedSlotUiState> = combine(
        getTodayTrackedSlotListUseCase(),
        getCurrentTrackingUseCase(),
        getCategoryListUseCase()
    ) { trackedSlots, currentTracking, categoryList ->
        Success(
            trackStartDate = currentTracking,
            todayTrackedSlot = trackedSlots,
            categoryList = categoryList
        )
    }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Loading)

    fun onUiEvent(event: TrackedSlotUiEvent) {
        when (event) {
            TrackedSlotUiEvent.OnTrackedClicked -> onTrackedClicked()
            is TrackedSlotUiEvent.OnCategoryAssigned -> onCategoryAssigned(event.trackedSlot, event.category)
        }
    }

    private fun onTrackedClicked() {
        viewModelScope.launch {
            onToggleTrackerUseCase((uiState.value as? Success)?.trackStartDate)
            //trackedSlotRepository.populate()
        }
    }

    private fun onCategoryAssigned(trackedSlot: TrackedSlot, category: Category) {
        viewModelScope.launch {
            assignCategoryUseCase(trackedSlot, category)
        }
    }
}

sealed class TrackedSlotUiState {
    object Loading : TrackedSlotUiState()
    data class Success(
        val trackStartDate: CurrentTracking,
        val todayTrackedSlot: List<TrackedSlot>,
        val categoryList: List<Category>,
    ) : TrackedSlotUiState()
}

sealed class TrackedSlotUiEvent {
    object OnTrackedClicked : TrackedSlotUiEvent()
    data class OnCategoryAssigned(val trackedSlot: TrackedSlot, val category: Category) :
        TrackedSlotUiEvent()
}
