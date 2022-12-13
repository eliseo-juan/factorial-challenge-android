package tech.eliseo.timetracker.ui.screen.tracked_slot_dialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import tech.eliseo.timetracker.domain.model.Category
import tech.eliseo.timetracker.domain.model.TrackedSlot
import tech.eliseo.timetracker.ui.preview.FakePreviewData


@Composable
private fun TrackedSlotDialogScreen(
    trackedSlot: TrackedSlot,
    categories: List<Category>
) {
    Dialog(onDismissRequest = { /*TODO*/ }) {

    }
}


@Preview
@Composable
fun TrackedSlotDialogScreenPreview() {
    TrackedSlotDialogScreen(
        trackedSlot = FakePreviewData.getListOfTrackedSlot().first(),
        categories = FakePreviewData.getCategories()
    )
}