package com.example.sharebook.exchangerequest_feature.domain.usecases

import com.example.sharebook.core.utils.Resource
import com.example.sharebook.exchangerequest_feature.data.remote.model.SendRequestModel
import com.example.sharebook.exchangerequest_feature.domain.adapter.ExchangeRequestRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SendRequestUseCase @Inject constructor(
    private val exchangeRequestRepository: ExchangeRequestRepository
) {
    operator fun invoke(body: SendRequestModel): Flow<Resource<Boolean>> {
       return flow {
           try {
               emit(Resource.Loading())
               exchangeRequestRepository.sendRequest(body)
               emit(Resource.Success(true))
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