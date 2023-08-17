package com.keyme.presentation.takekeymetest

import androidx.lifecycle.SavedStateHandle
import com.keyme.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TakeKeymeTestViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : BaseViewModel() {
    private val testId = savedStateHandle[TakeKeymeTestDestination.testIdArg] ?: 0
    val keymeTestUrl = "https://keyme-frontend.vercel.app/test/$testId"
}
