package com.example.sharebook.exchanges_feature.domain.model

import com.example.sharebook.core.domain.model.BookSummaryModel

data class RequestDetailsModel(
    val extertalUserRequest: BookSummaryModel,
    val userLoggedRequest: BookSummaryModel
)
