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

package tech.eliseo.timetracker.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import tech.eliseo.timetracker.data.local.database.TrackedSlot
import tech.eliseo.timetracker.data.local.database.TrackedSlotDao
import javax.inject.Inject

interface TrackedSlotRepository {
    val trackedSlots: Flow<List<String>>

    suspend fun add(name: String)
}

class DefaultTrackedSlotRepository @Inject constructor(
    private val trackedSlotDao: TrackedSlotDao
) : TrackedSlotRepository {

    override val trackedSlots: Flow<List<String>> =
        trackedSlotDao.getTrackedSlots().map { items -> items.map { it.name } }

    override suspend fun add(name: String) {
        trackedSlotDao.insertTrackedSlot(TrackedSlot(name = name))
    }
}
