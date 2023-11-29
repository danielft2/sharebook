package com.example.sharebook.maps_feature.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.sharebook.core.presentation.navigation.routes.authenticated.PrivateRoutes
import com.example.sharebook.maps_feature.presentation.MapsViewModel
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@Composable
fun Maps(
    navController: NavHostController,
    mapsViewModel: MapsViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val cameraPositionState = rememberCameraPositionState {
        position = mapsViewModel.state.initialPosition!!
    }

    LaunchedEffect(context) {
        mapsViewModel.navigationChangeEvent.collect {
            navController.navigate(PrivateRoutes.ExternalBook.withArgs(it))
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            properties = MapProperties(
                isMyLocationEnabled = mapsViewModel.state.lastKnownLocation != null
            ),
            cameraPositionState = cameraPositionState
        ) {
            mapsViewModel.state.listMarkerBooks.forEach { marker ->
                Marker(
                    state = rememberMarkerState(position = LatLng(marker.latitude, marker.longitude)),
                    title = marker.title,
                    snippet = marker.userName,
                    onInfoWindowClick = { mapsViewModel.clickMarked(marker.id ?: "") },
                    onClick = {
                        it.showInfoWindow()
                        true
                    }
                )
            }
        }
    }
}