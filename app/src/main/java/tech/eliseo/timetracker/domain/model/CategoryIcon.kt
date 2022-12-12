package tech.eliseo.timetracker.domain.model

enum class CategoryIcon(val typeName: String) {
    WORK("work"),
    WORKOUT("workout"),
    ENTERTAINMENT("entertainment"),
    DEFAULT("default");

    companion object {
        fun getFromTypeName(typeName: String): CategoryIcon {
            return values().firstOrNull { it.typeName == typeName } ?: DEFAULT
        }
    }
}
