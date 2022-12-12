package tech.eliseo.timetracker.ui.coponents

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.eliseo.timetracker.domain.model.TrackedSlot
import tech.eliseo.timetracker.ui.formatter.CategoryFormatter
import tech.eliseo.timetracker.ui.formatter.TrackedSlotFormatter
import tech.eliseo.timetracker.ui.theme.MyApplicationTheme
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrackedSlotView(
    modifier: Modifier = Modifier,
    trackedSlot: TrackedSlot,
    onCardClick: () -> Unit = {}
) {
    Card(
        modifier = modifier,
        onClick = onCardClick,
        colors = CardDefaults.elevatedCardColors(),
        elevation = CardDefaults.elevatedCardElevation()
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(1f)
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CategoryIconView(
                modifier = Modifier.padding(8.dp),
                category = trackedSlot.category
            )
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f, true),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = CategoryFormatter.getName(trackedSlot.category),
                    maxLines = 1,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = TrackedSlotFormatter.getTimePeriod(trackedSlot),
                    maxLines = 1,
                    style = MaterialTheme.typography.labelMedium
                )
            }
            Text(
                modifier = Modifier
                    .padding(8.dp)
                    .wrapContentWidth(),
                text = TrackedSlotFormatter.getDuration(LocalContext.current, trackedSlot),
                style = MaterialTheme.typography.titleMedium
            )
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