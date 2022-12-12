package tech.eliseo.timetracker.domain

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tech.eliseo.timetracker.domain.usecase.*

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {

    @Binds
    fun bindsGetLastTrackedSlotUseCase(
        useCase: GetTodayTrackedSlotListUseCaseImpl
    ): GetTodayTrackedSlotListUseCase

    @Binds
    fun bindsGetTrackedSlotListUseCase(
        useCase: GetTrackedSlotListUseCaseImpl
    ): GetTrackedSlotListUseCase

    @Binds
    fun bindsGetCurrentTrackingUseCase(
        useCase: GetCurrentTrackingUseCaseImpl
    ): GetCurrentTrackingUseCase

    @Binds
    fun bindsOnToggleTrackerUseCase(
        useCase: OnToggleTrackerUseCaseImpl
    ): OnToggleTrackerUseCase

    @Binds
    fun bindsGetCategoryListUseCase(
        useCase: GetCategoryListUseCaseImpl
    ): GetCategoryListUseCase

    @Binds
    fun bindsAssignCategoryUseCase(
        useCase: AssignCategoryUseCaseImpl
    ): AssignCategoryUseCase
}