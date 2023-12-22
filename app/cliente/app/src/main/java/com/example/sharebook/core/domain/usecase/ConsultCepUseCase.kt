package com.example.sharebook.core.domain.usecase

import com.example.sharebook.core.data.remote.response.toCepModel
import com.example.sharebook.core.domain.adapter.CepRepository
import com.example.sharebook.core.domain.model.CepModel
import com.example.sharebook.core.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class ConsultCepUseCase @Inject constructor(private val repository: CepRepository)  {
    operator fun invoke(cep: String): Flow<Resource<CepModel>> {
        return flow {
            try {
                emit(Resource.Loading())
                val response = repository.consultCEP(cep)
                if (response.erro) {
                    emit(Resource.Error("Insira um cep válido"))
                } else {
                    emit(Resource.Success(response.toCepModel()))
                }
            } catch (e: HttpException) {
                emit(Resource.Error(e.localizedMessage ?: "Ocorreu um erro inesperado!"))
            } catch (e: IOException) {
                emit(Resource.Error("Aconteu um erro inesperado, verifique sua conexão!"))
            } finally {
                emit(Resource.Finnaly())
            }
        }
    }
}