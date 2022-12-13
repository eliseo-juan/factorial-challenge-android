package tech.eliseo.timetracker.data.network.datasource

import kotlinx.coroutines.flow.Flow
import tech.eliseo.timetracker.domain.model.TrackedSlot

interface NetworkTrackedSlotDatasource {

    fun saveTrackedSlotList(trackedSlotList: List<TrackedSlot>) : Flow<Unit>

    fun saveTrackedSlot(trackedSlot: TrackedSlot) : Flow<Unit>
}