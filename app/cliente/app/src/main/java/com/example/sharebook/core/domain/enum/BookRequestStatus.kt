package com.example.sharebook.core.domain.enum

enum class BookRequestStatus(val tag: String) {
    SEND("Aguardando Confirmação"),
    CANCEL("Solicitação Cancelada / Rejeitada"),
    FINALIZE("Solicitação Finalizada"),
    ACCEPTED("Solicitação Aceita")
}