package com.example.sharebook.exchanges_feature.data.remote.repository

import com.example.sharebook.exchanges_feature.data.remote.responses.MyBooksResponse
import com.example.sharebook.exchanges_feature.data.remote.service.ExchangesService
import com.example.sharebook.exchanges_feature.domain.adapter.ExchangesRepository
import javax.inject.Inject

class ExchangesRepositoryImpl @Inject constructor(
    private val exchangesService: ExchangesService) : ExchangesRepository
{
    override suspend fun listMyBooks(): MyBooksResponse {
        return exchangesService.listMyBooks()
    }
}