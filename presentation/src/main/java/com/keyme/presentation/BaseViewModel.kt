package com.keyme.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keyme.domain.entity.ApiResult
import com.keyme.domain.entity.onApiError
import com.keyme.domain.entity.onFailure
import com.keyme.domain.entity.onSuccess
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import timber.log.Timber
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {

    val baseViewModelScope = viewModelScope + CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable)
    }

    @Inject
    lateinit var uiEventManager: UiEventManager

    fun <T> apiCall(apiRequest: suspend () -> ApiResult<T>, action: (T) -> Unit) {
        baseViewModelScope.launch {
            apiRequest().onSuccess {
                action(it)
            }.onApiError { code, message ->
                baseViewModelScope.launch {
                    uiEventManager.onEvent(UiEvent.Toast("($code) $message"))
                }

            }.onFailure {
                baseViewModelScope.launch {
                    uiEventManager.onEvent(UiEvent.Toast(it.message ?: ""))
                }
            }
        }
    }
}
