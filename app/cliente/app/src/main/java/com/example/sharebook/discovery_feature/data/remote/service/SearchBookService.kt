package com.example.sharebook.discovery_feature.data.remote.service

import com.example.sharebook.discovery_feature.data.remote.response.SearchBookResponse
import retrofit2.http.GET

interface SearchBookService {
    @GET("/book")
    suspend fun searchBook(): SearchBookResponse
}