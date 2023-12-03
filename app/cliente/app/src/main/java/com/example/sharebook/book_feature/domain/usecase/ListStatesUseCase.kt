package com.example.sharebook.book_feature.domain.usecase

import com.example.sharebook.book_feature.data.remote.model.toSelectItem
import com.example.sharebook.book_feature.domain.adapter.BookRepository
import com.example.sharebook.core.presentation.components.input.types.SelectItem
import com.example.sharebook.core.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ListStatesUseCase @Inject constructor(private val bookRepository: BookRepository) {
    operator fun invoke(): Flow<Resource<List<SelectItem>>> {
        return flow {
            try {
                emit(Resource.Loading())
                val response = bookRepository.listStates()
                emit(Resource.Success(response.map { it.toSelectItem() }))
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