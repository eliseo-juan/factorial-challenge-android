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

package tech.eliseo.timetracker.ui.screen.analytics_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import tech.eliseo.timetracker.domain.model.Category
import tech.eliseo.timetracker.domain.usecase.GetCategoryListUseCase
import javax.inject.Inject

@HiltViewModel
class AnalyticsPageViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    get: GetCategoryListUseCase,
) : ViewModel() {

    private val date: LocalDate
        get() = savedStateHandle.get<Int>(KEY_PRODUCT_ID)
            ?: throw IllegalStateException("Product id not found")

    val uiState: StateFlow<CategoryListUiState> = getCategoryListUseCase()
        .map {
            CategoryListUiState.Success(
                list = it
            )
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), CategoryListUiState.Loading)

    fun onUiEvent(event: CategoryListUiEvent) {
        when (event) {
            CategoryListUiEvent.OnAddCategoryClicked -> onAddCategoryClicked()
        }
    }

    private fun onAddCategoryClicked() {
        viewModelScope.launch {
            //onToggleTrackerUseCase()
        }
    }
}

sealed class AnalyticsPageUiState {
    object Loading : AnalyticsPageUiState()
    data class Success(
        val list: List<TrackedSlot>
    ) : CategoryListUiState()
}

sealed class CategoryListUiEvent {
    object OnAddCategoryClicked : CategoryListUiEvent()
}
