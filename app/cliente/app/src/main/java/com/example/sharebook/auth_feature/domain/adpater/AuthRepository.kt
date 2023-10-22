package com.example.sharebook.auth_feature.domain.adpater
import com.example.sharebook.auth_feature.data.remote.model.LoginModel
import com.example.sharebook.auth_feature.data.remote.model.RegisterModel
import com.example.sharebook.auth_feature.data.remote.response.LoginResponse
import com.example.sharebook.auth_feature.data.remote.response.RegisterResponse

interface AuthRepository {
    suspend fun login(body: LoginModel): LoginResponse
    suspend fun register(body: RegisterModel): RegisterResponse
}