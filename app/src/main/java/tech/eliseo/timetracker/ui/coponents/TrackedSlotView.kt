package tech.eliseo.timetracker.ui.coponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
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
    ) {
        Row(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(1f)
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CategoryIconView(
                modifier = Modifier.padding(4.dp)
            )
            Column(
                modifier = Modifier
                    .padding(4.dp)
                    .weight(1f, true),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = CategoryFormatter.getName(null),
                    maxLines = 1,
                    style = MaterialTheme.typography.labelLarge
                )
                Text(
                    text = TrackedSlotFormatter.getDuration(trackedSlot),
                    maxLines = 1,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Column(
                modifier = Modifier
                    .padding(4.dp)
                    .wrapContentWidth(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = TrackedSlotFormatter.getStartTime(trackedSlot),
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = TrackedSlotFormatter.getEndTime(trackedSlot),
                    style = MaterialTheme.typography.bodyMedium
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
                    category = "Prueba"
                )
            )
        }
    }
}