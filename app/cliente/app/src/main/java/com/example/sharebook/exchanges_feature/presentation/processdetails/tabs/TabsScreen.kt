package com.example.sharebook.exchanges_feature.presentation.processdetails.tabs

import com.example.sharebook.R

val listTabs = listOf(
    TabsScreen.InformationsScreen,
    TabsScreen.ProcessScreen
)

sealed class TabsScreen(val name: String, val icon: Int) {
    object InformationsScreen: TabsScreen("Informações", R.drawable.icon_infor)
    object ProcessScreen: TabsScreen("Processo", R.drawable.icon_arrow_sync)
}
