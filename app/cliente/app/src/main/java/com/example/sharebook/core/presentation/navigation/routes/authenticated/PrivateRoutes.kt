package com.example.sharebook.core.presentation.navigation.routes.authenticated

import com.example.sharebook.core.utils.Constants

sealed class PrivateRoutes(val route: String) {
    object MainScreen: PrivateRoutes(Constants.MAIN_ROUTE)
    object HomeScreen: PrivateRoutes(Constants.HOME_ROUTE)
    object DiscoveryScreen: PrivateRoutes(Constants.DISCOVERY_ROUTE)
    object ExchangesScreen: PrivateRoutes(Constants.EXCHANGES_ROUTE)
    object MapsScreen: PrivateRoutes(Constants.MAPS_ROUTE)
    object AddBookScreen: PrivateRoutes(Constants.ADD_BOOK_ROUTE)
}
