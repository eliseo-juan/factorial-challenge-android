package tech.eliseo.timetracker.ui.coponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.datetime.*
import tech.eliseo.timetracker.domain.model.TrackedSlot
import tech.eliseo.timetracker.ui.formatter.TrackedSlotFormatter
import tech.eliseo.timetracker.ui.theme.MyApplicationTheme
import java.time.Instant

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
            Image(
                modifier = Modifier
                    .padding(4.dp)
                    .size(40.dp)
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.Blue)
                    .padding(8.dp),
                imageVector = Icons.Filled.ShoppingCart,
                contentDescription = "",
                colorFilter = ColorFilter.tint(Color.White)
            )
            Column(
                modifier = Modifier
                    .padding(4.dp)
                    .weight(1f, true),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Trabajo",
                    maxLines = 1,
                    style = MaterialTheme.typography.labelMedium
                )
                Text(
                    text = TrackedSlotFormatter.getDuration(trackedSlot),
                    maxLines = 1,
                    style = MaterialTheme.typography.titleMedium
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
                    startDate = Clock.System.now().plus(-90, DateTimeUnit.MINUTE).toLocalDateTime(TimeZone.currentSystemDefault()),
                    endDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                    category = "Prueba"
                )
            )
        }
    }
}