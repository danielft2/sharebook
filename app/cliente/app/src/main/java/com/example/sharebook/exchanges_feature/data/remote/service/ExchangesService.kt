package com.example.sharebook.exchanges_feature.data.remote.service

import com.example.sharebook.exchanges_feature.data.remote.responses.MyBooksResponse
import retrofit2.http.GET

interface ExchangesService {
    @GET("/book/mybooks")
    suspend fun listMyBooks(): MyBooksResponse
}