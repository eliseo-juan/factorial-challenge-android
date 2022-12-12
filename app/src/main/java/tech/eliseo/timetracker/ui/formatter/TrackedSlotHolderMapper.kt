package tech.eliseo.timetracker.ui.formatter

import android.content.Context
import tech.eliseo.timetracker.domain.model.TrackedSlot
import tech.eliseo.timetracker.ui.coponents.ContentIconHolder
import tech.eliseo.timetracker.ui.coponents.ContentRowHolder

fun TrackedSlot.toContentRowHolder(context: Context): ContentRowHolder = ContentRowHolder(
    icon = ContentIconHolder(
        icon = CategoryFormatter.getIcon(category),
        backgroundColor = CategoryFormatter.getColor(category),
    ),
    title = CategoryFormatter.getName(category),
    subtitle = TrackedSlotFormatter.getTimePeriod(this),
    detail = TrackedSlotFormatter.getDuration(context, this)
)