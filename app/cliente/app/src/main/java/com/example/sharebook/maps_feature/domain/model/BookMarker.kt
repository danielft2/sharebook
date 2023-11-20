package com.example.sharebook.maps_feature.domain.model

data class BookMarker(
    val latitude: Double,
    val longitude: Double,
    val id: String?,
    val title: String,
    val userName: String
)
