package com.example.sharebook.auth_feature.data.remote.repository
import com.example.sharebook.auth_feature.data.remote.model.LoginModel
import com.example.sharebook.auth_feature.data.remote.model.RegisterModel
import com.example.sharebook.auth_feature.data.remote.response.LoginResponse
import com.example.sharebook.auth_feature.data.remote.response.RegisterResponse
import com.example.sharebook.auth_feature.data.remote.service.AuthService
import com.example.sharebook.auth_feature.domain.adpater.AuthRepository
import timber.log.Timber
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val authService: AuthService): AuthRepository {
    override suspend fun login(body: LoginModel): LoginResponse {
        return authService.login(body)
    }

    override suspend fun register(body: RegisterModel): RegisterResponse {
        return authService.register(body)
    }
}