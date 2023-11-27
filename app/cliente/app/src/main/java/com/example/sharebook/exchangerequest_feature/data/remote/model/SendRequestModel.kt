package com.example.sharebook.exchangerequest_feature.data.remote.model
import com.example.sharebook.core.domain.enum.BookRequestStatus

data class SendRequestModel(
    val idRescueUser: String,
    val idBook: String,
    val idBookFromRescue: String,
    val status: BookRequestStatus
)
