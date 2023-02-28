package com.example.firebasefcm.service

import com.example.firebasefcm.model.NotificationData
import com.example.firebasefcm.model.PushNotification
import com.example.firebasefcm.utils.Constans.Companion.CONTENT_TYPE
import com.example.firebasefcm.utils.Constans.Companion.SERVER_KEY
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface NotificationService {

    @Headers("Authorization: key=$SERVER_KEY", "Content-Type: $CONTENT_TYPE")
    @POST("fcm/send")
    suspend fun postNotification(
        @Body notification: PushNotification
    ) : Response<ResponseBody>

}