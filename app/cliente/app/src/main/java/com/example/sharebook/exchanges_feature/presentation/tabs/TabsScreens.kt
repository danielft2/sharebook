package com.example.sharebook.exchanges_feature.presentation.tabs

val tabsList = listOf(
    TabScreen.MyBooksScreen,
    TabScreen.EnteredProcessScreen
)

sealed class TabScreen(val title: String) {
    object MyBooksScreen: TabScreen("Meus livros")
    object EnteredProcessScreen: TabScreen("Solicitações")
}