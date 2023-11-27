package com.example.sharebook.exchangerequest_feature.presentation.exchangerequest.channel

sealed class SendRequestChannel(val message: String? = null) {
    class ErrorRequest(message: String? = null): SendRequestChannel(message)
    class SuccessRequest() : SendRequestChannel()
}
