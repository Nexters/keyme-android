package com.keyme.presentation

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

sealed interface UiEvent {
    class Toast(val message: String) : UiEvent
}

@Singleton
class UiEventManager @Inject constructor() {
    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    suspend fun onEvent(event: UiEvent) {
        _uiEvent.emit(event)
    }
}
