package com.example.sharebook.core.domain.enum

enum class BookRequestStatus(val tag: String) {
    SEND("Arguadando Confirmação"),
    CANCEL("Solicitação Cancelada / Rejeitada"),
    FINALIZE("Solicitação Finalizada")
}