package com.example.sharebook.notification_feature.data.remote.response

import com.example.sharebook.notification_feature.data.remote.mock.model.NotificationModelMock
import com.google.gson.annotations.SerializedName

data class NotificationResponse(
    @SerializedName("Foto_Livro")
    val fotoLivro: String
)

fun NotificationResponse.toUserModel(): NotificationModelMock {
    return NotificationModelMock()
}