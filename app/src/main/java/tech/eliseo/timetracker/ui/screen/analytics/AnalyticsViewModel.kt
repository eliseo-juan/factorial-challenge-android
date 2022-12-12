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

package tech.eliseo.timetracker.ui.screen.analytics

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import tech.eliseo.timetracker.domain.model.Category
import tech.eliseo.timetracker.domain.model.TrackedSlot
import tech.eliseo.timetracker.domain.usecase.GetCategoryUsageByDateUseCase
import tech.eliseo.timetracker.domain.usecase.GetTrackedDatesUseCase
import tech.eliseo.timetracker.domain.usecase.GetTrackedSlotListByDateUseCase
import tech.eliseo.timetracker.ui.base.BaseViewModel
import tech.eliseo.timetracker.ui.base.UiState
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class AnalyticsViewModel @Inject constructor(
    getCategoryUsageByDateUseCase: GetCategoryUsageByDateUseCase,
    getTrackedSlotListByDateUseCase: GetTrackedSlotListByDateUseCase,
    getTrackedDatesUseCase: GetTrackedDatesUseCase,
) : BaseViewModel<AnalyticsUiState>() {

    private val selectedDate = MutableStateFlow<LocalDate>(LocalDate.now())

    @FlowPreview
    override val uiState: StateFlow<AnalyticsUiState> = selectedDate
        .flatMapMerge {
            combine(
                getTrackedSlotListByDateUseCase(it),
                getCategoryUsageByDateUseCase(it),
                getTrackedDatesUseCase()
            ) { trackedSlots, categoryUsage, trackedDates ->
                AnalyticsUiState.Success(
                    dateList = trackedDates,
                    selectedDate = it,
                    trackedSlotList = trackedSlots,
                    categoryUsage = categoryUsage
                )
            }
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            AnalyticsUiState.Loading
        )

    fun onUiEvent(event: AnalyticsUiEvent) {
        when (event) {
            is AnalyticsUiEvent.OnDateSelected -> onDateSelected(event.date)
        }
    }

    private fun onDateSelected(date: LocalDate) {
        selectedDate.value = date
    }
}

sealed class AnalyticsUiState : UiState {
    object Loading : AnalyticsUiState()
    data class Success(
        val dateList: List<LocalDate> = emptyList(),
        val selectedDate: LocalDate = LocalDate.now(),
        val categoryUsage: List<Pair<Category?, Int>> = emptyList(),
        val trackedSlotList: List<TrackedSlot> = emptyList()
    ) : AnalyticsUiState()
}

sealed class AnalyticsUiEvent {
    data class OnDateSelected(val date: LocalDate) : AnalyticsUiEvent()
}
