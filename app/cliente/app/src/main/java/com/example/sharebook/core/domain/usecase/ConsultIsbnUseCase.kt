package com.example.sharebook.core.domain.usecase

import com.example.sharebook.core.data.remote.response.toIsbnModel
import com.example.sharebook.core.domain.adapter.IsbnRepository
import com.example.sharebook.core.domain.model.IsbnModel
import com.example.sharebook.core.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import retrofit2.HttpException
import java.io.IOException

class ConsultIsbnUseCase @Inject constructor(private val repository: IsbnRepository) {
    operator fun invoke(isbn: String): Flow<Resource<IsbnModel>> {
        return flow {
            try {
                emit(Resource.Loading())
                val response = repository.consultIsbn(isbn)
                if(response.totalItens == 0){
                    emit(Resource.Error("Insira um isbn válido"))
                }else{
                    emit(Resource.Success(response.toIsbnModel()))
                }
            } catch (e: HttpException) {
                emit(Resource.Error(e.localizedMessage ?: "Ocorreu um erro inesperado!"))
            } catch (e: IOException) {
                emit(Resource.Error("Aconteu um erro inesperado, verifique sua conexão!"))
            }
        }
    }
}