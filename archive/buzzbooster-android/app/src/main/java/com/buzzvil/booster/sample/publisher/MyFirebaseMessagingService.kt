package com.buzzvil.booster.sample.publisher

import com.buzzvil.booster.external.BuzzBooster
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        BuzzBooster.setFCMToken(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        val data = message.data

        if (BuzzBooster.isBuzzBoosterNotification(data)) {
            BuzzBooster.handleNotification(data)
        } else {
            // Enter your code
        }
    }
}
