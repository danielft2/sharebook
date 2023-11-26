package com.example.sharebook.core.presentation.navigation.components

import android.annotation.SuppressLint
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.example.sharebook.auth_feature.presentation.login.components.Login
import com.example.sharebook.auth_feature.presentation.register.components.Register
import com.example.sharebook.book_feature.presentation.external_book.components.ExternalBook
import com.example.sharebook.book_feature.presentation.self_book.components.SelfBook
import com.example.sharebook.core.presentation.navigation.NavigationViewModel
import com.example.sharebook.core.presentation.navigation.routes.authenticated.PrivateRoutes
import com.example.sharebook.core.presentation.navigation.routes.authenticated.bottomNavigationItens
import com.example.sharebook.core.presentation.navigation.routes.unauthenticated.PublicRoutes
import com.example.sharebook.core.presentation.ui.theme.*
import com.example.sharebook.core.utils.Constants
import com.example.sharebook.exchangerequest_feature.presentation.exchangerequest.components.ExchangeRequest
import com.example.sharebook.notification_feature.presentation.components.Notification
import com.example.sharebook.welcome_feature.presentation.Welcome

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NavigationRoot(
    navigationViewModel: NavigationViewModel = hiltViewModel(),
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Constants.NAVIGATION_PUBLIC) {
        navigation(
            startDestination = PublicRoutes.WelcomeScreen.route,
            route = Constants.NAVIGATION_PUBLIC
        ) {
            composable(PublicRoutes.WelcomeScreen.route) {
                RedirectRoute(navigationViewModel.isLogged, navController = navController)
                Welcome(navController = navController)
            }

            composable(PublicRoutes.LoginScreen.route) {
                RedirectRoute(navigationViewModel.isLogged, navController = navController)
                Login(navController = navController)
            }

            composable(PublicRoutes.RegisterScreen.route) {
                RedirectRoute(navigationViewModel.isLogged, navController = navController)
                Register(navController = navController)
            }
        }

        navigation(
            startDestination = PrivateRoutes.MainScreen.route,
            route = Constants.NAVIGATION_PRIVATE
        ) {
            composable(PrivateRoutes.MainScreen.route) {
                BottomNavigation(navControllerRoot = navController, itens = bottomNavigationItens)
            }
            composable(
                route = PrivateRoutes.ExternalBook.route + "/{book_id}",
                arguments = listOf(
                    navArgument(Constants.EXTERNAL_BOOK_PARAM_ID) { type = NavType.StringType }
                )
            ) {
                ExternalBook(navController = navController)
            }
            composable(PrivateRoutes.UserBook.route) {
                SelfBook(navController = navController)
            }

            composable(PrivateRoutes.ExchangeRequest.route) {
                ExchangeRequest(navController = navController)
            }

            composable(PrivateRoutes.Notification.route) {
                Notification(navController = navController)
            }
        }
    }
}
@Composable
fun RedirectRoute(isLogged: Boolean, navController: NavHostController){
    LaunchedEffect(isLogged) {
        if (isLogged) {
            navController.navigate(Constants.NAVIGATION_PRIVATE) {
                popUpTo(Constants.NAVIGATION_PUBLIC) { inclusive = true }
            }
        }
    }
}