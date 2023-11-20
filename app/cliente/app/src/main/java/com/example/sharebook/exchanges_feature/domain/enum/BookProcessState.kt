package com.example.sharebook.exchanges_feature.domain.enum

enum class BookProcessState(val title: String) {
    CANCELED("Cancelado"),
    IN_PROGRESS("Em andamento"),
    WAITING_CONFIRMATION("Aguardando confirmação"),
    FINISHED("Finalizada")
}