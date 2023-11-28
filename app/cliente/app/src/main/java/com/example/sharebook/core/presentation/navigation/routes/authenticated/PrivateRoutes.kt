package com.example.sharebook.core.presentation.navigation.routes.authenticated

import com.example.sharebook.core.utils.Constants

sealed class PrivateRoutes(val route: String) {
    object MainScreen: PrivateRoutes(Constants.MAIN_ROUTE)
    object HomeScreen: PrivateRoutes(Constants.HOME_ROUTE)
    object DiscoveryScreen: PrivateRoutes(Constants.DISCOVERY_ROUTE)
    object ExchangesScreen: PrivateRoutes(Constants.EXCHANGES_ROUTE)
    object MapsScreen: PrivateRoutes(Constants.MAPS_ROUTE)
    object ExternalBook: PrivateRoutes(Constants.EXTERNAL_BOOK_ROUTE)
    object UserBook: PrivateRoutes(Constants.USER_BOOK_ROUTE)
    object ExchangeRequest: PrivateRoutes(Constants.EXCHANGE_REQUEST_ROUTE)
    object RequestDetails: PrivateRoutes(Constants.REQUEST_DETAILS_ROUTE)
    object Notification: PrivateRoutes(Constants.NOTIFICATION_ROUTE)

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
