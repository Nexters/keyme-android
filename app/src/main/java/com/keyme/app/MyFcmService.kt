package com.keyme.app

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import timber.log.Timber

class MyFcmService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Timber.d("remoteMessage: $remoteMessage")
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Timber.d("newToken: $token")
    }
}
