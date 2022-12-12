package tech.eliseo.timetracker.ui.coponents

import android.widget.Space
import androidx.compose.foundation.clickable
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

@Composable
fun ContentRow(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    iconBackgroundColor: Color,
    title: String? = null,
    subtitle: String? = null,
    detail: String? = null,
    onCardClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth(1f)
            .wrapContentHeight()
            .clickable { onCardClick() },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ContentIcon(
            icon = icon,
            backgroundColor = iconBackgroundColor
        )
        Spacer(modifier = Modifier.size(16.dp))
        Column(
            modifier = Modifier
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
        Spacer(modifier = Modifier.size(16.dp))
        if (detail != null) {
            Text(
                modifier = Modifier
                    .wrapContentWidth(),
                text = detail,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Composable
fun ContentRow(
    modifier: Modifier = Modifier,
    holder: ContentRowHolder,
    onCardClick: () -> Unit = {}
) {
    ContentRow(
        modifier = modifier,
        icon = holder.icon.icon,
        iconBackgroundColor = holder.icon.backgroundColor,
        title = holder.title,
        subtitle = holder.subtitle,
        detail = holder.detail,
        onCardClick = onCardClick
    )
}

data class ContentRowHolder(
    val icon: ContentIconHolder,
    val title: String? = null,
    val subtitle: String? = null,
    val detail: String? = null,
)

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    MyApplicationTheme {
        Box(Modifier.padding(0.dp)) {
            ContentRow(
                icon = Icons.Rounded.Star,
                iconBackgroundColor = Color.Red,
                title = "Title",
                subtitle = "Subtitle",
                detail = "Details"
            )
        }
    }
}