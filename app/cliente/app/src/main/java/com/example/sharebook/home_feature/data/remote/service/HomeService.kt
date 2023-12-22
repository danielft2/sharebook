package com.example.sharebook.home_feature.data.remote.service

import com.example.sharebook.home_feature.data.remote.response.ListBooksResponse
import retrofit2.http.GET

interface HomeService {
    @GET("/book/home")
    suspend fun listBooks(): ListBooksResponse
}