package tech.eliseo.timetracker.ui.coponents

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.eliseo.timetracker.ui.theme.MyApplicationTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardContentRow(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    iconBackgroundColor: Color,
    title: String? = null,
    subtitle: String? = null,
    detail: String? = null,
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
            ContentIcon(
                modifier = Modifier.padding(8.dp),
                icon = icon,
                backgroundColor = iconBackgroundColor
            )
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f, true),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                if (title != null) {
                    Text(
                        text = title,
                        maxLines = 1,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                if (subtitle != null) {
                    Text(
                        text = subtitle,
                        maxLines = 1,
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
            if (detail != null) {
                Text(
                    modifier = Modifier
                        .padding(8.dp)
                        .wrapContentWidth(),
                    text = detail,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@Composable
fun CardContentRow(
    modifier: Modifier = Modifier,
    holder: ContentRowHolder,
    onCardClick: () -> Unit = {}
) {
    CardContentRow(
        modifier = modifier,
        icon = holder.icon.icon,
        iconBackgroundColor = holder.icon.backgroundColor,
        title = holder.title,
        subtitle = holder.subtitle,
        detail = holder.detail,
        onCardClick = onCardClick
    )
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    MyApplicationTheme {
        Box(Modifier.padding(0.dp)) {
            CardContentRow(
                icon = Icons.Rounded.Star,
                iconBackgroundColor = Color.Red,
                title = "Title",
                subtitle = "Subtitle",
                detail = "Details"
            )
        }
    }
}