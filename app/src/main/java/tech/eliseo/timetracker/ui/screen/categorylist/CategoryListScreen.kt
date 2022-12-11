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

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle.State.STARTED
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import tech.eliseo.timetracker.domain.model.Category
import tech.eliseo.timetracker.ui.coponents.*
import tech.eliseo.timetracker.ui.preview.FakePreviewData
import tech.eliseo.timetracker.ui.theme.MyApplicationTheme
import java.util.*

@Composable
fun CategoryListScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: CategoryListViewModel = hiltViewModel()
) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val state by produceState<CategoryListUiState>(
        initialValue = CategoryListUiState.Loading,
        key1 = lifecycle,
        key2 = viewModel
    ) {
        lifecycle.repeatOnLifecycle(state = STARTED) {
            viewModel.uiState.collect { value = it }
        }
    }
    if (state is CategoryListUiState.Success) {
        CategoryListScreen(
            modifier = modifier,
            list = (state as CategoryListUiState.Success).list,
            onBackButtonClicked = { navController.popBackStack() },
            onAddButtonClicked = { viewModel.onUiEvent(CategoryListUiEvent.OnAddCategoryClicked) },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CategoryListScreen(
    modifier: Modifier = Modifier,
    list: List<Category> = emptyList(),
    onBackButtonClicked: () -> Unit = {},
    onAddButtonClicked: () -> Unit = {},
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Categorías",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackButtonClicked) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },

            )
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it)
                .padding(8.dp)
        ) {
            list.forEach { category ->
                CategoryRow(modifier = Modifier.padding(8.dp), category = category)
            }
            Card(
                modifier = Modifier.padding(8.dp).height(120.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "+")
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
        CategoryListScreen(
            list = FakePreviewData.getCategories()
        )
    }
}
