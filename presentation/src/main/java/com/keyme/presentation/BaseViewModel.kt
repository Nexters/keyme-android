package com.keyme.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.plus

abstract class BaseViewModel : ViewModel() {

    val baseViewModelScope = viewModelScope + CoroutineExceptionHandler { _, throwable ->
        Log.e("", "throwable: $throwable")
    }
}
