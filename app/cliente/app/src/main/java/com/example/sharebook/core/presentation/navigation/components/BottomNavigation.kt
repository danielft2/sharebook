package com.example.sharebook.core.presentation.navigation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sharebook.core.presentation.navigation.routes.authenticated.BottomNavigationItem
import com.example.sharebook.core.presentation.navigation.routes.authenticated.PrivateRoutes
import com.example.sharebook.core.presentation.ui.theme.*
import com.example.sharebook.core.utils.Constants
import com.example.sharebook.core.utils.UiText
import com.example.sharebook.discovery_feature.presentation.components.Discovery
import com.example.sharebook.home_feature.presentation.components.Home
import com.example.sharebook.maps_feature.presentation.components.Maps

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BottomNavigation(
    navControllerRoot: NavHostController,
    itens: List<BottomNavigationItem>
) {
    val navBottomController = rememberNavController()
    val backStackEntry = navBottomController.currentBackStackEntryAsState()

    Scaffold(bottomBar = {
        androidx.compose.material.BottomNavigation(
            modifier = Modifier.clip(RoundedCornerShape(12.dp, 12.dp, 0.dp, 0.dp)),
            elevation = 40.dp,
            backgroundColor = white,
        ) {
            itens.forEach { item ->
                val currentRoute = backStackEntry.value?.destination?.route
                val itemSelected = item.route == currentRoute

                BottomNavigationItem (
                    selected = itemSelected,
                    onClick = {
                        navBottomController.navigate(item.route) {
                            popUpTo(navBottomController.graph.findStartDestination().id) { saveState = true }
                        }
                              },
                    selectedContentColor = green500,
                    unselectedContentColor = gray500,
                    icon = {
                        Icon(
                            painter = painterResource(item.icon),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                            tint = if (itemSelected) green300 else gray300
                        )
                    },
                    label = {
                        Text(
                            text = UiText.StringResource(item.title).asString(),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = Inter,
                            color = if (itemSelected) green500 else gray500
                        )
                    }
                )
            }
        }
    }) {
        NavHost(navController = navBottomController, startDestination = Constants.HOME_ROUTE) {
            composable(PrivateRoutes.HomeScreen.route) { Home(navControllerRoot) }
            composable(PrivateRoutes.DiscoveryScreen.route) { Discovery(navController = navControllerRoot) }
            composable(PrivateRoutes.MapsScreen.route) { Maps() }
        }
    }
}