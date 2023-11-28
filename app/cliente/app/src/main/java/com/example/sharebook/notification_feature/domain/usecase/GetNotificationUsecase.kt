package com.example.sharebook.notification_feature.domain.usecase

import com.example.sharebook.notification_feature.domain.adapter.NotificationRepository
import javax.inject.Inject

class GetNotificationUsecase @Inject constructor(private val notificationRepository: NotificationRepository) {
    operator fun invoke() {
        println("Realizando a chamada de buscar notificações na API")
    }
}