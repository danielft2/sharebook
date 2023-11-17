package com.example.sharebook.auth_feature.presentation.login.state

data class LoginRequestState(
    val isLoading: Boolean = false,
    val success: Boolean = false,
    val error: String? = ""
)