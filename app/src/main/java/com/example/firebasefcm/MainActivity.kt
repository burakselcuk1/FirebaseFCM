package com.example.firebasefcm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebasefcm.databinding.ActivityMainBinding
import com.example.firebasefcm.model.NotificationData
import com.example.firebasefcm.model.PushNotification
import com.example.firebasefcm.service.RetrofitObject
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val topic = "/topics/genelduyurular"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseMessaging.getInstance().subscribeToTopic(topic)

        with(binding){
            val baslik = editTextTextPersonName.text.toString()
            val mesaj = editTextTextPersonName2.text.toString()

            if (baslik != "" && mesaj != "") {
                val data = NotificationData(baslik, mesaj)
                val notification = PushNotification (data,topic)
                notificationYolla(notification)
            }
        }
    }


    private fun notificationYolla(notification: PushNotification) = CoroutineScope(Dispatchers.IO).launch {
        try {

            val cevap = RetrofitObject.api.postNotification(notification)
            if (cevap.isSuccessful){
               // println(Gson().toJson(cevap))
            }
            else{
               // println(cevap.errorBody().toString())
            }

        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}