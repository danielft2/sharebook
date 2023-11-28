package com.example.sharebook.core.data.remote.service

import com.example.sharebook.core.data.remote.response.IsbnResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface IsbnService {
    @GET("{isbn}")
    suspend fun consultIsbn(@Path("isbn") isbn: String) : IsbnResponse
}