package tech.eliseo.timetracker.data.network.datasource

import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import tech.eliseo.timetracker.domain.model.TrackedSlot
import javax.inject.Inject

class FirebaseTrackedSlotDatasource @Inject constructor(
    val database: DatabaseReference
) : NetworkTrackedSlotDatasource {

    @ExperimentalCoroutinesApi
    override fun saveTrackedSlotList(trackedSlotList: List<TrackedSlot>) = callbackFlow {
        database.setValue(trackedSlotList) { error, ref ->
            if (error != null) {
                this@callbackFlow.cancel(error.message, error.toException())
            } else {
                this@callbackFlow.trySendBlocking(Unit)
            }
        }
    }

    override fun saveTrackedSlot(trackedSlot: TrackedSlot): Flow<Unit> = callbackFlow {
        database.setValue(trackedSlot) { error, ref ->
            if (error != null) {
                this@callbackFlow.cancel(error.message, error.toException())
            } else {
                this@callbackFlow.trySendBlocking(Unit)
            }
        }
    }
}