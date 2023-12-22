package com.example.sharebook.exchangerequest_feature.data.remote.repository

import com.example.sharebook.exchangerequest_feature.data.remote.model.SendRequestModel
import com.example.sharebook.exchangerequest_feature.data.remote.response.SendRequestResponse
import com.example.sharebook.exchangerequest_feature.data.remote.service.ExchangeRequestService
import com.example.sharebook.exchangerequest_feature.domain.adapter.ExchangeRequestRepository
import javax.inject.Inject

class ExchangeRequestRepositoryImpl @Inject constructor(
    private val exchangeRequestService: ExchangeRequestService) : ExchangeRequestRepository {
    override suspend fun sendRequest(body : SendRequestModel): SendRequestResponse {
        return exchangeRequestService.sendRequest(body)
    }
}