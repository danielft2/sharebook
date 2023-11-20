package com.example.sharebook.maps_feature.presentation.state

import android.location.Location
import com.example.sharebook.maps_feature.domain.model.BookMarker
import com.google.android.gms.maps.model.CameraPosition

data class MapState(
    val lastKnownLocation: Location? = null,
    val listMarkerBooks: List<BookMarker> = listOf(),
    val initialPosition: CameraPosition? = null
)