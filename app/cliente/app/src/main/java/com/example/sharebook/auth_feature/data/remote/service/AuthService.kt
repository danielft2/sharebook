package com.example.sharebook.auth_feature.data.remote.service

import com.example.sharebook.auth_feature.data.remote.model.LoginModel
import com.example.sharebook.auth_feature.data.remote.model.RegisterModel
import com.example.sharebook.auth_feature.data.remote.response.LoginResponse
import com.example.sharebook.auth_feature.data.remote.response.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("/login")
    suspend fun login(@Body body: LoginModel): LoginResponse

    @POST("/user")
    suspend fun register(@Body body: RegisterModel): RegisterResponse
}