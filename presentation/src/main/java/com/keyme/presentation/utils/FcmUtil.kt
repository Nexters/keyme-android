package com.keyme.presentation.utils

import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.suspendCancellableCoroutine
import timber.log.Timber
import kotlin.coroutines.resume

object FcmUtil {

    suspend fun getToken() = suspendCancellableCoroutine { continuation ->
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                Timber.e(task.exception)
                continuation.resume(null)
            } else {
                continuation.resume(task.result)
            }
        }
    }
}
