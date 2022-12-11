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

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.flow.map
import tech.eliseo.timetracker.data.database.dao.TrackedSlotDao
import tech.eliseo.timetracker.data.database.mapper.TrackedSlotDBMapper
import tech.eliseo.timetracker.domain.model.CurrentTracking
import tech.eliseo.timetracker.domain.model.TrackedSlot
import tech.eliseo.timetracker.domain.repository.TrackedSlotRepository
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

class DefaultTrackedSlotRepository @Inject constructor(
    private val trackedSlotDao: TrackedSlotDao
) : TrackedSlotRepository, TrackedSlotDBMapper {

    override val trackedSlots: Flow<List<TrackedSlot>> =
        trackedSlotDao.getTrackedSlots()
            .map { it.map { item -> item.toTrackedSlot() } }

    override fun getTrackedSlotsByDate(date: LocalDate) : Flow<List<TrackedSlot>> {
        return trackedSlotDao.getTrackedSlotsByDate()
            .map { it.map { item -> item.toTrackedSlot() } }
    }

    override suspend fun add(trackedSlot: TrackedSlot) {
        trackedSlotDao.insertTrackedSlot(trackedSlot.toTrackedSlotDB())
    }
}