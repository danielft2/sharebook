package com.example.sharebook.exchanges_feature.presentation.request_details.channel

import com.example.sharebook.R

sealed class UpdateRequestChannel(
    val message: String? = null,
    val messageReource: Int? = null
) {
    class Success(messageReource: Int? = R.string.request_update_success): UpdateRequestChannel(
        messageReource = messageReource
    )
    class Error(message: String?): UpdateRequestChannel(message)
}
