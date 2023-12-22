package com.example.sharebook.notification_feature.domain.adapter

interface NotificationRepository {
    suspend fun getNotification()
}