package com.example.sharebook.maps_feature.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sharebook.maps_feature.data.BookMarkers
import com.example.sharebook.maps_feature.domain.usecases.DeviceLocationUseCase
import com.example.sharebook.maps_feature.presentation.state.MapState
import com.example.sharebook.maps_feature.utils.DeviceLocationResult
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor(
    val deviceLocationUseCase: DeviceLocationUseCase
): ViewModel() {
    private val navigationChangeChanel = Channel<Boolean>()
    val navigationChangeEvent = navigationChangeChanel.receiveAsFlow()

    var state by mutableStateOf(MapState())
        private set

    init {
        state = state.copy(
            listMarkerBooks = BookMarkers,
            initialPosition = CameraPosition.fromLatLngZoom(
                LatLng(
                -9.592679513516732,
                -56.03762900973237),
                2f
            )
        )
    }


    fun getDeviceLocation(
        fusedLocationProviderClient: FusedLocationProviderClient
    ) {
        viewModelScope.launch {
            deviceLocationUseCase(fusedLocationProviderClient).collect {
                when (it) {
                    is DeviceLocationResult.Success -> {
                        state = state.copy(lastKnownLocation = it.location)
                    }
                    is DeviceLocationResult.Error -> {}
                }
            }
        }
    }

    fun clickMarked() {
        viewModelScope.launch {
            navigationChangeChanel.send(true)
        }
    }
}