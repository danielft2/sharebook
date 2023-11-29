package com.example.sharebook.auth_feature.presentation.register.state

import com.example.sharebook.auth_feature.data.remote.model.RegisterModel
import com.example.sharebook.core.utils.UiText

data class RegisterFormState(
    val nome: String = "",
    val nomeError: UiText = UiText.DynamicString(""),

    val telefone: String = "",
    val telefoneError: UiText = UiText.DynamicString(""),

    val email: String = "",
    val emailError: UiText = UiText.DynamicString(""),

    val password: String = "",
    val passwordError: UiText = UiText.DynamicString(""),

    val confirmPassword: String = "",
    val confirmPasswordError: UiText = UiText.DynamicString(""),

    val cep: String = "",
    val cepSearchIsLoading: Boolean = false,
    val cepError: UiText = UiText.DynamicString(""),

    val estado: String = "",
    val estadoError: UiText = UiText.DynamicString(""),

    val cidade: String = "",
    val cidadeError: UiText = UiText.DynamicString("")
)

fun RegisterFormState.toRegisterModel() : RegisterModel {
    return RegisterModel(
        nome = nome,
        cidade = cidade,
        estado = estado,
        senha = password,
        email = email,
        cep = cep,
        telefone = telefone
    )
}