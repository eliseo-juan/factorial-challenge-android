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

package tech.eliseo.timetracker.data.network

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import tech.eliseo.timetracker.data.network.datasource.FirebaseTrackedSlotDatasource
import tech.eliseo.timetracker.data.network.datasource.NetworkTrackedSlotDatasource
import tech.eliseo.timetracker.data.network.service.TrackedSlotService

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideDatabaseReference(): DatabaseReference {
        return Firebase.database.reference
    }

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .build()
    }

    @Provides
    fun provideTrackedSlotService(
        retrofit: Retrofit
    ): TrackedSlotService {
        return retrofit.create(TrackedSlotService::class.java)
    }

    @Provides
    fun provideNetworkTrackedSlotDatasource(
        database: FirebaseTrackedSlotDatasource
    ): NetworkTrackedSlotDatasource = database

}
