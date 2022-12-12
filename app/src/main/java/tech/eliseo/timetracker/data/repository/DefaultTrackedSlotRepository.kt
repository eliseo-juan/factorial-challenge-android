/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tech.eliseo.timetracker.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import tech.eliseo.timetracker.data.database.dao.CategoryDao
import tech.eliseo.timetracker.data.database.dao.TrackedSlotDao
import tech.eliseo.timetracker.data.database.dto.CategoryDB
import tech.eliseo.timetracker.data.database.dto.TrackedSlotDB
import tech.eliseo.timetracker.data.database.mapper.CategoryDBMapper
import tech.eliseo.timetracker.data.database.mapper.TrackedSlotDBMapper
import tech.eliseo.timetracker.domain.model.Category
import tech.eliseo.timetracker.domain.model.TrackedSlot
import tech.eliseo.timetracker.domain.repository.TrackedSlotRepository
import tech.eliseo.timetracker.ui.preview.FakePreviewData
import java.time.LocalDate
import javax.inject.Inject

class DefaultTrackedSlotRepository @Inject constructor(
    private val trackedSlotDao: TrackedSlotDao,
    private val categoryDao: CategoryDao
) : TrackedSlotRepository, TrackedSlotDBMapper, CategoryDBMapper {

    override suspend fun populate() {
        trackedSlotDao.insertTrackedSlotList(FakePreviewData.getListOfTrackedSlot().map { it.toTrackedSlotDB() })
    }

    override val trackedSlots: Flow<List<TrackedSlot>> =
        combine(
            trackedSlotDao.getTrackedSlots(),
            categoryDao.getCategories(),
            transform = getTrackedSlotCategoryMergeAndMapper()
        )

    override fun getTrackedSlotsByDate(date: LocalDate): Flow<List<TrackedSlot>> =
        combine(
            trackedSlotDao.getTrackedSlotsByDate(date),
            categoryDao.getCategories(),
            transform = getTrackedSlotCategoryMergeAndMapper()
        )

    override fun getTodayTrackedSlots(): Flow<List<TrackedSlot>> =
        combine(
            trackedSlotDao.getTodayTrackedSlots(),
            categoryDao.getCategories(),
            transform = getTrackedSlotCategoryMergeAndMapper()
        )

    override fun getTrackedDates(): Flow<List<LocalDate>> {
        return trackedSlotDao.getTrackedDates()
    }

    override suspend fun assignCategory(
        trackedSlot: TrackedSlot,
        category: Category
    ) {
        trackedSlotDao.updateTrackedSlot(trackedSlot.copy(category = category).toTrackedSlotDB())
    }

    override suspend fun add(trackedSlot: TrackedSlot) {
        trackedSlotDao.insertTrackedSlot(trackedSlot.toTrackedSlotDB())
    }

    private fun getTrackedSlotCategoryMergeAndMapper(): (List<TrackedSlotDB>, List<CategoryDB>) -> List<TrackedSlot> =
        { trackedSlots, categoryDBList ->
            val categoryList = categoryDBList.map { it.toCategory() }
            trackedSlots.map { item -> item.toTrackedSlot(categoryList) }
        }
}
