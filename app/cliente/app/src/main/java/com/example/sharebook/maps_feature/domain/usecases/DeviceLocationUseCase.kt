package com.example.sharebook.maps_feature.domain.usecases

import android.annotation.SuppressLint
import com.example.sharebook.R
import com.example.sharebook.maps_feature.utils.DeviceLocationResult
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class DeviceLocationUseCase {
    @SuppressLint("MissingPermission")
    suspend operator fun invoke(fusedLocationProviderClient: FusedLocationProviderClient) : Flow<DeviceLocationResult> = callbackFlow {
        try {
            val locationResult = fusedLocationProviderClient.lastLocation.await()
            trySend(DeviceLocationResult.Success(locationResult)).isSuccess
        } catch (e: SecurityException) {
            trySend(DeviceLocationResult.Error(errorResId = R.string.maps_device_location))
            close(e)
        }

        awaitClose {}
    }
}