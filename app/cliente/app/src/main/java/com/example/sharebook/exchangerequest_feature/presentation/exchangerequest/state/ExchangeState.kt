package com.example.sharebook.exchangerequest_feature.presentation.exchangerequest.state

import com.example.sharebook.core.domain.model.BookUserLoggedModel

data class ExchangeState(
    val checkbox: Boolean = false,
    val bookSelected: BookUserLoggedModel? = null
)