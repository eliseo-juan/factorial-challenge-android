package tech.eliseo.timetracker.ui.coponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.eliseo.timetracker.R
import tech.eliseo.timetracker.domain.model.Category
import tech.eliseo.timetracker.domain.model.TrackedSlot
import tech.eliseo.timetracker.ui.theme.MyApplicationTheme
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrackedSlotWithoutCategoryView(
    modifier: Modifier = Modifier,
    trackedSlot: TrackedSlot,
    categoryList: List<Category>,
    onCardClick: () -> Unit = {},
    onCategorySelected: (Category) -> Unit = {}
) {
    Card(
        modifier = modifier,
        onClick = onCardClick,
        colors = CardDefaults.cardColors(),
    ) {
        TrackedSlotView(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp),
            trackedSlot = trackedSlot,
            onCardClick = onCardClick
        )

        Text(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 0.dp, bottom = 0.dp),
            text = stringResource(id = R.string.tracked_slot_assign_a_category),
            maxLines = 1,
            style = MaterialTheme.typography.labelMedium
        )

        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(top = 0.dp, start = 8.dp, end = 8.dp, bottom = 8.dp)
        ) {
            categoryList.forEach {
                CategoryIconView(
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable { onCategorySelected(it) },
                    category = it
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    MyApplicationTheme {
        Box(Modifier.padding(0.dp)) {
            TrackedSlotView(
                trackedSlot = TrackedSlot(
                    startDate = LocalDateTime.now().minusHours(2),
                    endDate = LocalDateTime.now(),
                    category = null
                )
            )
        }
    }
}