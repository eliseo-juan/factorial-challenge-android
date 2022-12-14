package tech.eliseo.timetracker.data.network.datasource

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import tech.eliseo.timetracker.data.network.dto.toHashMap
import tech.eliseo.timetracker.data.network.mapper.TrackedSlotNetworkMapper
import tech.eliseo.timetracker.domain.model.TrackedSlot
import java.io.IOException
import javax.inject.Inject

class FirebaseTrackedSlotDatasource @Inject constructor(
    private val db: FirebaseFirestore
) : NetworkTrackedSlotDatasource, TrackedSlotNetworkMapper {

    override suspend fun saveTrackedSlotList(trackedSlotList: List<TrackedSlot>) {
        db.collection("users")
            .document("factorial_test_user")
            .collection("trackedSlots")
            .add(
                trackedSlotList.map { it.toNetworkTrackedSlot().toHashMap() }
            )
            .addOnFailureListener {
                throw IOException()
            }.await()
    }

    override suspend fun saveTrackedSlot(trackedSlot: TrackedSlot) {
        db.collection("users")
            .document("factorial_test_user")
            .collection("trackedSlots")
            .add(
                trackedSlot.toNetworkTrackedSlot().toHashMap()
            )
            .addOnFailureListener {
                throw IOException()
            }.await()
    }
}