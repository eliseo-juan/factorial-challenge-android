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
import tech.eliseo.timetracker.domain.usecase.CreateCategoryUseCase
import tech.eliseo.timetracker.domain.usecase.GetCategoryListUseCase
import javax.inject.Inject

@HiltViewModel
class CategoryListViewModel @Inject constructor(
    getCategoryListUseCase: GetCategoryListUseCase,
    private val createCategoryUseCase: CreateCategoryUseCase,
) : ViewModel() {

    val categoryDialogOpenState = MutableStateFlow(false)

    val uiState: StateFlow<List<Category>> = getCategoryListUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun onUiEvent(event: CategoryListUiEvent) {
        when (event) {
            CategoryListUiEvent.OnAddCategoryClicked -> onAddCategoryClicked()
            CategoryListUiEvent.OnRequestCloseDialog -> onOnRequestCloseDialog()
            is CategoryListUiEvent.OnConfirmNewCategoryClicked -> createCategory(event.name)
        }
    }

    private fun onAddCategoryClicked() {
        viewModelScope.launch {
            categoryDialogOpenState.value = true
        }
    }

    private fun onOnRequestCloseDialog() {
        viewModelScope.launch {
            categoryDialogOpenState.value = false
        }
    }

    private fun createCategory(name: String) {
        viewModelScope.launch {
            createCategoryUseCase(name)
            categoryDialogOpenState.value = false
        }
    }
}

sealed class CategoryListUiEvent {
    object OnAddCategoryClicked : CategoryListUiEvent()
    object OnRequestCloseDialog : CategoryListUiEvent()
    data class OnConfirmNewCategoryClicked(val name: String) : CategoryListUiEvent()
}
