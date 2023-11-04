package com.example.sharebook.core.domain.adapter

import com.example.sharebook.core.data.remote.response.CepResponse

interface CepRepository {
    suspend fun consultCEP(cep: String): CepResponse
}