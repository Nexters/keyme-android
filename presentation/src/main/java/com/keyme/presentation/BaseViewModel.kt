package com.keyme.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.plus
import timber.log.Timber

abstract class BaseViewModel : ViewModel() {

    val baseViewModelScope = viewModelScope + CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable)
    }
}
