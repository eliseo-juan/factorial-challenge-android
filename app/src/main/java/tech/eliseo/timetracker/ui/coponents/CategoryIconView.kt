package tech.eliseo.timetracker.ui.coponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.eliseo.timetracker.domain.model.Category
import tech.eliseo.timetracker.ui.formatter.CategoryFormatter

@Composable
fun CategoryIconView(
    modifier: Modifier = Modifier,
    category: Category? = null
) {
    Image(
        modifier = modifier
            .size(40.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(8.dp))
            .background(CategoryFormatter.getColor(category))
            .padding(8.dp),
        imageVector = CategoryFormatter.getIcon(category),
        contentDescription = "",
        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.surface)
    )
}