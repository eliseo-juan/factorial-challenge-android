package tech.eliseo.timetracker.domain.repository

import kotlinx.coroutines.flow.Flow
import tech.eliseo.timetracker.domain.model.Category

interface CategoryRepository {

    val categories: Flow<List<Category>>
}