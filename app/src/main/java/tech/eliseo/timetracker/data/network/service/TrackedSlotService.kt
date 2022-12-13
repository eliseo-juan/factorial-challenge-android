package tech.eliseo.timetracker.data.network.service

import retrofit2.http.Body
import retrofit2.http.POST
import tech.eliseo.timetracker.data.network.dto.NetworkTrackedSlot

interface TrackedSlotService {

    @POST(value = "trackedSlots")
    suspend fun setTrackedSlotList(
        @Body trackedSlot: List<NetworkTrackedSlot>,
    )

    @POST(value = "trackedSlot")
    suspend fun setTrackedSlot(
        @Body trackedSlot: NetworkTrackedSlot,
    )
}