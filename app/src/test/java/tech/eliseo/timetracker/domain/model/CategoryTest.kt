package tech.eliseo.timetracker.domain.model

class CategoryTest {
    fun getCategory(
        id: Long = 0,
        title: String = "CategoryTitle",
        color: Long = 0xFFfee440,
        icon: CategoryIcon = CategoryIcon.DEFAULT,
    ): Category = Category(
        id = id,
        title = title,
        color = color,
        icon = icon,
    )

    fun getCategories(
        list: List<Category> = listOf(
            Category(id = 1, title = "Trabajo", color = 0xFF00f5d4, icon = CategoryIcon.WORK),
            Category(id = 2, title = "Deporte", color = 0xFF00bbf9, icon = CategoryIcon.WORKOUT),
            Category(
                id = 3,
                title = "Entretenimiento",
                color = 0xFFf15bb5,
                icon = CategoryIcon.ENTERTAINMENT
            ),
        )
    ): List<Category> = list
}