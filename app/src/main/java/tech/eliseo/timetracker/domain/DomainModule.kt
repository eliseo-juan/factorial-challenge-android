package tech.eliseo.timetracker.domain

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tech.eliseo.timetracker.domain.repository.TrackedSlotRepository
import tech.eliseo.timetracker.domain.usecase.GetLastTrackedSlotUseCase
import tech.eliseo.timetracker.domain.usecase.GetLastTrackedSlotUseCaseImpl
import tech.eliseo.timetracker.domain.usecase.GetTrackedSlotListUseCase
import tech.eliseo.timetracker.domain.usecase.GetTrackedSlotListUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {

    @Binds
    fun bindsGetLastTrackedSlotUseCase(
        useCase: GetLastTrackedSlotUseCaseImpl
    ): GetLastTrackedSlotUseCase

    @Binds
    fun bindsGetTrackedSlotListUseCase(
        useCase: GetTrackedSlotListUseCaseImpl
    ): GetTrackedSlotListUseCase
}