package com.example.sharebook.core.utils

object Constants {
    // Remote
    const val VIA_CEP_URL = "https://viacep.com.br/ws/"

    // Data Storage
    const val STORAGE_USER_KEY = "USER_KEY"
    const val STORAGE_TOKEN_KEY = "TOKEN_KEY"

    // Navigation Public
    const val NAVIGATION_PUBLIC = "unauthenticated"
    const val WELCOME_ROUTE = "welcome_route"
    const val LOGIN_ROUTE = "login_route"
    const val REGISTER_ROUTE = "register_route"

    // Navigation Private
    const val NAVIGATION_PRIVATE = "authenticated"
    const val MAIN_ROUTE = "app_route"
    const val HOME_ROUTE = "home_route"
    const val DISCOVERY_ROUTE = "discovery_route"
    const val EXCHANGES_ROUTE = "exchanges_route"
    const val MAPS_ROUTE = "maps_route"
    const val EXTERNAL_BOOK_ROUTE = "external_book_route"
    const val USER_BOOK_ROUTE = "user_book_route"
    const val BOOK_PROCESS_ROUTE = "book_process_route"
    const val NOTIFICATION_ROUTE = "notification_route"
}