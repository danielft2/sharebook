package com.example.sharebook.book_feature.domain.usecase

import com.example.sharebook.book_feature.data.remote.response.toBookDetailsModel
import com.example.sharebook.book_feature.domain.adapter.BookRepository
import com.example.sharebook.book_feature.domain.model.BookDetailsModel
import com.example.sharebook.core.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DetailsBookUseCase @Inject constructor(private val bookRepository: BookRepository) {
    operator fun invoke(bookId: String): Flow<Resource<BookDetailsModel>> {
        return flow {
            try {
                emit(Resource.Loading())
                val response = bookRepository.detailsBook(bookId)
                emit(Resource.Success(response.toBookDetailsModel()))
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