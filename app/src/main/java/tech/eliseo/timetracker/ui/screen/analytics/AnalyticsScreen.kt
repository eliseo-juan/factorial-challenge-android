package tech.eliseo.timetracker.ui.screen.analytics

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import tech.eliseo.timetracker.domain.model.Category
import tech.eliseo.timetracker.ui.screen.categorylist.CategoryListUiEvent
import tech.eliseo.timetracker.ui.screen.categorylist.CategoryListUiState
import tech.eliseo.timetracker.ui.screen.categorylist.CategoryListViewModel
import java.time.LocalDate

@Composable
fun AnalyticsListScreen(
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
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            viewModel.uiState.collect { value = it }
        }
    }
    if (state is CategoryListUiState.Success) {
        tech.eliseo.timetracker.ui.screen.categorylist.CategoryListScreen(
            modifier = modifier,
            list = (state as CategoryListUiState.Success).list,
            onBackButtonClicked = { navController.popBackStack() },
            onAddButtonClicked = { viewModel.onUiEvent(CategoryListUiEvent.OnAddCategoryClicked) },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AnalyticsListScreen(
    modifier: Modifier = Modifier,
    dateList: List<LocalDate> = emptyList(),
    selectedDate: LocalDate = LocalDate.now(),
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

    }
}