package com.example.sharebook.core.data.remote.respository

import com.example.sharebook.core.data.remote.response.CepResponse
import com.example.sharebook.core.data.remote.service.CepService
import com.example.sharebook.core.domain.adapter.CepRepository
import javax.inject.Inject

class CepRepositoryImpl @Inject constructor(private val cepService: CepService) : CepRepository {
    override suspend fun consultCEP(cep: String): CepResponse {
        return cepService.consultCep(cep = cep)
    }
}