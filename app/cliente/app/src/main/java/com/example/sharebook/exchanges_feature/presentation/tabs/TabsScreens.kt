package com.example.sharebook.exchanges_feature.presentation.tabs

val tabsList = listOf(
    TabScreen.MyBooksScreen,
    TabScreen.EnteredProcessScreen
)

sealed class TabScreen(
    val title: String,
    val badgeValue: Int
) {
    object MyBooksScreen: TabScreen("Meus livros", 3)
    object EnteredProcessScreen: TabScreen("Solicitações", 0)
}