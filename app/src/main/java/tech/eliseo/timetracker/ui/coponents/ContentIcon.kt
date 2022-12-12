package tech.eliseo.timetracker.ui.coponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun ContentIcon(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    backgroundColor: Color,
) {
    Image(
        modifier = modifier
            .size(40.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .padding(8.dp),
        imageVector = icon,
        contentDescription = "",
        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.surface)
    )
}

@Composable
fun ContentIcon(
    modifier: Modifier = Modifier,
    holder: ContentIconHolder
) {
    ContentIcon(
        modifier = modifier,
        icon = holder.icon,
        backgroundColor = holder.backgroundColor
    )
}

data class ContentIconHolder(
    val icon: ImageVector,
    val backgroundColor: Color,
)