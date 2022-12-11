package tech.eliseo.timetracker.domain.model

data class Category(
    val title: String,
    val color: Long,
    val icon: CategoryIcon,
)