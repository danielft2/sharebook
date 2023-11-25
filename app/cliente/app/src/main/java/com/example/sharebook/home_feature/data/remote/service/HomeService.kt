package com.example.sharebook.home_feature.data.remote.service

import com.example.sharebook.home_feature.data.remote.response.ListBooksResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeService {
    @GET("/book/home/{usuario_id}")
    suspend fun listBooks(@Path("usuario_id") usuario_id: String): ListBooksResponse
}