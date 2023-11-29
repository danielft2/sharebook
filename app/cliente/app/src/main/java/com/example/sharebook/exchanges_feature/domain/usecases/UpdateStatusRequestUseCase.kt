package com.example.sharebook.exchanges_feature.domain.usecases

import com.example.sharebook.core.domain.enum.BookRequestStatus
import com.example.sharebook.core.utils.Resource
import com.example.sharebook.exchangerequest_feature.data.remote.model.SendRequestModel
import com.example.sharebook.exchanges_feature.domain.adapter.ExchangesRepository
import com.example.sharebook.exchanges_feature.domain.model.RequestDetailsModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UpdateStatusRequestUseCase @Inject constructor(private val exchangesRepository: ExchangesRepository) {
    operator fun invoke(
        data: RequestDetailsModel,
        status: BookRequestStatus,
        userLoggedId: String
    ): Flow<Resource<Boolean>>  {

        val body = SendRequestModel(
            idRescueUser = userLoggedId,
            idBook = data.userExternalBook.id ?: "",
            idBookFromRescue = data.userLoggedBook.id ?: "",
            status = status
        )

        return flow {
            try {
                emit(Resource.Loading())
                exchangesRepository.updateStatusRequest(body)
                emit(Resource.Success(true))
            } catch (e: HttpException) {
                println(e)
                emit(Resource.Error("Ocorreu um erro inesperado!"))
            } catch (e: IOException) {
                emit(Resource.Error("Aconteu um erro inesperado, verifique sua conexão!"))
            } finally {
                emit(Resource.Finnaly())
            }
        }
    }
}