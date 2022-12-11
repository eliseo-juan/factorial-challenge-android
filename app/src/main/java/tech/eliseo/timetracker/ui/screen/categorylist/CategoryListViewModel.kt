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

package tech.eliseo.timetracker.ui.screen.categorylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import tech.eliseo.timetracker.domain.model.Category
import tech.eliseo.timetracker.domain.usecase.*
import javax.inject.Inject

@HiltViewModel
class CategoryListViewModel @Inject constructor(
    getCategoryListUseCase: GetCategoryListUseCase,
) : ViewModel() {

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

sealed class CategoryListUiState {
    object Loading : CategoryListUiState()
    data class Success(
        val list: List<Category>
    ) : CategoryListUiState()
}

sealed class CategoryListUiEvent {
    object OnAddCategoryClicked : CategoryListUiEvent()
}
