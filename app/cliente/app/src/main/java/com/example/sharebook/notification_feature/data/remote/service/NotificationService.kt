package com.example.sharebook.notification_feature.data.remote.service

import com.example.sharebook.discovery_feature.data.remote.response.SearchBookResponse
import com.example.sharebook.notification_feature.data.remote.response.NotificationResponse
import retrofit2.http.GET

interface NotificationService {
    @GET("/notification")
    suspend fun getNotification(): NotificationResponse
}