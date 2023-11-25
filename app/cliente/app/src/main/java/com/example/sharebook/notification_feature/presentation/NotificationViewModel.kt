package com.example.sharebook.notification_feature.presentation

import androidx.lifecycle.ViewModel
import com.example.sharebook.notification_feature.domain.usecase.GetNotificationUsecase
import com.example.sharebook.notification_feature.presentation.event.GetNotificationEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val getNotificationUsecase: GetNotificationUsecase
): ViewModel() {
    fun onEvent(event: GetNotificationEvent) {
        when (event) {
            is GetNotificationEvent.GetNotification -> {
                getNotification()
            }
        }
    }

    private fun getNotification() {
        getNotificationUsecase()
    }
}