package com.example.sharebook.exchanges_feature.domain.model

import com.example.sharebook.core.domain.enum.BookRequestStatus
import com.example.sharebook.core.domain.model.BookSummaryModel

data class RequestDetailsModel(
    val userLoggedBook: BookSummaryModel,

    val userExternalBook: BookSummaryModel,
    val userExternalPhone: String,
    val userExternalLocation: String,

    val userRequestId: String,
    val status: BookRequestStatus
)
