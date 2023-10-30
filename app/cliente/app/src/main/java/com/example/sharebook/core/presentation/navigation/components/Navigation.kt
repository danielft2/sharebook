package com.example.sharebook.core.presentation.navigation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.sharebook.auth_feature.presentation.login.components.Login
import com.example.sharebook.auth_feature.presentation.register.components.Register
import com.example.sharebook.core.presentation.navigation.NavigationViewModel
import com.example.sharebook.core.presentation.navigation.routes.PrivateRoutes
import com.example.sharebook.core.presentation.navigation.routes.PublicRoutes
import com.example.sharebook.core.utils.Constants
import com.example.sharebook.home_feature.presentation.Home

@Composable
fun Navigation(
    navigationViewModel: NavigationViewModel = hiltViewModel(),
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Constants.NAVIGATION_PUBLIC) {
        navigation(
            startDestination = PublicRoutes.LoginScreen.route,
            route = Constants.NAVIGATION_PUBLIC
        ) {
            composable(PublicRoutes.LoginScreen.route) {
                RedirectRoute(navigationViewModel = navigationViewModel, navController = navController)
                Login(navController = navController)
            }

            composable(PublicRoutes.RegisterScreen.route) {
                RedirectRoute(navigationViewModel = navigationViewModel, navController = navController)
                Register(navController = navController)
            }
        }

        navigation(
            startDestination = PrivateRoutes.HomeScreen.route,
            route = Constants.NAVIATION_PRIVATE
        ) {
            composable(PrivateRoutes.HomeScreen.route) { Home() }
        }
    }
}
@Composable
fun RedirectRoute(navigationViewModel: NavigationViewModel, navController: NavHostController) {
    LaunchedEffect(navigationViewModel.isLogged) {
        if (navigationViewModel.isLogged) {
            navController.navigate(Constants.NAVIATION_PRIVATE) {
                popUpTo(Constants.NAVIGATION_PUBLIC) { inclusive = true }
            }
        }

    }
}