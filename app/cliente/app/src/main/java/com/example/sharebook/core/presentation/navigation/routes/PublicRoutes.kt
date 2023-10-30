package com.example.sharebook.core.presentation.navigation.routes

import com.example.sharebook.core.utils.Constants

sealed class PublicRoutes(val route: String) {
    object LoginScreen: PublicRoutes(Constants.LOGIN_ROUTE)
    object RegisterScreen: PublicRoutes(Constants.REGISTER_ROUTE)
}