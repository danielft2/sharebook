package com.example.sharebook.exchanges_feature.presentation.request_details.state

import com.example.sharebook.core.domain.model.UserModel
import com.example.sharebook.exchanges_feature.domain.model.RequestDetailsModel

data class UiState(
    val userLogged: UserModel? = null,

    val isLoadingDetails: Boolean = false,
    val isErrorDetails: String? = "",
    val requestDetails: RequestDetailsModel? = null,
)