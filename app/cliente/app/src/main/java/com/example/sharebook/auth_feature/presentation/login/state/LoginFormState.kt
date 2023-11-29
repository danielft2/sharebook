package com.example.sharebook.auth_feature.presentation.login.state

import com.example.sharebook.auth_feature.data.remote.model.LoginModel
import com.example.sharebook.core.utils.UiText

data class LoginFormState (
    val email: String = "",
    val emailError: UiText = UiText.DynamicString(""),

    val password: String = "",
    val passwordError: UiText = UiText.DynamicString("")
)

fun LoginFormState.toLoginModel() : LoginModel {
    return LoginModel(
        email = email,
        password = password
    )
}