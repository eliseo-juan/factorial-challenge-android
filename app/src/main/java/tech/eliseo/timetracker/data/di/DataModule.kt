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

package tech.eliseo.timetracker.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tech.eliseo.timetracker.data.repository.DefaultCategoryRepository
import tech.eliseo.timetracker.data.repository.DefaultCurrentTrackingRepository
import tech.eliseo.timetracker.data.repository.DefaultTrackedSlotRepository
import tech.eliseo.timetracker.domain.repository.CategoryRepository
import tech.eliseo.timetracker.domain.repository.CurrentTrackingRepository
import tech.eliseo.timetracker.domain.repository.TrackedSlotRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Singleton
    @Binds
    fun bindsTrackedSlotRepository(
        repository: DefaultTrackedSlotRepository
    ): TrackedSlotRepository

    @Singleton
    @Binds
    fun bindsCurrentTrackingRepository(
        repository: DefaultCurrentTrackingRepository
    ): CurrentTrackingRepository

    @Singleton
    @Binds
    fun bindsCategoryRepository(
        repository: DefaultCategoryRepository
    ): CategoryRepository
}
