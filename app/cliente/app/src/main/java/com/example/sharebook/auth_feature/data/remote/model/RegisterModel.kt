package com.example.sharebook.auth_feature.data.remote.model

data class RegisterModel(
    val nome: String,
    val telefone: String,
    val email: String,
    val senha: String,
    val cep: String,
    val estado: String,
    val cidade: String
)