package com.example.sharebook.exchanges_feature.presentation.requests.state

import com.example.sharebook.core.domain.model.UserModel
import com.example.sharebook.exchanges_feature.domain.model.RequestModel

data class UiState(
    val isLoading: Boolean = false,
    val isError: String? = null,
    val requestsList: List<RequestModel> = listOf(),

    val userLogged: UserModel? = null
)
