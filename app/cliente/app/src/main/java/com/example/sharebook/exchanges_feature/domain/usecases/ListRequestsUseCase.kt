package com.example.sharebook.exchanges_feature.domain.usecases

import com.example.sharebook.core.utils.Resource
import com.example.sharebook.exchanges_feature.data.remote.model.toRequestModel
import com.example.sharebook.exchanges_feature.domain.adapter.ExchangesRepository
import com.example.sharebook.exchanges_feature.domain.model.RequestModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ListRequestsUseCase @Inject constructor(private val exchangesRepository: ExchangesRepository) {
    operator fun invoke(userId: String): Flow<Resource<List<RequestModel>>> {
        return flow {
            try {
                val list = mutableListOf<RequestModel>()
                emit(Resource.Loading())

                val response = exchangesRepository.listRequests(userId)
                list.addAll(response.rescuesFromUser.map { it.toRequestModel() })
                list.addAll(response.rescuesToUser.map { it.toRequestModel() })

                emit(Resource.Success(list))
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