package com.example.sharebook.maps_feature.utils
import android.location.Location

sealed class DeviceLocationResult(val location: Location? = null, val errorResId: Int? = null) {
    class Success(location: Location?): DeviceLocationResult(location = location)
    class Error(errorResId: Int?): DeviceLocationResult(errorResId = errorResId)
}