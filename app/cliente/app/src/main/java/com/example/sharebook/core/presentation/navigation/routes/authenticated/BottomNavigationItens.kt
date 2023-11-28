package com.example.sharebook.core.presentation.navigation.routes.authenticated
import com.example.sharebook.R

val bottomNavigationItens = listOf(
    BottomNavigationItem(
        title = R.string.home_route_title,
        route = PrivateRoutes.HomeScreen.route,
        icon = R.drawable.icon_home
    ),
//    BottomNavigationItem(
//        title = R.string.discovery_route_title,
//        route = PrivateRoutes.DiscoveryScreen.route,
//        icon = R.drawable.icon_search
//    ),
    BottomNavigationItem(
        title = R.string.exchanges_route_title,
        route = PrivateRoutes.ExchangesScreen.route,
        icon = R.drawable.icon_exchanges
    ),
    BottomNavigationItem(
        title = R.string.maps_route_title,
        route = PrivateRoutes.MapsScreen.route,
        icon = R.drawable.icon_maps
    )
)