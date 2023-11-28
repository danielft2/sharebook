package com.example.sharebook.exchanges_feature.data.remote.service

import com.example.sharebook.exchanges_feature.data.remote.responses.MyBooksResponse
import com.example.sharebook.exchanges_feature.data.remote.responses.RequestsReponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ExchangesService {
    @GET("/book/mybooks")
    suspend fun listMyBooks(): MyBooksResponse

    @GET("/rescue/user/{user_id}")
    suspend fun listRequests(@Path("user_id") userId: String): RequestsReponse
}