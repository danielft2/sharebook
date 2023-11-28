package com.example.sharebook.exchanges_feature.domain.usecases

import com.example.sharebook.core.utils.Functions
import com.example.sharebook.core.utils.Resource
import com.example.sharebook.exchanges_feature.data.remote.model.toBookSumaryExternalModel
import com.example.sharebook.exchanges_feature.data.remote.model.toBookYourSumaryModel
import com.example.sharebook.exchanges_feature.domain.adapter.ExchangesRepository
import com.example.sharebook.exchanges_feature.domain.model.RequestDetailsModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RequestDetailsUseCase @Inject constructor(
    private val exchangesRepository: ExchangesRepository) {

    operator fun invoke(
        requestId: String, userNameLogged: String? = ""
    ): Flow<Resource<RequestDetailsModel>> {
        return flow {
            try {
                emit(Resource.Loading())
                val response = exchangesRepository.requestDetails(requestId)
                emit(Resource.Success(
                    RequestDetailsModel(
                        userLoggedBook = response.userLoggedRequest
                            .toBookYourSumaryModel(userNameLogged ?: ""),
                        userExternalBook = response.extertalUserRequest
                            .toBookSumaryExternalModel(),
                        userExternalLocation = response.extertalUserRequest.localizacao ?: "",
                        userExternalPhone = response.extertalUserRequest.telefone ?: "",
                        status = Functions.getStatusByName(response.status)
                    )
                ))
            } catch (e: HttpException) {
                emit(Resource.Error("Ocorreu um erro inesperado!"))
            } catch (e: IOException) {
                emit(Resource.Error("Aconteu um erro inesperado, verifique sua conexão!"))
            } finally {
                emit(Resource.Finnaly())
            }
        }
    }
}