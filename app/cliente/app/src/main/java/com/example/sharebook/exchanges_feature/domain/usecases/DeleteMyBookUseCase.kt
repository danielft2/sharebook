package com.example.sharebook.exchanges_feature.domain.usecases

import com.example.sharebook.core.utils.Resource
import com.example.sharebook.exchanges_feature.domain.adapter.ExchangesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DeleteMyBookUseCase @Inject constructor(private val exchangesRepository: ExchangesRepository) {
    operator fun invoke(bookId: String) : Flow<Resource<Boolean>> {
        return flow {
            try {
                emit(Resource.Loading())
                exchangesRepository.deleteMyBook(bookId)
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