package com.example.sharebook.exchanges_feature.data.remote.repository

import com.example.sharebook.exchangerequest_feature.data.remote.model.SendRequestModel
import com.example.sharebook.exchangerequest_feature.data.remote.response.SendRequestResponse
import com.example.sharebook.exchanges_feature.data.remote.response.MyBooksResponse
import com.example.sharebook.exchanges_feature.data.remote.response.RequestDetailsResponse
import com.example.sharebook.exchanges_feature.data.remote.response.RequestsReponse
import com.example.sharebook.exchanges_feature.data.remote.service.ExchangesService
import com.example.sharebook.exchanges_feature.domain.adapter.ExchangesRepository
import javax.inject.Inject

class ExchangesRepositoryImpl @Inject constructor(
    private val exchangesService: ExchangesService) : ExchangesRepository
{
    override suspend fun listMyBooks(): MyBooksResponse {
        return exchangesService.listMyBooks()
    }

    override suspend fun deleteMyBook(bookId: String) {
        return exchangesService.deleteMyBook(bookId)
    }

    override suspend fun listRequests(userId: String): RequestsReponse {
        return exchangesService.listRequests(userId)
    }

    override suspend fun requestDetails(requestId: String): RequestDetailsResponse {
        return exchangesService.requestDetails(requestId)
    }

    override suspend fun updateStatusRequest(body: SendRequestModel): SendRequestResponse {
        return exchangesService.updateStatusRequest(body)
    }
}