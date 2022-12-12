package tech.eliseo.timetracker.data.database.mapper

import tech.eliseo.timetracker.data.database.dto.CategoryDB
import tech.eliseo.timetracker.domain.model.Category
import tech.eliseo.timetracker.domain.model.CategoryIcon

interface CategoryDBMapper {

    fun CategoryDB.toCategory() = Category(
        id = this.categoryId,
        title = this.title,
        color = this.color,
        icon = CategoryIcon.getFromTypeName(this.icon),
    )

    fun Category.toCategoryDB() = CategoryDB(
        categoryId = this.id,
        title = this.title,
        color = this.color,
        icon = this.icon.typeName,
    )
}