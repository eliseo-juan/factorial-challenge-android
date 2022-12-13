package tech.eliseo.timetracker.ui.screen.analytics

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import tech.eliseo.timetracker.R
import tech.eliseo.timetracker.domain.model.Category
import tech.eliseo.timetracker.domain.model.TrackedSlot
import tech.eliseo.timetracker.ui.base.ViewModelScreen
import tech.eliseo.timetracker.ui.coponents.CardContentRow
import tech.eliseo.timetracker.ui.coponents.ContentRow
import tech.eliseo.timetracker.ui.coponents.DonutChart
import tech.eliseo.timetracker.ui.formatter.CategoryFormatter
import tech.eliseo.timetracker.ui.formatter.contentRowHolderFromEmptyCategory
import tech.eliseo.timetracker.ui.formatter.toContentRowHolder
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@Composable
fun AnalyticsScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: AnalyticsViewModel = hiltViewModel()
) {
    ViewModelScreen(
        viewModel = viewModel,
        initialState = AnalyticsUiState.Loading,
    ) { state ->
        if (state is AnalyticsUiState.Success) {
            AnalyticsScreen(
                modifier = modifier,
                dateList = state.dateList,
                selectedDate = state.selectedDate,
                categoryUsage = state.categoryUsage,
                trackedSlotList = state.trackedSlotList,
                onDateSelected = { viewModel.onUiEvent(AnalyticsUiEvent.OnDateSelected(it)) },
                onBackButtonClicked = { navController.popBackStack() },
            )
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AnalyticsScreen(
    modifier: Modifier = Modifier,
    dateList: List<LocalDate> = emptyList(),
    selectedDate: LocalDate = LocalDate.now(),
    trackedSlotList: List<TrackedSlot> = emptyList(),
    categoryUsage: List<Pair<Category?, Int>> = emptyList(),
    onDateSelected: (LocalDate) -> Unit = {},
    onBackButtonClicked: () -> Unit = {},
) {

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                TopAppBar(
                    title = {
                        Text(
                            stringResource(id = R.string.statistics_title),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = onBackButtonClicked) {
                            Icon(
                                imageVector = Icons.Rounded.ArrowBack,
                                contentDescription = null
                            )
                        }
                    },
                )
                ScrollableTabRow(
                    modifier = Modifier.fillMaxWidth(),
                    selectedTabIndex = dateList.indexOf(selectedDate),
                    divider = @Composable {
                        Divider(Modifier.fillMaxWidth())
                    }
                ) {
                    dateList.forEachIndexed { index, localDate ->
                        Tab(
                            selected = localDate == selectedDate,
                            onClick = { onDateSelected(localDate) },
                            text = {
                                Text(
                                    text = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)
                                        .format(localDate)
                                )
                            }
                        )
                    }
                }
            }

        },
    ) {
        AnalyticsPageScreen(
            modifier = Modifier.padding(it),
            trackedSlotList = trackedSlotList,
            categoryUsage = categoryUsage
        )
    }
}

@Composable
internal fun AnalyticsPageScreen(
    modifier: Modifier = Modifier,
    trackedSlotList: List<TrackedSlot>,
    categoryUsage: List<Pair<Category?, Int>>,
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        DonutChart(
            modifier = Modifier
                .size(LocalConfiguration.current.screenWidthDp.dp / 2)
                .padding(16.dp),
            slices = categoryUsage.map {
                Pair(CategoryFormatter.getColor(it.first), it.second.toFloat())
            }
        )

        categoryUsage.forEach {
            ContentRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 8.dp),
                holder = it.first?.toContentRowHolder(LocalContext.current, it.second)
                    ?: contentRowHolderFromEmptyCategory(
                        LocalContext.current, it.second
                    )
            )
        }

        Spacer(modifier = Modifier.size(16.dp))
        if (trackedSlotList.isNotEmpty()) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .align(Alignment.Start),
                text = stringResource(id = R.string.analytics_tracked_slots_title),
                style = MaterialTheme.typography.labelLarge
            )
            trackedSlotList.forEach {
                CardContentRow(
                    modifier = Modifier.padding(8.dp),
                    holder = it.toContentRowHolder(LocalContext.current)
                )
            }
        }
    }
}