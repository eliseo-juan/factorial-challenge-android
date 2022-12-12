package tech.eliseo.timetracker.ui.formatter

import android.content.Context
import tech.eliseo.timetracker.domain.model.Category
import tech.eliseo.timetracker.ui.coponents.ContentIconHolder
import tech.eliseo.timetracker.ui.coponents.ContentRowHolder

fun Category.toContentIconHolder(): ContentIconHolder = ContentIconHolder(
    icon = CategoryFormatter.getIcon(this),
    backgroundColor = CategoryFormatter.getColor(this),
)

fun Category.toContentRowHolder(context: Context, seconds: Int): ContentRowHolder = ContentRowHolder(
    icon = this.toContentIconHolder(),
    title = CategoryFormatter.getName(this),
    detail = TrackedSlotFormatter.getDuration(context, seconds)
)

fun contentRowHolderFromEmptyCategory(context: Context, seconds: Int) = ContentRowHolder(
    icon = ContentIconHolder(
        icon = CategoryFormatter.getIcon(null),
        backgroundColor = CategoryFormatter.getColor(null),
    ),
    title = CategoryFormatter.getName(null),
    detail = TrackedSlotFormatter.getDuration(context, seconds)
)