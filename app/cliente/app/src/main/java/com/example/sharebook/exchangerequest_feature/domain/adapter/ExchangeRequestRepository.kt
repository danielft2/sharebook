package com.example.sharebook.exchangerequest_feature.domain.adapter

import com.example.sharebook.exchangerequest_feature.data.remote.model.SendRequestModel
import com.example.sharebook.exchangerequest_feature.data.remote.response.SendRequestResponse

interface ExchangeRequestRepository {
    suspend fun sendRequest(body: SendRequestModel): SendRequestResponse
}