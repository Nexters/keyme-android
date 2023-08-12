package com.keyme.presentation

sealed class UiState<out T : Any> {
    object Loading : UiState<Nothing>()
    data class Success<out T : Any>(
        val data: T,
    ) : UiState<T>()

    data class ApiError(
        val code: String,
        val message: String,
    ) : UiState<Nothing>()

    data class Failure(
        val throwable: Throwable,
    ) : UiState<Nothing>()
}
