package com.example.sharebook.auth_feature.domain.usecase

import com.example.sharebook.auth_feature.data.remote.model.RegisterModel
import com.example.sharebook.auth_feature.data.remote.response.RegisterResponse
import com.example.sharebook.auth_feature.domain.adpater.AuthRepository
import com.example.sharebook.core.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val repository: AuthRepository) {
    operator fun invoke(body: RegisterModel): Flow<Resource<RegisterResponse>> {
        return flow {
            try {
                emit(Resource.Loading())
                val response = repository.register(body)
                emit(Resource.Success(response))
            } catch (e: IOException) {
                emit(Resource.Error("Ocorreu um erro inesperado, verifique sua conexão!"))
            } catch (e: HttpException) {
                if (e.code() == 409) {
                    emit(Resource.Error("Número já cadastrado.", code = 409))
                } else {
                    emit(Resource.Error("Ocorreu um erro inesperado!"))
                }
            } finally {
                emit(Resource.Finnaly())
            }
        }
    }
}