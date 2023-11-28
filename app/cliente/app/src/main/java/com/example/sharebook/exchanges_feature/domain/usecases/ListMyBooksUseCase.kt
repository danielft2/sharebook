package com.example.sharebook.exchanges_feature.domain.usecases

import com.example.sharebook.core.utils.Resource
import com.example.sharebook.exchanges_feature.data.remote.model.toMyBookModel
import com.example.sharebook.exchanges_feature.domain.adapter.ExchangesRepository
import com.example.sharebook.exchanges_feature.domain.model.MyBookModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ListMyBooksUseCase @Inject constructor(private val exchangesRepository: ExchangesRepository) {
    operator fun invoke() : Flow<Resource<List<MyBookModel>>> {
        return flow {
            try {
                emit(Resource.Loading())

                val response = exchangesRepository.listMyBooks()
                if (response.isEmpty()) emit(Resource.Success(listOf()))
                else emit(Resource.Success(
                    response.map { it.toMyBookModel() }
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