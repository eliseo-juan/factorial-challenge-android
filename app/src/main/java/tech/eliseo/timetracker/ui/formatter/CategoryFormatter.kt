package tech.eliseo.timetracker.ui.formatter

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import tech.eliseo.timetracker.domain.model.Category
import tech.eliseo.timetracker.domain.model.CategoryIcon

object CategoryFormatter {

    fun getName(category: Category?): String {
        return category?.title ?: "Sin categorizar"
    }

    fun getColor(category: Category?): Color {
        return if (category == null) {
            Color.Gray
        } else {
            Color(category.color)
        }
    }

    fun getIcon(category: Category?): ImageVector {
        return when (category?.icon) {
            CategoryIcon.WORK -> Icons.Rounded.Work
            CategoryIcon.WORKOUT -> Icons.Rounded.FitnessCenter
            CategoryIcon.ENTERTAINMENT -> Icons.Rounded.SportsEsports
            null -> Icons.Rounded.Help
            else -> Icons.Rounded.Star
        }
    }
}