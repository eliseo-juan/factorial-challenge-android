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

package tech.eliseo.timetracker.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import tech.eliseo.timetracker.data.local.database.converter.LocalDateConverter
import tech.eliseo.timetracker.data.local.database.converter.LocalDateTimeConverter
import tech.eliseo.timetracker.data.local.database.dao.CategoryDao
import tech.eliseo.timetracker.data.local.database.dao.TrackedSlotDao
import tech.eliseo.timetracker.data.local.database.entities.CategoryEntity
import tech.eliseo.timetracker.data.local.database.entities.TrackedSlotEntity

@Database(entities = [TrackedSlotEntity::class, CategoryEntity::class], version = 2)
@TypeConverters(LocalDateTimeConverter::class, LocalDateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun trackedSlotDao(): TrackedSlotDao
    abstract fun categoryDao(): CategoryDao
}
