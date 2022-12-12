package tech.eliseo.timetracker.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<State : UiState> : ViewModel() {
    abstract val uiState: StateFlow<State>
}