package com.example.sharebook.core.data.remote.service

import com.example.sharebook.core.data.remote.response.CepResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CepService {
    @GET("{cep}/json")
    suspend fun consultCep(@Path("cep") cep: String): CepResponse
}