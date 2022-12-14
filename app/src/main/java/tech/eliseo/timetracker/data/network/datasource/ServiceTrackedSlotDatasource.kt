package tech.eliseo.timetracker.data.network.datasource

import tech.eliseo.timetracker.data.network.mapper.TrackedSlotNetworkMapper
import tech.eliseo.timetracker.data.network.service.TrackedSlotService
import tech.eliseo.timetracker.domain.model.TrackedSlot
import javax.inject.Inject

class ServiceTrackedSlotDatasource @Inject constructor(
    private val trackedSlotService: TrackedSlotService
) : NetworkTrackedSlotDatasource, TrackedSlotNetworkMapper {
    override suspend fun saveTrackedSlotList(trackedSlotList: List<TrackedSlot>) {
        trackedSlotService.setTrackedSlotList(trackedSlotList.map { it.toNetworkTrackedSlot() })
    }

    override suspend fun saveTrackedSlot(trackedSlot: TrackedSlot) {
        trackedSlotService.setTrackedSlot(trackedSlot.toNetworkTrackedSlot())
    }
}