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

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import tech.eliseo.timetracker.domain.model.TrackedSlot
import tech.eliseo.timetracker.domain.usecase.GetLastTrackedSlotUseCase
import tech.eliseo.timetracker.domain.usecase.GetTrackedSlotListUseCase
import tech.eliseo.timetracker.ui.trackedslot.TrackedSlotUiState.Loading
import tech.eliseo.timetracker.ui.trackedslot.TrackedSlotUiState.Success
import javax.inject.Inject

@HiltViewModel
class TrackedSlotViewModel @Inject constructor(
    private val getTrackedSlotListUseCase: GetTrackedSlotListUseCase,
    getLastTrackedSlotUseCase: GetLastTrackedSlotUseCase
) : ViewModel() {

    val uiState: StateFlow<TrackedSlotUiState> = getLastTrackedSlotUseCase()
        .map { Success(lastTrackedSlot = it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Loading)

    fun addTrackedSlot(name: String) {
        viewModelScope.launch {

        }
    }
}

sealed class TrackedSlotUiState {
    object Loading : TrackedSlotUiState()
    data class Success(val lastTrackedSlot: TrackedSlot) : TrackedSlotUiState()
}

sealed class TrackedSlotUiEvent {
    object OnTrackedClicked : TrackedSlotUiEvent()
    object OnLastTrackedSlotClicked : TrackedSlotUiEvent()
}
