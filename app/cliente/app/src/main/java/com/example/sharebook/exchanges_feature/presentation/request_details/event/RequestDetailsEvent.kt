package com.example.sharebook.exchanges_feature.presentation.request_details.event

import com.example.sharebook.core.domain.enum.BookRequestStatus

sealed class RequestDetailsEvent(status: BookRequestStatus? = null) {
    data class UpdateStatusRequest(val status: BookRequestStatus?): RequestDetailsEvent(status)
    object ListDetails: RequestDetailsEvent()
}
