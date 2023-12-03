package com.example.sharebook.book_feature.domain.usecase

import com.example.sharebook.book_feature.data.remote.model.FormBookModel
import com.example.sharebook.book_feature.data.remote.model.toFormBookMultipartform
import com.example.sharebook.book_feature.domain.adapter.BookRepository
import com.example.sharebook.core.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CreateBookUseCase @Inject constructor(private val bookRepository: BookRepository) {
    operator fun invoke(body: FormBookModel): Flow<Resource<Boolean>> {
        return flow {
            try {
                emit(Resource.Loading())
                bookRepository.createBook(book = body.toFormBookMultipartform())
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