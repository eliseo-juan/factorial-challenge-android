package tech.eliseo.timetracker.domain.model

data class Category(
    val id: Long,
    val title: String,
    val color: Long,
    val icon: CategoryIcon,
)