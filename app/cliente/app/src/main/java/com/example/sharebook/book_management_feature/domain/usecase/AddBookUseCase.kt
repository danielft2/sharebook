package com.example.sharebook.book_management_feature.domain.usecase

import com.example.sharebook.book_management_feature.data.remote.model.AddBookModel
import com.example.sharebook.book_management_feature.data.remote.response.AddBookResponse
import com.example.sharebook.book_management_feature.domain.adapter.BookManagementRepository
import com.example.sharebook.core.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class AddBookUseCase @Inject constructor(private val repository: BookManagementRepository){
    operator fun invoke(body: AddBookModel): Flow<Resource<AddBookResponse>> {
        return flow {
            try{
                emit(Resource.Loading())
                val response = repository.addBook(body)
                emit(Resource.Success(response))
            } catch (e: IOException) {
                emit(Resource.Error("Ocorreu um erro inesperado, verifique sua conexão!"))
            } catch (e: HttpException) {
                if (e.code() == 409) {
                    emit(Resource.Error("Livro já cadastrado.", code = 409))
                } else {
                    emit(Resource.Error("Ocorreu um erro inesperado!"))
                }
            }
        }
    }
}