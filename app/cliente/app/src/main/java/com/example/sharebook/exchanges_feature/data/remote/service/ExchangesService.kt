package com.example.sharebook.exchanges_feature.data.remote.service

import com.example.sharebook.exchanges_feature.data.remote.response.MyBooksResponse
import com.example.sharebook.exchanges_feature.data.remote.response.RequestDetailsResponse
import com.example.sharebook.exchanges_feature.data.remote.response.RequestsReponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ExchangesService {
    @GET("/book/mybooks")
    suspend fun listMyBooks(): MyBooksResponse

    @GET("/rescue/user/{user_id}")
    suspend fun listRequests(@Path("user_id") userId: String): RequestsReponse

    @GET("/rescue/{request_id}")
    suspend fun requestDetails(@Path("request_id") requestId: String): RequestDetailsResponse
}