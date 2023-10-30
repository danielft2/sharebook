package com.example.sharebook.core.presentation.navigation.routes

import com.example.sharebook.core.utils.Constants

sealed class PrivateRoutes(val route: String) {
    object HomeScreen: PrivateRoutes(Constants.HOME_ROUTE)
}
