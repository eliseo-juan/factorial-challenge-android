package tech.eliseo.timetracker.data.network.datasource

import tech.eliseo.timetracker.domain.model.TrackedSlot

interface NetworkTrackedSlotDatasource {

    suspend fun saveTrackedSlotList(trackedSlotList: List<TrackedSlot>)

    suspend fun saveTrackedSlot(trackedSlot: TrackedSlot)
}