package com.example.sharebook.exchanges_feature.domain.adapter

import com.example.sharebook.exchanges_feature.data.remote.responses.MyBooksResponse

interface ExchangesRepository {
    suspend fun listMyBooks(): MyBooksResponse
}