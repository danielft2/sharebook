package com.example.sharebook.auth_feature.presentation.register.state

import com.example.sharebook.auth_feature.data.remote.model.RegisterModel
import com.example.sharebook.core.utils.UiText

data class RegisterFormState(
    val nome: String = "",
    val nomeError: UiText = UiText.DynamicText(""),

    val telefone: String = "",
    val telefoneError: UiText = UiText.DynamicText(""),

    val email: String = "",
    val emailError: UiText = UiText.DynamicText(""),

    val password: String = "",
    val passwordError: UiText = UiText.DynamicText(""),

    val confirmPassword: String = "",
    val confirmPasswordError: UiText = UiText.DynamicText(""),

    val cep: String = "",
    val cepSearchIsLoading: Boolean = false,
    val cepError: UiText = UiText.DynamicText(""),

    val estado: String = "",
    val estadoError: UiText = UiText.DynamicText(""),

    val cidade: String = "",
    val cidadeError: UiText = UiText.DynamicText("")
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