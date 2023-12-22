package com.example.sharebook.exchangerequest_feature.data.remote.service

import com.example.sharebook.exchangerequest_feature.data.remote.model.SendRequestModel
import com.example.sharebook.exchangerequest_feature.data.remote.response.SendRequestResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ExchangeRequestService {
    @POST("/rescue")
    suspend fun sendRequest(@Body body: SendRequestModel): SendRequestResponse
}