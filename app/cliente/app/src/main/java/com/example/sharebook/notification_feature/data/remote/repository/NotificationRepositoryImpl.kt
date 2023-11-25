package com.example.sharebook.notification_feature.data.remote.repository

import com.example.sharebook.notification_feature.data.remote.service.NotificationService
import com.example.sharebook.notification_feature.domain.adapter.NotificationRepository
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor(private val notificationService: NotificationService):
    NotificationRepository {
        override suspend fun getNotification() {
            println("Chamada para buscar notificações na API")
        }
}