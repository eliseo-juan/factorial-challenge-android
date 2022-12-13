package tech.eliseo.timetracker.data.network.datasource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import tech.eliseo.timetracker.data.network.mapper.TrackedSlotNetworkMapper
import tech.eliseo.timetracker.data.network.service.TrackedSlotService
import tech.eliseo.timetracker.domain.model.TrackedSlot
import javax.inject.Inject

class ServiceTrackedSlotDatasource @Inject constructor(
    private val trackedSlotService: TrackedSlotService
) : NetworkTrackedSlotDatasource, TrackedSlotNetworkMapper {

    override fun saveTrackedSlotList(trackedSlotList: List<TrackedSlot>): Flow<Unit> =
        callbackFlow {
            send(trackedSlotService.setTrackedSlotList(trackedSlotList.map { it.toNetworkTrackedSlot() }))
        }

    override fun saveTrackedSlot(trackedSlot: TrackedSlot): Flow<Unit> =
        callbackFlow {
            send(trackedSlotService.setTrackedSlot(trackedSlot.toNetworkTrackedSlot()))
        }
}