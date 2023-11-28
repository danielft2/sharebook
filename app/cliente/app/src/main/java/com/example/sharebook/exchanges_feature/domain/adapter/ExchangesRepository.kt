package com.example.sharebook.exchanges_feature.domain.adapter

import com.example.sharebook.exchanges_feature.data.remote.response.MyBooksResponse
import com.example.sharebook.exchanges_feature.data.remote.response.RequestsReponse

interface ExchangesRepository {
    suspend fun listMyBooks(): MyBooksResponse
    suspend fun listRequests(userId: String): RequestsReponse
}