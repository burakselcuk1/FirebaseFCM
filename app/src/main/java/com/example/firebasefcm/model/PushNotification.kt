package com.example.firebasefcm.model

data class PushNotification(
    var data: NotificationData,
    var to: String
)
