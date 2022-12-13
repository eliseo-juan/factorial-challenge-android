package tech.eliseo.timetracker.data.local.database.mapper

import tech.eliseo.timetracker.data.local.database.entities.CategoryEntity
import tech.eliseo.timetracker.domain.model.Category
import tech.eliseo.timetracker.domain.model.CategoryIcon

interface CategoryEntityMapper {

    fun CategoryEntity.toCategory() = Category(
        id = this.categoryId,
        title = this.title,
        color = this.color,
        icon = CategoryIcon.getFromTypeName(this.icon),
    )

    fun Category.toCategoryEntity() = CategoryEntity(
        categoryId = this.id,
        title = this.title,
        color = this.color,
        icon = this.icon.typeName,
    )
}