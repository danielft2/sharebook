package com.example.sharebook.auth_feature.presentation.register.event

sealed class RegisterFormEvent {
    data class NomeChange(val nome: String): RegisterFormEvent()
    data class TelefoneChange(val telefone: String): RegisterFormEvent()
    data class EmailChanged(val email: String): RegisterFormEvent()
    data class PasswordChanged(val password: String): RegisterFormEvent()
    data class ConfirmPasswordChange(val confirmPasswordChange: String): RegisterFormEvent()
    data class CepChange(val cep: String): RegisterFormEvent()
    data class EstadoChange(val estadoChange: String): RegisterFormEvent()
    data class CidadeChange(val cidadeChange: String): RegisterFormEvent()


    object Submit: RegisterFormEvent()
}
