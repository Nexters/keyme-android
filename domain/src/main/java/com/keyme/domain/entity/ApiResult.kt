package com.keyme.domain.entity

import com.keyme.domain.entity.response.WithoutDataResponse

sealed interface ApiResult<T> {
    data class Success<T>(val data: T) : ApiResult<T>

    data class ApiError(val code: String, val message: String) : ApiResult<Nothing>
    data class NetworkError(val throwable: Throwable) : ApiResult<Nothing>
}

fun <T> ApiResult<T>.onSuccess(action: (T) -> Unit): ApiResult<T> {
    if (this is ApiResult.Success) {
        action.invoke(this.data)
    }
    return this
}

fun <T> ApiResult<T>.onApiError(action: (code: String, message: String) -> Unit): ApiResult<T> {
    if (this is ApiResult.ApiError) {
        action.invoke(this.code, this.message)
    }
    return this
}

fun <T> ApiResult<T>.onFailure(action: (Throwable) -> Unit): ApiResult<T> {
    if (this is ApiResult.NetworkError) {
        action.invoke(this.throwable)
    }
    return this
}

@Suppress("UNCHECKED_CAST")
inline fun <T : Any> apiResult(call: () -> BaseResponse<T>): ApiResult<T> {
    return runCatching {
        val response = call()
        if (response.code == "200") {
            ApiResult.Success(response.data)
        } else {
            ApiResult.ApiError(response.code, response.message)
        }
    }.getOrElse {
        ApiResult.NetworkError(it)
    } as ApiResult<T>
}

@Suppress("UNCHECKED_CAST")
inline fun <T : Any> apiResultWithoutData(call: () -> WithoutDataResponse): ApiResult<T> {
    return runCatching {
        val response = call()
        if (response.code == "200") {
            ApiResult.Success(Unit)
        } else {
            ApiResult.ApiError(response.code, response.message)
        }
    }.getOrElse {
        ApiResult.NetworkError(it)
    } as ApiResult<T>
}
