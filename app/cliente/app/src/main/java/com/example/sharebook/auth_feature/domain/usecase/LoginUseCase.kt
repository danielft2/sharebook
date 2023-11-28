package com.example.sharebook.auth_feature.domain.usecase

import com.example.sharebook.auth_feature.data.remote.model.LoginModel
import com.example.sharebook.auth_feature.data.remote.response.LoginResponse
import com.example.sharebook.auth_feature.domain.adpater.AuthRepository
import com.example.sharebook.core.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: AuthRepository)   {
    operator fun invoke(body: LoginModel): Flow<Resource<LoginResponse>> {
        return flow {
            try {
                emit(Resource.Loading())
                val response = repository.login(body)
                emit(Resource.Success(response))
            } catch (e: HttpException) {
                if (e.code() == 401) {
                    emit(Resource.Error("Usuário ou senha incorretos", code = 401))
                } else {
                    emit(Resource.Error("Ocorreu um erro inesperado!"))
                }
            } catch (e: IOException) {
                emit(Resource.Error("Aconteu um erro inesperado, verifique sua conexão!"))
            } finally {
                emit(Resource.Finnaly())
            }
        }
    }
}