package com.example.sharebook.auth_feature.presentation.register.state

data class RegisterRequestState (
    val isLoading: Boolean = false,
    val sucess: Boolean = false,
    val error: String? = ""
)
